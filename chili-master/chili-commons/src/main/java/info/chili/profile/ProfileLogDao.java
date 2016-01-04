/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.profile;

import info.chili.spring.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author phani
 */
@Component
@Scope("prototype")
@Transactional
public class ProfileLogDao {

    @Autowired
    protected MongoOperations mongoTemplate;

    @Async
    @Transactional
    public void save(ProfileLog log) {
        mongoTemplate.insert(log);
    }

    public static ProfileLogDao instance() {
        return SpringContext.getBean(ProfileLogDao.class);
    }
}
