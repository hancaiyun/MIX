package com.nicehancy.mix.manager.note;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.constant.DatePatternConstant;
import com.nicehancy.mix.common.enums.AccountTypeEnum;
import com.nicehancy.mix.common.enums.FileTypeEnum;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.common.utils.ThreadPoolUtil;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.AccountInfoRepository;
import com.nicehancy.mix.dal.FileUploadRecordRepository;
import com.nicehancy.mix.dal.model.AccountInfoDO;
import com.nicehancy.mix.dal.model.FileUploadRecordDO;
import com.nicehancy.mix.manager.convert.AccountInfoBOConvert;
import com.nicehancy.mix.manager.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账户管理manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/2 20:02
 **/
@Slf4j
@Component
public class AccountManager {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private FileUploadRecordRepository fileUploadRecordRepository;

    /**
     * 账户新增
     * @param accountAddReqBO   BO
     */
    public void add(AccountAddReqBO accountAddReqBO) {
        accountInfoRepository.add(AccountInfoBOConvert.getDOByBO(accountAddReqBO));
    }

    /**
     * 查询分页总条目数
     * @param reqBO         请求参数
     * @return              总条目数
     */
    public int queryCount(AccountQueryReqBO reqBO) {
        return accountInfoRepository.queryCount(AccountInfoBOConvert.getDOByBO(reqBO));
    }

    /**
     * 查询分页结果集
     * @param reqBO         请求参数
     * @return              总条目数
     */
    public List<AccountInfoBO> pageQuery(AccountQueryReqBO reqBO) {

        List<AccountInfoDO> accountInfoDOList = accountInfoRepository.pageQuery(AccountInfoBOConvert.getDOByBO(reqBO));
        return AccountInfoBOConvert.getBOsByDOs(accountInfoDOList);
    }

    /**
     * 账户删除
     * @param accountDeleteReqBO    请求BO
     */
    public void delete(AccountDeleteReqBO accountDeleteReqBO) {
        accountInfoRepository.delete(AccountInfoBOConvert.getDOByBO(accountDeleteReqBO));
    }

    /**
     * 导入
     * @param reqBO                 请求参数BO
     */
    public void importAccount(AccountImportReqBO reqBO) {

        //1、文件流处理
        Workbook workbook;
        try {
            InputStream inputStream = reqBO.getFileData().getInputStream();
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e.getMessage());
        }
        //2、文件头校验
        checkHead(workbook);

        //3、空表格及数据行校验
        checkRows(workbook);

