/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.analytics.service;

import com.google.common.base.Strings;
import info.chili.analytics.jrs.EmailEventsResource.EmailEventsTable;
import info.chili.analytics.model.Event;
import info.chili.email.Email;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ayalamanchili
 */
@Component
@Scope("prototype")
@Transactional
public class EmailEventsService {

    @Autowired
    protected MongoOperations mongoTemplate;

    public EmailEventsTable getEvents(int start, int limit) {
        EmailEventsTable res = new EmailEventsTable();
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "sentTimeStamp"));
        res.setEntities(mongoTemplate.find(query.limit(limit).skip(start), Email.class));
        res.setSize(mongoTemplate.count(query, Email.class));
        return res;
    }

    public List<Event> searchEvents(SearchEventDto search) {
        Query query = new Query();
        if (!Strings.isNullOrEmpty(search.getUser())) {
            query.addCriteria(Criteria.where("user").is(search.getUser().trim()));
        }
        if (!Strings.isNullOrEmpty(search.getName())) {
            query.addCriteria(Criteria.where("name").regex(search.getName().trim()));
        }
        if (!Strings.isNullOrEmpty(search.getInput())) {
            query.addCriteria(Criteria.where("input").regex(search.getInput().trim()));
        }
        if (!Strings.isNullOrEmpty(search.getOutput())) {
            query.addCriteria(Criteria.where("output").regex(search.getOutput().trim()));
        }
        query.with(new Sort(Sort.Direction.DESC, "evenTimeStamp"));
        return mongoTemplate.find(query.limit(100), Event.class);
    }
}