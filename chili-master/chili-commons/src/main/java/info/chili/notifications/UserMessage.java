/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ayalamanchili
 */
@XmlRootElement
@XmlType
public class UserMessage {

    protected String messageId;
    protected String summary;
    protected String details;
    protected String moreDetailsLink;
    protected Integer priority;

    public UserMessage() {
    }

    public UserMessage(String messageId, String summary, String details, String moreDetailsLink, Integer priority) {
        this.messageId = messageId;
        this.summary = summary;
        this.details = details;
        this.moreDetailsLink = moreDetailsLink;
        this.priority = priority;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

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

    public String getMoreDetailsLink() {
        return moreDetailsLink;
    }

    public void setMoreDetailsLink(String moreDetailsLink) {
        this.moreDetailsLink = moreDetailsLink;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
