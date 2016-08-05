/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import info.chili.document.AbstractDocument;
import java.util.Date;
import java.util.Set;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author ayalamanchili
 */
@Document(collection = "releasenotes")
public class ReleaseNotes extends AbstractDocument {

    protected String summary;
    protected String details;
    @Indexed
    protected Date createdDate;
    @Indexed
    protected Date endDate;
    protected String moreInformationLink;
    @Indexed
    protected Set<String> roles;
    @Indexed
    protected Set<String> userIds;
    @Indexed
    protected Integer priority;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMoreInformationLink() {
        return moreInformationLink;
    }

    public void setMoreInformationLink(String moreInformationLink) {
        this.moreInformationLink = moreInformationLink;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<String> userIds) {
        this.userIds = userIds;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
