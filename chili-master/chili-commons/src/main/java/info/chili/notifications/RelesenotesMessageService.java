/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import info.chili.security.SecurityService;
import info.chili.spring.SpringContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
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
        for (ReleaseNotes note : mongoTemplate.find(query.limit(10), ReleaseNotes.class)) {
            if (note.getUserIds().contains(securityService.getCurrentUserName()) || !Collections.disjoint(securityService.getCurrentUserRoles(), Arrays.asList(note.getRoles().split(",")))) {
                messages.add(new UserMessage(note.getId(), note.getSummary(), note.getDetails(), note.getMoreInformationLink(), 0));
            }
        }
        return messages;
    }

    public static RelesenotesMessageService instance() {
        return SpringContext.getBean(RelesenotesMessageService.class);
    }
}
