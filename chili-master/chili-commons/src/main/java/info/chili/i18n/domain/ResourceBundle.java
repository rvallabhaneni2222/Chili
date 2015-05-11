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

/**
 *
 * @author ayalamanchili
 */
@Entity
public class ResourceBundle implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private ResourceLocale resourceLocale;

    @OneToMany(mappedBy = "resourceBundle", cascade = CascadeType.ALL)
    private List<Resource> resources;

    public ResourceBundle() {
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

    public ResourceLocale getResourceLocale() {
        return resourceLocale;
    }

    public void setResourceLocale(ResourceLocale resourceLocale) {
        this.resourceLocale = resourceLocale;
    }

    public void addResource(Resource resource) {
        this.getResources().add(resource);
    }

    public List<Resource> getResources() {
        if (this.resources == null) {
            this.resources = new ArrayList();
        }
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

}
