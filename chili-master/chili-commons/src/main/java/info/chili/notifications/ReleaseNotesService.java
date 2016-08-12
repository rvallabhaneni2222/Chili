/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import com.google.common.base.Strings;
import info.chili.spring.SpringContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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
 * @author sudharani.bandaru
 */
@Component
@Scope("prototype")
@Transactional
public class ReleaseNotesService {
    
    @Autowired
    protected MongoOperations mongoTemplate;
    @Autowired
    public ReleaseNotesDao releaseNotesDao;
    
    public static ReleaseNotesService instance() {
        return SpringContext.getBean(ReleaseNotesService.class);
    }
    

    public ReleaseNotesTable getReleaseNotes(int start, int limit) {
        ReleaseNotesTable res = new ReleaseNotesTable();
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "userIds"));
        for (ReleaseNotes releaseNotes : mongoTemplate.find(query.limit(limit).skip(start), ReleaseNotes.class)) {
            ReleaseNotesDto dto = new ReleaseNotesDto();
            dto.setSummary(releaseNotes.getSummary());
            dto.setDetails(releaseNotes.getDetails());
            dto.setEndDate(releaseNotes.getEndDate());
            dto.setUserIds(releaseNotes.getUserIds());
            dto.setRoles(releaseNotes.getRoles());
            dto.setMoreInformationLink(releaseNotes.getMoreInformationLink());
            res.getEntities().add(dto);
        }
        res.setSize(mongoTemplate.count(query, ReleaseNotes.class));
        return res;
    }

    public List<ReleaseNotesDto> searchReleaseNotes(ReleaseNotesDto search) {
        List<ReleaseNotesDto> res = new ArrayList();
        Query query = new Query();
        if (!Strings.isNullOrEmpty(search.getUserIds())) {
            query.addCriteria(Criteria.where("userIds").regex(search.getUserIds(), "i"));
        }
        if (search.getEndDate() != null) {
            query.addCriteria(Criteria.where("endDate").regex(search.getEndDate().toString(), "i"));
        }
        if (!Strings.isNullOrEmpty(search.getRoles())) {
            query.addCriteria(Criteria.where("roles").regex(search.getRoles(), "i"));
        }
        query.with(new Sort(Sort.Direction.DESC, "userIds"));
        for (ReleaseNotes releaseNotes : mongoTemplate.find(query.limit(100), ReleaseNotes.class)) {
            ReleaseNotesDto dto = new ReleaseNotesDto();
            dto.setSummary(releaseNotes.getSummary());
            dto.setDetails(releaseNotes.getDetails());
            dto.setUserIds(releaseNotes.getUserIds());
            dto.setRoles(releaseNotes.getRoles());
            dto.setMoreInformationLink(releaseNotes.getMoreInformationLink());
            res.add(dto);
        }
        return res;
    }    

    @XmlRootElement
    @XmlType
    public static class ReleaseNotesDto implements java.io.Serializable {

        protected String summary;
        protected String details;
        protected Date endDate;
        protected String userIds;
        protected String roles;
        protected String moreInformationLink;

        /**
         * @return the summary
         */
        public String getSummary() {
            return summary;
        }

        /**
         * @param summary the summary to set
         */
        public void setSummary(String summary) {
            this.summary = summary;
        }

        /**
         * @return the details
         */
        public String getDetails() {
            return details;
        }

        /**
         * @param details the description to set
         */
        public void setDetails(String details) {
            this.details = details;
        }
        
        /**
         * @return the endDate
         */
        public Date getEndDate() {
            return endDate;
        }

        /**
         * @param endDate the endDate to set
         */
        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }        
        /**
         * @return the userIds
         */
        public String getUserIds() {
            return userIds;
        }

        /**
         * @param userIds the userId to set
         */
        public void setUserIds(String userIds) {
            this.userIds = userIds;
        }

        /**
         * @return the roles
         */
        public String getRoles() {
            return roles;
        }

        /**
         * @param roles the email to set
         */
        public void setRoles(String roles) {
            this.roles = roles;
        }

        /**
         * @return the MoreInfoLink
         */
        public String getMoreInformationLink() {
            return moreInformationLink;
        }

        /**
         * @param moreInformationLink
         */
        public void setMoreInformationLink(String moreInformationLink) {
            this.moreInformationLink = moreInformationLink;
        }

    }

    @XmlRootElement
    @XmlType
    public static class ReleaseNotesTable implements java.io.Serializable {

        protected Long size;
        protected List<ReleaseNotesDto> entities;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        @XmlElement
        public List<ReleaseNotesDto> getEntities() {
            if (this.entities == null) {
                this.entities = new ArrayList<>();
            }
            return entities;
        }

        public void setEntities(List<ReleaseNotesDto> entities) {
            this.entities = entities;
        }
    }

}
