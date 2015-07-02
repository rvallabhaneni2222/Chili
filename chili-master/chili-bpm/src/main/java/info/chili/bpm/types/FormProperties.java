/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType
public class FormProperties {

    protected List<FormProperty> entries;

    @XmlElement
    public List<FormProperty> getEntries() {
        if (this.entries == null) {
            this.entries = new ArrayList<FormProperty>();
        }
        return entries;
    }

    public void setEntries(List<FormProperty> entries) {
        this.entries = entries;
    }

}
