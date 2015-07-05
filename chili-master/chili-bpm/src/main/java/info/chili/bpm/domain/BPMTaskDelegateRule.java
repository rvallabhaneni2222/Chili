/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.domain;

import info.chili.jpa.AbstractEntity;
import info.chili.service.jrs.types.Entry;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ayalamanchili
 */
@Entity
@Audited
@XmlRootElement
public class BPMTaskDelegateRule extends AbstractEntity {

    public static final String BPM_TASK_DELEGATE_RULE_CACHE_REGION = "bpm-task-delegate-rules";
    
    /**
     *
     */
    @NotEmpty
    @org.hibernate.annotations.Index(name = "BPM_TSK_RUL_PRC_ID")
    protected String bpmProcessId;
    /**
     *
     */
    @NotEmpty
    @org.hibernate.annotations.Index(name = "BPM_TSK_RUL_TSK_ID")
    protected String bpmTaskId;
    /**
     *
     */
    @org.hibernate.annotations.Index(name = "BPM_TSK_RUL_NM")
    @NotEmpty
    protected String ruleName;
    /**
     *
     */
    protected String ruleExpression;
    /**
     *
     */
    @Lob
    protected String attributeData;
    /**
     *
     */
    @Transient
    @Valid
    protected List<Entry> attributes;

    public String getBpmProcessId() {
        return bpmProcessId;
    }

    public void setBpmProcessId(String bpmProcessId) {
        this.bpmProcessId = bpmProcessId;
    }

    public String getBpmTaskId() {
        return bpmTaskId;
    }

    public void setBpmTaskId(String bpmTaskId) {
        this.bpmTaskId = bpmTaskId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleExpression() {
        return ruleExpression;
    }

    public void setRuleExpression(String ruleExpression) {
        this.ruleExpression = ruleExpression;
    }

    public String getAttributeData() {
        return attributeData;
    }

    public void setAttributeData(String attributeData) {
        this.attributeData = attributeData;
    }

    public List<Entry> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new ArrayList();
        }
        return attributes;
    }

    public void setAttributes(List<Entry> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Entry entry) {
        this.getAttributes().add(entry);
    }

}
