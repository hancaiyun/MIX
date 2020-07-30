package com.nicehancy.mix.manager.note;

import com.nicehancy.mix.common.enums.AccountTypeEnum;
import com.nicehancy.mix.dal.AccountInfoRepository;
import com.nicehancy.mix.dal.model.AccountInfoDO;
import com.nicehancy.mix.manager.convert.AccountInfoBOConvert;
import com.nicehancy.mix.manager.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
     * @param workbook              Excel操作流
     */
    private void analysisExcel(Workbook workbook, AccountImportReqBO reqBO) {

        //1、解析Excel文件
        List<AccountInfoBO> accountInfoBOS = analysis(workbook);

        //2、数据导入
        batchInsert(accountInfoBOS, reqBO);
        //TODO 3、生成反馈文件

        //TODO 4、新增导入记录（用于下载反馈文件）
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
            AccountInfoDO accountInfoDO = new AccountInfoDO();
            accountInfoDO.setUserNo(reqBO.getUserNo());
            accountInfoDO.setAddress(accountInfoBO.getAddress());
            accountInfoDO.setAccount(accountInfoBO.getAccount());
            accountInfoDO.setPassword(accountInfoBO.getPassword());
            accountInfoDO.setAccountType(accountInfoBO.getAccountType());
            accountInfoDO.setRemark(accountInfoBO.getRemark());
            accountInfoDO.setUpdatedBy(reqBO.getUserNo());
            accountInfoDO.setCreatedBy(reqBO.getUserNo());
            accountInfoRepository.add(accountInfoDO);
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
