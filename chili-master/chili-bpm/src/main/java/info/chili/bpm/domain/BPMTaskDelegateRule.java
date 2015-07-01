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

    /**
     *
     */
    @NotEmpty
    protected String bpmProcessId;
    /**
     *
     */
    @NotEmpty
    protected String bpmTaskId;
    /**
     *
     */
    @NotEmpty
    protected String ruleName;
    /**
     *
     */
    @NotEmpty
    protected String ruleExpression;
    /**
     *
     */
    @Lob
    protected String report;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public List<Entry> getAttributes() {
        if (attributes == null) {
            this.attributes = new ArrayList();
        }
        return attributes;
    }

    public void setAttributes(List<Entry> attributes) {
        this.attributes = attributes;
    }

}
