/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.email.domain;

import info.chili.jpa.AbstractEntity;
import info.chili.jpa.validation.Unique;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ayalamanchili
 */
@Indexed
@XmlRootElement
@Entity
@Audited
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"ruleId"}))
@Unique(entity = EmailPreferenceRule.class, fields = {"ruleId"}, message = "{email.preference.rule.id.not.unique.msg}")
public class EmailPreferenceRule extends AbstractEntity {

    public static final String EMAIL_PREF_RULE_CACHE_REGION = "email-pref-rules";
    @Transient
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @NotEmpty
    protected String ruleId;
    /**
     *
     */
    @NotEmpty
    protected String name;
    /**
     *
     */
    protected String description;
    /**
     *
     */
    @NotEmpty
    protected String processId;
    /**
     *
     */
    @NotEmpty
    protected String taskId;
    /**
     *
     */
    @NotEmpty
    protected String eventName;
    /**
     *
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    protected EmailPreferenceType actionName;

    public EmailPreferenceRule() {
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public EmailPreferenceType getActionName() {
        return actionName;
    }

    public void setActionName(EmailPreferenceType actionName) {
        this.actionName = actionName;
    }

}
