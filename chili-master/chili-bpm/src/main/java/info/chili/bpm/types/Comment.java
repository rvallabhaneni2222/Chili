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

/**
 *
 * @author ayalamanchili
 */
@XmlRootElement
@XmlType
public class Comment implements org.activiti.engine.task.Comment {

    protected String userId;
    protected Date time;
    protected String taskId;
    protected String processInstanceId;
    protected String fullMessage;

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    @Override
    public String toString() {
        return "Comment{" + "userId=" + userId + ", time=" + time + ", taskId=" + taskId + ", processInstanceId=" + processInstanceId + ", fullMessage=" + fullMessage + '}';
    }

    @XmlRootElement
    @XmlType
    public static class CommentTable implements java.io.Serializable{

        protected Long size;
        protected List<Comment> entities;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        @XmlElement
        public List<Comment> getEntities() {
            if (this.entities == null) {
                this.entities = new ArrayList<Comment>();
            }
            return entities;
        }

        public void setEntities(List<Comment> entities) {
            this.entities = entities;
        }
    }
}
