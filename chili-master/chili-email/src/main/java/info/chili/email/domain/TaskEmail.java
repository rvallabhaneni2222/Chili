/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.email.domain;

import info.chili.document.AbstractDocument;
import java.util.Date;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author ayalamanchili
 */
@Document(collection = "taskemails")
public class TaskEmail extends AbstractDocument {

    /**
     *
     */
    @Indexed
    protected String messageId;
    /**
     *
     */
    @Indexed
    protected String taskId;
    /**
     *
     */
    protected boolean processed;
    /**
     *
     */
    protected String processedByEmail;
    /**
     *
     */
    protected Date processedTimeStamp;
    /**
     *
     */
    protected String responseContent;

    public TaskEmail() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getProcessedByEmail() {
        return processedByEmail;
    }

    public void setProcessedByEmail(String processedByEmail) {
        this.processedByEmail = processedByEmail;
    }

    public Date getProcessedTimeStamp() {
        return processedTimeStamp;
    }

    public void setProcessedTimeStamp(Date processedTimeStamp) {
        this.processedTimeStamp = processedTimeStamp;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

}
