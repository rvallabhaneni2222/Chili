/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.document.dao;

import info.chili.document.SerializedEntity;
import info.chili.spring.SpringContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author anuyalamanchili
 */
@Component
@Scope("prototype")
public class SerializedEntityDao {

    @Autowired
    protected MongoOperations mongoTemplate;

    public void save(Object obj, String targetClassName, String targetInstanceId) {
        SerializedEntity entity = new SerializedEntity();
        entity.setEntityClassName(obj.getClass().getCanonicalName());
        entity.setEntityData(null);
        entity.setTargetClassName(targetClassName);
        entity.setTargetInstanceId(targetInstanceId);
        mongoTemplate.save(entity);
    }

    public SerializedEntity find(String targetClassName, String targetInstanceId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("targetClassName").is(targetClassName));
        query.addCriteria(Criteria.where("targetInstanceId").is(targetInstanceId));
        return mongoTemplate.findOne(query, SerializedEntity.class);
    }

    public Object findAndConvert(String targetClassName, String targetInstanceId) {
        return null;
    }

    public static SerializedEntityDao instance() {
        return SpringContext.getBean(SerializedEntityDao.class);
    }

}
