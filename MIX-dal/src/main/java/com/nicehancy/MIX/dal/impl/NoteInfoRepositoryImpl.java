package com.nicehancy.MIX.dal.impl;

import com.nicehancy.MIX.common.enums.NoteStatusEnum;
import com.nicehancy.MIX.common.utils.UUIDUtil;
import com.nicehancy.MIX.dal.NoteInfoRepository;
import com.nicehancy.MIX.dal.model.DirectoryQueryReqDO;
import com.nicehancy.MIX.dal.model.FileListReqDO;
import com.nicehancy.MIX.dal.model.NoteInfoDO;
import com.nicehancy.MIX.dal.model.NoteQueryReqDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
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
        criteria.and("primaryDirectory").is(reqDO.getPrimaryDirectory());
        if(StringUtils.isEmpty(reqDO.getSecondaryDirectory())){
            criteria.and("secondaryDirectory").is(null).orOperator(criteria.and("secondaryDirectory").is(""));
        }else{
            criteria.and("secondaryDirectory").is(reqDO.getSecondaryDirectory());
        }
        criteria.and("documentName").is(reqDO.getDocumentName());
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
        criteria.and("documentName").is(noteDO.getDocumentName());
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
        mongoTemplate.updateFirst(query, update, NoteInfoDO.class);

    }
}
