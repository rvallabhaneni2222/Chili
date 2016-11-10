/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.document.dao;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import info.chili.document.SerializedEntity;
import info.chili.spring.SpringContext;
import java.io.Serializable;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
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

    public void save(@QueryParam("className") String className, String objData) {
        SerializedEntity entity = new SerializedEntity();
        entity.setClassName(className);
        entity.setEntityData(objData);
        mongoTemplate.save(entity);
    }

    public <T extends Serializable> void save(T object, String targetclass, String targetInstanceId) {
        SerializedEntity entity = new SerializedEntity();
        entity.setClassName(object.getClass().getCanonicalName());
        entity.setEntityData(new String(Base64.encode(SerializationUtils.serialize(object))));
        entity.setTargetClassName(targetclass);
        entity.setTargetInstanceId(targetInstanceId);
        mongoTemplate.save(entity);
    }

    public SerializedEntity find(String className, String targetClassName, String targetInstanceId) {
        Query query = new Query();
        if (!Strings.isNullOrEmpty(className)) {
            query.addCriteria(Criteria.where("className").is(className));
        }
        if (!Strings.isNullOrEmpty(targetClassName)) {
            query.addCriteria(Criteria.where("targetClassName").is(targetClassName));
        }
        if (!Strings.isNullOrEmpty(targetInstanceId)) {
            query.addCriteria(Criteria.where("targetInstanceId").is(targetInstanceId));
        }
        return mongoTemplate.findOne(query, SerializedEntity.class);
    }

    public Response findAll(String className, String targetClassName, String targetInstanceId) {
        Query query = new Query();
        if (!Strings.isNullOrEmpty(className)) {
            query.addCriteria(Criteria.where("className").is(className));
        }
        if (!Strings.isNullOrEmpty(targetClassName)) {
            query.addCriteria(Criteria.where("targetClassName").is(targetClassName));
        }
        if (!Strings.isNullOrEmpty(targetInstanceId)) {
            query.addCriteria(Criteria.where("targetInstanceId").is(targetInstanceId));
        }
        JsonArray entities = new JsonArray();
        Gson gson = new Gson();
        for (SerializedEntity e : mongoTemplate.find(query, SerializedEntity.class)) {
            entities.add(gson.fromJson(e.getEntityData(), JsonObject.class));
        }
        return Response.ok(entities.toString()).build();
    }

    public T findAndConvert(String targetClassName, String targetInstanceId) {
        return (T) convertToObject(find(null, targetClassName, targetInstanceId));
    }

    public Object convertToObject(SerializedEntity entity) {
        return SerializationUtils.deserialize(Base64.decode(entity.getEntityData().getBytes()));
    }

    public static SerializedEntityDao instance() {
        return SpringContext.getBean(SerializedEntityDao.class);
    }

}
