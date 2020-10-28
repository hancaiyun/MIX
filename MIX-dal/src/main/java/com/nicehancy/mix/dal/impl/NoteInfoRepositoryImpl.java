package com.nicehancy.mix.dal.impl;

import com.nicehancy.mix.common.enums.NoteStatusEnum;
import com.nicehancy.mix.common.enums.ShareFlagEnum;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.NoteInfoRepository;
import com.nicehancy.mix.dal.model.request.DirectoryQueryReqDO;
import com.nicehancy.mix.dal.model.request.FileListReqDO;
import com.nicehancy.mix.dal.model.NoteInfoDO;
import com.nicehancy.mix.dal.model.request.NoteQueryReqDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.util.Date;
import java.util.List;

/**
 * 笔记repository
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/17 12:09
 **/
@Repository(value = "noteInfoRepositoryImpl")
public class NoteInfoRepositoryImpl implements NoteInfoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 目录列表查询
     * @param reqDO                  请求DO
     * @return                       目录列表
     */
    @Override
    public List<NoteInfoDO> queryDirectory(DirectoryQueryReqDO reqDO) {

        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(reqDO.getUserNo());
        if(!StringUtils.isEmpty(reqDO.getPrimaryDirectory())) {
            criteria.and("primaryDirectory").is(reqDO.getPrimaryDirectory());
        }
        if(!StringUtils.isEmpty(reqDO.getSecondaryDirectory())){
            criteria.and("secondaryDirectory").is(reqDO.getSecondaryDirectory());
        }
        criteria.and("status").is(NoteStatusEnum.ENABLE.getCode());
        query.addCriteria(criteria);
        return mongoTemplate.find(query, NoteInfoDO.class);
    }

    /**
     * 文档列表查询
     * @param reqDO                  请求DO
     * @return                       列表结果
     */
    @Override
    public List<NoteInfoDO> queryFileList(FileListReqDO reqDO) {

        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(reqDO.getUserNo());
        criteria.and("primaryDirectory").is(reqDO.getPrimaryDirectory());
        criteria.and("secondaryDirectory").is(null).orOperator(criteria.and("secondaryDirectory").is(""));
        criteria.and("status").is(NoteStatusEnum.ENABLE.getCode());
        query.addCriteria(criteria);
        return mongoTemplate.find(query, NoteInfoDO.class);
    }

    /**
     * 根据文档id获取文档信息
     * @param reqDO             请求参数
     * @return                  文档信息
     */
    @Override
    public List<NoteInfoDO> queryNoteInfo(NoteQueryReqDO reqDO) {

        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(reqDO.getUserNo());
        if(!StringUtils.isEmpty(reqDO.getPrimaryDirectory())){
            criteria.and("primaryDirectory").is(reqDO.getPrimaryDirectory());
        }
        if(!StringUtils.isEmpty(reqDO.getSecondaryDirectory())){
            criteria.and("secondaryDirectory").is(reqDO.getSecondaryDirectory());
        }
        if(!StringUtils.isEmpty(reqDO.getFileName())){
            criteria.and("fileName").is(reqDO.getFileName());
        }
        criteria.and("status").is(NoteStatusEnum.ENABLE.getCode());

        query.addCriteria(criteria);
        return mongoTemplate.find(query, NoteInfoDO.class);
    }

    /**
     * 保存笔记
     * @param noteInfoDO             笔记DO
     */
    @Override
    public void saveNote(NoteInfoDO noteInfoDO) {

        //字段初始化
        noteInfoDO.setId(Long.valueOf(UUIDUtil.createNoByUUId()));
        noteInfoDO.setCreatedAt(new Date());
        noteInfoDO.setUpdatedAt(new Date());
        noteInfoDO.setStatus(NoteStatusEnum.ENABLE.getCode());

        mongoTemplate.insert(noteInfoDO);
    }

    /**
     * 更新文档内容
     * @param noteDO              文档
     */
    @Override
    public void updateContent(NoteInfoDO noteDO) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(noteDO.getUserNo());
        criteria.and("primaryDirectory").is(noteDO.getPrimaryDirectory());
        criteria.and("secondaryDirectory").is(noteDO.getSecondaryDirectory());
        criteria.and("fileName").is(noteDO.getFileName());
        query.addCriteria(criteria);

        //更新内容
        Update update = new Update();
        update.set("content", noteDO.getContent());
        update.set("updatedAt", new Date());
        update.set("updatedBy", noteDO.getUpdatedBy());
        if(!StringUtils.isEmpty(noteDO.getStatus())){
            update.set("status", noteDO.getStatus());
        }

        //更新操作
        mongoTemplate.updateMulti(query, update, NoteInfoDO.class);

    }

    /**
     * 更新目录
     * @param noteInfoDO             DO
     * @param opLocation             更新目录位置
     * @param opName                 更新值
     */
    @Override
    public void updateDirectory(NoteInfoDO noteInfoDO, String opLocation, String opName) {

        //查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(noteInfoDO.getUserNo());
        criteria.and("primaryDirectory").is(noteInfoDO.getPrimaryDirectory());
        if(!StringUtils.isEmpty(noteInfoDO.getSecondaryDirectory())) {
            criteria.and("secondaryDirectory").is(noteInfoDO.getSecondaryDirectory());
        }
        if(!StringUtils.isEmpty(noteInfoDO.getFileName())) {
            criteria.and("fileName").is(noteInfoDO.getFileName());
        }
        query.addCriteria(criteria);

        //更新内容
        Update update = new Update();
        if("PRIMARY_DIRECTORY_OP".equals(opLocation)){
            update.set("primaryDirectory", opName);
        }
        if("SECONDARY_DIRECTORY_OP".equals(opLocation)){
            update.set("secondaryDirectory", opName);
        }
        if("FILE_OP".equals(opLocation)){
            update.set("fileName", opName);
        }

        update.set("updatedAt", new Date());
        update.set("updatedBy", noteInfoDO.getUpdatedBy());

        //更新操作
        mongoTemplate.updateMulti(query, update, NoteInfoDO.class);
    }

    /**
     * 删除更新
     * @param reqDO                  请求DO
     * @param opLocation             删除位置
     */
    @Override
    public void updateForDelete(NoteInfoDO reqDO, String opLocation) {

        //查询条件
        Query query = new Query();
        //更新内容
        Update update = new Update();
        Criteria criteria = new Criteria();
        criteria.and("userNo").is(reqDO.getUserNo());
        if("PRIMARY_DIRECTORY_OP".equals(opLocation)){
            criteria.and("primaryDirectory").is(reqDO.getPrimaryDirectory());
            update.set("status", reqDO.getStatus());
        }
        if("SECONDARY_DIRECTORY_OP".equals(opLocation)){
            criteria.and("primaryDirectory").is(reqDO.getPrimaryDirectory());
            criteria.and("secondaryDirectory").is(reqDO.getSecondaryDirectory());
            update.set("secondaryDirectory", "");
            update.set("fileName", "");
            update.set("content", "");
        }
        if("FILE_OP".equals(opLocation)){
            criteria.and("primaryDirectory").is(reqDO.getPrimaryDirectory());
            if(!StringUtils.isEmpty(reqDO.getSecondaryDirectory())) {
                criteria.and("secondaryDirectory").is(reqDO.getSecondaryDirectory());
            }
            criteria.and("fileName").is(reqDO.getFileName());
            update.set("status", reqDO.getStatus());
        }

        query.addCriteria(criteria);
        update.set("updatedAt", new Date());
        update.set("updatedBy", reqDO.getUpdatedBy());

        //更新操作
        mongoTemplate.updateMulti(query, update, NoteInfoDO.class);
    }

    /**
     * 分享更新
     * @param noteInfoDO             请求DO
     */
    @Override
    public void updateForShare(NoteInfoDO noteInfoDO) {

        //查询条件
        Query query = new Query();
        //更新内容
        Update update = new Update();
        Criteria criteria = new Criteria();
        criteria.and("id").is(noteInfoDO.getId());

        query.addCriteria(criteria);
        update.set("shareFlag", noteInfoDO.getShareFlag());
        update.set("updatedBy", noteInfoDO.getUpdatedBy());
        update.set("updatedAt", new Date());

        //更新操作
        mongoTemplate.updateMulti(query, update, NoteInfoDO.class);
    }

    /**
     * 共享文档列表总数查询
     * @return              共享文档列表总数
     */
    @Override
    public int queryShareCount() {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("shareFlag").is(ShareFlagEnum.TRUE.getCode());
        criteria.and("status").is(NoteStatusEnum.ENABLE.getCode());
        query.addCriteria(criteria);
        //设置排序
        query.with(Sort.by(Sort.Direction.DESC, "updatedAt"));

        return (int)mongoTemplate.count(query, NoteInfoDO.class);
    }

    /**
     * 共享文档列表查询
     * @param current                当前页
     * @param limit                  每页条目数
     * @return                       文档列表
     */
    @Override
    public List<NoteInfoDO> queryShareNote(int current, int limit) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("shareFlag").is(ShareFlagEnum.TRUE.getCode());
        criteria.and("status").is(NoteStatusEnum.ENABLE.getCode());
        query.addCriteria(criteria);

        //分页
        query.skip((current - 1) * limit).limit(limit).with(Sort.by(Sort.Direction.DESC, "updatedAt"));

        return mongoTemplate.find(query, NoteInfoDO.class);
    }

    /**
     * 根据id查询文档明细
     * @param id                      数据库主键id
     * @return                        文档信息
     */
    @Override
    public NoteInfoDO queryNoteById(Long id) {

        //设置分页查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("id").is(id);
        criteria.and("status").is(NoteStatusEnum.ENABLE.getCode());
        query.addCriteria(criteria);

        return mongoTemplate.findOne(query, NoteInfoDO.class);
    }
}