        //4、文件解析处理
        analysisExcel(workbook, reqBO);
    }

    /**
     * 文件头校验
     * @param workbook              Excel操作流
     */
    private void checkHead(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        //sheet为空校验
        if(null == sheet){
            throw new RuntimeException("文件格式错误!");
        }

        Row headRow = sheet.getRow(3);
        //空文件校验
        if(null == headRow){
            throw new RuntimeException("空文件！");
        }
        //Excel头格式校验 --
        if (headRow.getCell(0) == null || !"地址*".equals(headRow.getCell(0).toString().trim())
                || headRow.getCell(1) == null || !"账号*".equals(headRow.getCell(1).toString().trim())
                || headRow.getCell(2) == null || !"密码*".equals(headRow.getCell(2).toString().trim())
                || headRow.getCell(3) == null || !"账号类型*".equals(headRow.getCell(3).toString().trim())
                || headRow.getCell(4) == null || !"备注".equals(headRow.getCell(4).toString().trim())) {
            throw new RuntimeException("文件格式错误!");
        }
    }

    /**
     * 空表格及数据行校验
     * @param workbook              Excel操作流
     */
    private void checkRows(Workbook workbook) {

        Sheet sheet = workbook.getSheetAt(0);
        int totalRows = sheet.getLastRowNum() + 1;//计数器从0开始;
        log.info("本次数据导入行数共有{}条(不包括文件头)", totalRows - 4);

        if (totalRows < 5) {
            log.error("导入失败，Excel数据为空");
            throw new RuntimeException("Excel数据为空");
        }

        if (totalRows > 20004) {
            log.error("导入失败，导入的数据行数不允许超过20000条");
            throw new RuntimeException("导入失败, 导入数量超限");
        }
    }

    /**
     * 解析excel文件
     *
     * @param workbook Excel操作流
     */
    private void analysisExcel(Workbook workbook, AccountImportReqBO reqBO) {

        //1、解析Excel文件
        List<AccountInfoBO> accountInfoBOS = analysis(workbook);

        //2、数据导入
        batchInsert(accountInfoBOS, reqBO);

        //异步生成反馈文件并上传至指定位置，新增导入记录
        ThreadPoolUtil.execute(() -> {
            Thread.currentThread().setName("生成反馈文件并上传至指定位置，新增导入记录子线程处理开始~");
            //1、生成反馈文件
            XSSFWorkbook xssfWorkbook = createResultFile(accountInfoBOS);

            //2、上传至指定位置
            String path = uploadExcel(xssfWorkbook);

            //3、新增导入记录（用于下载反馈文件）
            createFileUploadRecord(reqBO, path);
        });
    }

    /**
     * 上传excel文件
     * @param xssfWorkbook                  excel操作对象
     * @return                              路径
     */
    private String uploadExcel(XSSFWorkbook xssfWorkbook) {

        //服务器文件目录
        String path = CommonConstant.DOWNLOAD_CENTER_PATH;

        //文件名（唯一）
        //前缀
        String prefix = DateUtil.format(new Date(), DatePatternConstant.FULL_PATTERN) + UUIDUtil.createNoByUUId();
        String fileName = prefix + CommonConstant.SUFFIX_OF_XLSX;

        //文件路径
        File localFile = new File(path);
        if (!localFile.exists()) {
            boolean isCreated = localFile.mkdirs();
            if(!isCreated){
                log.error("文件目录创建失败！");
                throw new RuntimeException("文件目录创建失败！");
            }
        }

        File file = new File(path,fileName);
        try {
            boolean isCreated = file.createNewFile();
            if(!isCreated){
                throw new RuntimeException("文件创建失败！");
            }
        } catch (IOException e) {
            log.error("文件创建失败， 失败信息：", e);
            throw new RuntimeException(e.getMessage());
        }

        //写入文件
        String filePath = path + fileName;
        OutputStream os = null;
        try {
            os = new FileOutputStream(filePath);
            xssfWorkbook.write(os);
        } catch (IOException e) {
            log.error("文件流操作失败， 失败信息: ", e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                log.error("文件流关闭失败， 失败信息：", e);
            }
        }
        return filePath;
    }

    /**
     * 新增文件批量导入记录
     * @param reqBO                    请求参数BO
     * @param path                     位置
     */
    private void createFileUploadRecord(AccountImportReqBO reqBO, String path) {

        FileUploadRecordDO fileUploadRecordDO = new FileUploadRecordDO();
        fileUploadRecordDO.setUserNo(reqBO.getUserNo());
        fileUploadRecordDO.setFilePath(path);
        fileUploadRecordDO.setFileType(FileTypeEnum.WORD.getCode());
        fileUploadRecordDO.setFileName("批量导入账号结果文件.xlsx");
        fileUploadRecordDO.setCreatedBy(reqBO.getUserNo());
        fileUploadRecordDO.setUpdatedBy(reqBO.getUserNo());
        fileUploadRecordRepository.insert(fileUploadRecordDO);
    }

    /**
     * 生成反馈文件
     * @param accountInfoBOS         账号信息BOs
     */
    private XSSFWorkbook createResultFile(List<AccountInfoBO> accountInfoBOS) {

        //创建Excel文件操作流
        //1、导出的Excel表头结构
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet("批量导入结果");

        //2、生成文件头
        XSSFRow row;
        XSSFCell cell;
        //创建Excel文件标题
        String[] tableHeader = new String[]{"地址", "账号", "密码", "账号类型", "备注", "结果", "原因"};

        row = sheet.createRow(0);
        int i = 0;
        for (String string : tableHeader) {

            sheet.setColumnWidth(i, 20 * 240);
            cell = row.createCell(i);
            cell.setCellValue(string);
            i++;
        }
        //3、写入数据
        int rowIndex = 1;
        for(AccountInfoBO resultBO : accountInfoBOS){

            row = sheet.createRow(rowIndex);
            cell = row.createCell(0);
            cell.setCellValue(resultBO.getAddress());

            cell = row.createCell(1);
            cell.setCellValue(resultBO.getAccount());

            cell = row.createCell(2);
            cell.setCellValue(resultBO.getPassword());

            cell = row.createCell(3);
            cell.setCellValue(resultBO.getAccountType());

            cell = row.createCell(4);
            cell.setCellValue(resultBO.getRemark());

            cell = row.createCell(5);
            cell.setCellValue(resultBO.getResult());

            cell = row.createCell(6);
            cell.setCellValue(resultBO.getReason());

            rowIndex++;
        }
        return xssfWorkbook;
    }

    /**
     * 批量新增
     * @param accountInfoBOS                     新增数据列表
     * @param reqBO                              批量导入请求BO
     */
    private void batchInsert(List<AccountInfoBO> accountInfoBOS, AccountImportReqBO reqBO) {

        //空集合处理
        if(CollectionUtils.isEmpty(accountInfoBOS)){
            return;
        }
        //批量新增
        for(AccountInfoBO accountInfoBO : accountInfoBOS){
            if("失败".equals(accountInfoBO.getResult())){
                continue;
            }
            AccountInfoDO accountInfoDO = new AccountInfoDO();
            accountInfoDO.setUserNo(reqBO.getUserNo());
            accountInfoDO.setAddress(accountInfoBO.getAddress());
            accountInfoDO.setAccount(accountInfoBO.getAccount());
            accountInfoDO.setPassword(accountInfoBO.getPassword());
            accountInfoDO.setAccountType(accountInfoBO.getAccountType());
            accountInfoDO.setRemark(accountInfoBO.getRemark());
            accountInfoDO.setUpdatedBy(reqBO.getUserNo());
            accountInfoDO.setCreatedBy(reqBO.getUserNo());
            accountInfoBO.setResult("成功");
            try {
                accountInfoRepository.add(accountInfoDO);
            }catch (Exception e){
                accountInfoBO.setResult("失败");
                accountInfoBO.setReason("账号信息重复");
            }
        }
    }

    /**
     * 解析Excel文件
     * @param workbook              Excel操作流
     * @return                      账号信息
     */
    private List<AccountInfoBO> analysis(Workbook workbook) {

        List<AccountInfoBO> resultBOS = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 4; i<= sheet.getLastRowNum(); i++) {

            AccountInfoBO accountInfoBO = new AccountInfoBO();
            //空行,设置空行标注
            if(isBlankRow(sheet.getRow(i))){
                //设置失败的结果及原因
                accountInfoBO.setResult("失败");
                accountInfoBO.setReason("空行");
                resultBOS.add(accountInfoBO);
                continue;
            }
            //地址
            accountInfoBO.setAddress(getRealData(sheet.getRow(i).getCell(0)));

            //账号
            accountInfoBO.setAccount(getRealData(sheet.getRow(i).getCell(1)));

            //密码
            accountInfoBO.setPassword(getRealData(sheet.getRow(i).getCell(2)));

            //账号类型
            accountInfoBO.setAccountType(AccountTypeEnum.expireOfDesc(getRealData(sheet.getRow(i).getCell(3))).
                    getCode());

            //备注
            accountInfoBO.setRemark(getRealData(sheet.getRow(i).getCell(4)));

            //参数校验，不满足账号入库条件，设置错误信息
            if(StringUtils.isEmpty(accountInfoBO.getAddress())){
                accountInfoBO.setResult("失败");
                accountInfoBO.setReason("地址不允许为空");
                resultBOS.add(accountInfoBO);
                continue;
            }
            if(StringUtils.isEmpty(accountInfoBO.getAccount())){
                accountInfoBO.setResult("失败");
                accountInfoBO.setReason("账号不允许为空");
                resultBOS.add(accountInfoBO);
                continue;
            }
            if(StringUtils.isEmpty(accountInfoBO.getPassword())){
                accountInfoBO.setResult("失败");
                accountInfoBO.setReason("密码不允许为空");
                resultBOS.add(accountInfoBO);
                continue;
            }
            if(StringUtils.isEmpty(accountInfoBO.getAccountType())){
                accountInfoBO.setResult("失败");
                accountInfoBO.setReason("账好类型不允许为空");
                resultBOS.add(accountInfoBO);
                continue;
            }
            resultBOS.add(accountInfoBO);
        }
        return resultBOS;
    }

    /**
     * 去空处理
     * @param cell              cell
     * @return                  cell的值
     */
    private String getRealData(Cell cell){
        if(cell != null){
            return cell.getStringCellValue().trim();
        }
        return null;
    }
    /**
     * 空行判定
     * @param row               行
     * @return                  校验结果
     */
    private boolean isBlankRow(Row row) {
        //空行校验
        if (null == row || checkRowsIsEmpty(row)) {
            return true;
        }
        return false;
    }

    /**
     * 空cell校验
     * @param row                   行
     * @return                      校验结果
     */
    private boolean checkRowsIsEmpty(Row row) {

        for (Cell cell : row) {
            if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                return false;
            }
        }
        return true;
    }

}
