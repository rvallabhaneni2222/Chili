/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.email;

import info.chili.document.AbstractDocument;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author yalamanchili
 */
@Document
public class Email extends AbstractDocument implements Serializable {

    public static final String EMAILS_CACHE_KEY = "emails";
    @Indexed
    protected Set<String> tos;
    protected Set<String> ccs;
    protected Set<String> bccs;
    @Indexed
    protected String subject = "";
    protected String body = "";
    protected Boolean html = false;
    protected Boolean richText = false;
    protected Set<String> attachments;
    protected String templateName;
    @Transient
    protected Map<String, Object> context;
    protected Map<String, String> headers;
    protected String emailPreferenceRuleId;
    @Indexed
    protected Date sentTimeStamp;

    public Email() {
    }

    public Set<String> getTos() {
        if (this.tos == null) {
            this.tos = new HashSet<>();
        }
        return tos;
    }

    public void setTos(Set<String> tos) {
        this.tos = tos;
    }

    public void addTo(String to) {
        getTos().add(to);
    }

    public void addTos(Set<String> more) {
        getTos().addAll(more);
    }

    public Set<String> getCcs() {
        if (this.ccs == null) {
            this.ccs = new HashSet<>();
        }
        return ccs;
    }

    public void setCcs(Set<String> ccs) {
        this.ccs = ccs;
    }

    public void addCc(String cc) {
        getCcs().add(cc);
    }

    public Set<String> getBccs() {
        return bccs;
    }

    public void setBccs(Set<String> bccs) {
        this.bccs = bccs;
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

    public Boolean isHtml() {
        return html;
    }

    public void setHtml(Boolean html) {
        this.html = html;
    }

    public Boolean isRichText() {
        return richText;
    }

    public void setRichText(Boolean richText) {
        this.richText = richText;
    }

    public Set<String> getAttachments() {
        if (this.attachments == null) {
            this.attachments = new HashSet<>();
        }
        return attachments;
    }

    public void setAttachments(Set<String> attachments) {
        this.attachments = attachments;
    }

    /**
     * Takes the path of the attachment relative to the content-root base
     *
     * @param attachmentPath
     */
    public void addAttachment(String attachmentPath) {
        getAttachments().add(attachmentPath);
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public Map<String, String> getHeaders() {
        if (this.headers == null) {
            this.headers = new HashMap();
        }
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getEmailPreferenceRuleId() {
        return emailPreferenceRuleId;
    }

    public void setEmailPreferenceRuleId(String emailPreferenceRuleId) {
        this.emailPreferenceRuleId = emailPreferenceRuleId;
    }

    public Date getSentTimeStamp() {
        return sentTimeStamp;
    }

    public void setSentTimeStamp(Date sentTimeStamp) {
        this.sentTimeStamp = sentTimeStamp;
    }

    @Override
    public String toString() {
        return "Email{" + "tos=" + tos + ", ccs=" + ccs + ", bccs=" + bccs + ", subject=" + subject + ", body=" + body + ", html=" + html + ", richText=" + richText + ", attachments=" + attachments + ", templateName=" + templateName + ", context=" + context + '}';
    }
}
