/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.i18n.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.envers.Audited;

/**
 *
 * @author ayalamanchili
 */
@Entity
@Audited
@XmlRootElement
@XmlType
public class Ci18nResourceBundle implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Ci18nResourceLocale resourceLocale;

    @OneToMany(mappedBy = "resourceBundle", cascade = CascadeType.ALL)
    private List<Ci18nResource> resources;

    public Ci18nResourceBundle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ci18nResourceLocale getResourceLocale() {
        return resourceLocale;
    }

    public void setResourceLocale(Ci18nResourceLocale resourceLocale) {
        this.resourceLocale = resourceLocale;
    }

    public void addResource(Ci18nResource resource) {
        this.getResources().add(resource);
    }

    public List<Ci18nResource> getResources() {
        if (this.resources == null) {
            this.resources = new ArrayList();
        }
        return resources;
    }

    @XmlTransient
    public void setResources(List<Ci18nResource> resources) {
        this.resources = resources;
    }

    @XmlRootElement
    @XmlType
    public static class CResourceBundleTable implements java.io.Serializable {

        protected Long size;
        protected List<Ci18nResourceBundle> entities;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        @XmlElement
        public List<Ci18nResourceBundle> getEntities() {
            return entities;
        }

        public void setEntities(List<Ci18nResourceBundle> entities) {
            this.entities = entities;
        }
    }

}
