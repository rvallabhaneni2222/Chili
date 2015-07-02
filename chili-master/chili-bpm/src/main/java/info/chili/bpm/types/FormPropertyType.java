/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.types;

import info.chili.service.jrs.types.Entry;
import java.util.List;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ayalamanchili
 */
@XmlType
public class FormPropertyType {

    protected String name;
    protected List<Entry> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Entry> getValues() {
        return values;
    }

    public void setValues(List<Entry> values) {
        this.values = values;
    }
}
