/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.activiti.engine.history.HistoricTaskInstance;

/**
 *
 * @author anuyalamanchili
 */
@XmlRootElement
@XmlType
public class HistoricTask implements HistoricTaskInstance {

    protected String id;
    protected String processDefinitionId;
    protected String processInstanceId;
    protected String executionId;
    protected String name;
    protected String description;
    protected String deleteReason;
    protected String owner;
    protected String assignee;
    protected Date startTime;
    protected Date endTime;
    protected String parentTaskId;
    protected Long durationInMillis;
    protected Date dueDate;
    protected int priority;
    protected String taskDefinitionKey;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    @Override
    public Long getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(Long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    @Override
    public String toString() {
        return "HistoricTask{" + "id=" + id + ", processDefinitionId=" + processDefinitionId + ", processInstanceId=" + processInstanceId + ", executionId=" + executionId + ", name=" + name + ", description=" + description + ", deleteReason=" + deleteReason + ", owner=" + owner + ", assignee=" + assignee + ", startTime=" + startTime + ", endTime=" + endTime + ", parentTaskId=" + parentTaskId + ", durationInMillis=" + durationInMillis + ", dueDate=" + dueDate + ", priority=" + priority + ", taskDefinitionKey=" + taskDefinitionKey + '}';
    }

    @XmlRootElement
    @XmlType
    public static class HistoricTaskTable {

        protected Long size;
        protected List<HistoricTask> entities;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        @XmlElement
        public List<HistoricTask> getEntities() {
            if (this.entities == null) {
                this.entities = new ArrayList<HistoricTask>();
            }
            return entities;
        }

        public void setEntities(List<HistoricTask> entities) {
            this.entities = entities;
        }
    }
}
