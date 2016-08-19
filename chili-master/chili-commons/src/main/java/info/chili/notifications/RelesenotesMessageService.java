/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import com.google.common.base.Strings;
import info.chili.security.SecurityService;
import info.chili.spring.SpringContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayalamanchili
 */
@Service
public class RelesenotesMessageService extends AbstractUserMessageService {

    @Autowired
    protected SecurityService securityService;

    @Autowired
    protected MongoOperations mongoTemplate;

    @Override
    public List<UserMessage> getMessages() {
        List<UserMessage> messages = new ArrayList();
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "createdDate"));
        query.addCriteria(Criteria.where("endDate").gte(new Date())
                .and("effectiveDate").lt(new Date()));
        for (ReleaseNotes note : mongoTemplate.find(query.limit(10), ReleaseNotes.class)) {
            if ((!Strings.isNullOrEmpty(note.getUserIds()) && securityService.getCurrentUserName().contains(note.getUserIds())) || (!Strings.isNullOrEmpty(note.getRoles()) && !Collections.disjoint(securityService.getCurrentUserRoles(), Arrays.asList(note.getRoles().split(","))))) {
                if (!note.getAcknowledgedIds().contains(securityService.getCurrentUserName())) {
                    messages.add(new UserMessage(note.getId(), note.getClass().getCanonicalName(), note.getSummary(), note.getDetails(), note.getMoreInformationLink(), note.getCreatedDate()));
                }
            }
        }
        return messages;
    }

    @Override
    public void acknowledgeMessage(String source, String id, String userId) {
        if (source.equals(ReleaseNotes.class.getCanonicalName())) {
            ReleaseNotes note = mongoTemplate.findById(id, ReleaseNotes.class);
            note.getAcknowledgedIds().add(userId);
            mongoTemplate.save(note);
        }
    }

    public static RelesenotesMessageService instance() {
        return SpringContext.getBean(RelesenotesMessageService.class);
    }
}
