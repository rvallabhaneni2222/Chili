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

    protected String id;
    protected String source;
    protected String summary;
    protected String details;
    protected String moreDetailsLink;
    public UserMessage() {
    }

    public UserMessage(String id, String source, String summary, String details, String moreDetailsLink) {
        this.id = id;
        this.source = source;
        this.summary = summary;
        this.details = details;
        this.moreDetailsLink = moreDetailsLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

}
