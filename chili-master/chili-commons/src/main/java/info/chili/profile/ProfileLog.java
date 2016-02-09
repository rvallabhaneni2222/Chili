/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.profile;

import info.chili.document.AbstractDocument;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author phani
 */
@Document(collection = "profilelogs")
@XmlRootElement
@XmlType
public class ProfileLog extends AbstractDocument {
private static final long serialVersionUID = 1L;
    @Indexed
    protected Date evenTimeStamp;
    /**
     *
     */
    @Indexed
    protected String operationName;
    /**
     *
     */
    @Indexed
    protected Long executionTime;

    public ProfileLog() {
    }

    /**
     *
     * @param operationName
     * @param executionTime
     */
    public ProfileLog(String operationName, Long executionTime) {
        this.operationName = operationName;
        this.executionTime = executionTime;
        this.evenTimeStamp = new Date();
    }

    public Date getEvenTimeStamp() {
        return evenTimeStamp;
    }

    public void setEvenTimeStamp(Date evenTimeStamp) {
        this.evenTimeStamp = evenTimeStamp;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return "ProfileLog{" + "evenTimeStamp=" + evenTimeStamp + ", operationName=" + operationName + ", executionTime=" + executionTime + '}';
    }

    @XmlRootElement
    @XmlType
    public static class ProfileLogTable implements java.io.Serializable {

        protected Long size;
        protected List<ProfileLog> entities;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        @XmlElement
        public List<ProfileLog> getEntities() {
            return entities;
        }

        public void setEntities(List<ProfileLog> entities) {
            this.entities = entities;
        }
    }
}
