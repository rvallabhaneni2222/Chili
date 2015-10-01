/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.email.dao;

import com.google.common.base.Strings;
import info.chili.email.domain.TaskEmail;
import info.chili.spring.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ayalamanchili
 */
@Repository
@Scope("prototype")
@Transactional
public class TaskEmailDao {

    @Autowired
    protected MongoOperations mongoTemplate;

    public TaskEmail find(String msgId) {
        Query query = new Query();
        if (!Strings.isNullOrEmpty(msgId)) {
            query.addCriteria(Criteria.where("messageId").is(msgId));
            return mongoTemplate.findOne(query, TaskEmail.class);
        }
        return null;
    }

    public static TaskEmailDao instance() {
        return (TaskEmailDao) SpringContext.getBean(TaskEmailDao.class);
    }
}
