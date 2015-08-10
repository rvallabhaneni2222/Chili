/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.analytics.service;

import com.google.common.base.Strings;
import info.chili.email.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.StringUtils;
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
        for (Email email : mongoTemplate.find(query.limit(limit).skip(start), Email.class)) {
            EmailEventDto dto = new EmailEventDto();
            dto.setTo(StringUtils.join(email.getTos(), ", "));
            dto.setSentTimeStamp(email.getSentTimeStamp());
            dto.setSubject(email.getSubject());
            dto.setBody(email.getBody());
            res.getEntities().add(dto);
        }
        res.setSize(mongoTemplate.count(query, Email.class));
        return res;
    }

    public List<EmailEventDto> searchEvents(EmailEventDto search) {
        List<EmailEventDto> res = new ArrayList();
        Query query = new Query();
        if (!Strings.isNullOrEmpty(search.getTo())) {
            query.addCriteria(Criteria.where("tos").regex(search.getTo(),"i"));
        }
        if (!Strings.isNullOrEmpty(search.getBody())) {
            query.addCriteria(Criteria.where("body").regex(search.getBody(),"i"));
        }
        if (!Strings.isNullOrEmpty(search.getSubject())) {
             query.addCriteria(Criteria.where("subject").regex(search.getSubject(),"i"));

        }
        query.with(new Sort(Sort.Direction.DESC, "sentTimeStamp"));
        for (Email email : mongoTemplate.find(query.limit(100), Email.class)) {
            EmailEventDto dto = new EmailEventDto();
            dto.setTo(StringUtils.join(email.getTos(), ", "));
            dto.setSentTimeStamp(email.getSentTimeStamp());
            dto.setSubject(email.getSubject());
            dto.setBody(email.getBody());
            res.add(dto);
        }
        return res;
    }

    @XmlRootElement
    @XmlType
    public static class EmailEventDto implements java.io.Serializable {

        protected String to;
        protected Date sentTimeStamp;
        protected String subject;
        protected String body;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public Date getSentTimeStamp() {
            return sentTimeStamp;
        }

        public void setSentTimeStamp(Date sentTimeStamp) {
            this.sentTimeStamp = sentTimeStamp;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    @XmlRootElement
    @XmlType
    public static class EmailEventsTable implements java.io.Serializable {

        protected Long size;
        protected List<EmailEventDto> entities;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        @XmlElement
        public List<EmailEventDto> getEntities() {
            if (this.entities == null) {
                this.entities = new ArrayList<>();
            }
            return entities;
        }

        public void setEntities(List<EmailEventDto> entities) {
            this.entities = entities;
        }
    }

}
