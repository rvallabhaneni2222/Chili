/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.document;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.SerializationUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.codec.Base64;

/**
 *
 * @author ayalamanchili
 * @param <T>
 */
@Document(collection = "serializedentities")
@XmlRootElement
@XmlType
public class SerializedEntity<T> extends AbstractDocument {

    private static final long serialVersionUID = 1L;
    @NotEmpty
    protected String className;
    @NotEmpty
    protected String entityData;
    protected String targetClassName;
    protected String targetInstanceId;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEntityData() {
        return entityData;
    }

    public void setEntityData(String entityData) {
        this.entityData = entityData;
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    public String getTargetInstanceId() {
        return targetInstanceId;
    }

    public void setTargetInstanceId(String targetInstanceId) {
        this.targetInstanceId = targetInstanceId;
    }

    public T getEntity() {
        return (T) SerializationUtils.deserialize(Base64.decode(this.getEntityData().getBytes()));
    }

}
