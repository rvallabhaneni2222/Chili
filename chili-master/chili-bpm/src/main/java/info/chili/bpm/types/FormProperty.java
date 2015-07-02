/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.types;

import info.chili.service.jrs.types.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.dozer.Mapper;

/**
 *
 * @author ayalamanchili
 */
@XmlRootElement
@XmlType
public class FormProperty {

    protected String id;
    protected String name;
    protected FormPropertyType type;
    protected String value;
    protected boolean readable;
    protected boolean writable;
    protected boolean required;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement  
    public FormPropertyType getType() {
        return type;
    }

    public void setType(FormPropertyType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isWritable() {
        return writable;
    }

    public boolean isRequired() {
        return required;
    }

    public static FormProperty map(Mapper mapper, org.activiti.engine.form.FormProperty property) {
        FormProperty p = mapper.map(property, FormProperty.class);
        FormPropertyType type = new FormPropertyType();
        type.setName(property.getType().getName());
        if (property.getType().getName().equals("enum")) {
            List<Entry> enums = new ArrayList<Entry>();
            HashMap<String, String> enumValues = (HashMap<String, String>) property.getType().getInformation("values");
            for (String key : enumValues.keySet()) {
                Entry e = new Entry();
                e.setId(key);
                e.setValue(enumValues.get(key));
                enums.add(e);
            }
            type.setValues(enums);
        }
        p.setType(type);
        return p;
    }
}
