/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.document.dao;

import info.chili.document.SerializedEntity;
import info.chili.spring.SpringContext;
import java.io.Serializable;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 * @param <T>
 */
@Component
@Scope("prototype")
public class SerializedEntityDao<T> {

    @Autowired
    protected MongoOperations mongoTemplate;

    public <T extends Serializable> void save(T object, String targetclass, String targetInstanceId) {
        SerializedEntity entity = new SerializedEntity();
        entity.setClassName(object.getClass().getCanonicalName());
        entity.setEntityData(new String(Base64.encode(SerializationUtils.serialize(object))));
        entity.setTargetClassName(targetclass);
        entity.setTargetInstanceId(targetInstanceId);
        mongoTemplate.save(entity);
    }

    public SerializedEntity find(String targetClassName, String targetInstanceId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("targetClassName").is(targetClassName));
        query.addCriteria(Criteria.where("targetInstanceId").is(targetInstanceId));
        return mongoTemplate.findOne(query, SerializedEntity.class);
    }

    public T findAndConvert(String targetClassName, String targetInstanceId) {
        return (T) convertToObject(find(targetClassName, targetInstanceId));
    }

    public Object convertToObject(SerializedEntity entity) {
        return SerializationUtils.deserialize(Base64.decode(entity.getEntityData().getBytes()));
    }

    public static SerializedEntityDao instance() {
        return SpringContext.getBean(SerializedEntityDao.class);
    }

}
