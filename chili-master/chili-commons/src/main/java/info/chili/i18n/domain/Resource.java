/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.i18n.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ayalamanchili
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "keys",
            query = "select r.key from Resource r where r.resourceBundle.name = :bundleName and "
            + "r.resourceBundle.resourceLocale.language = :language and  "
            + "r.resourceBundle.resourceLocale.country = :country and "
            + "r.resourceBundle.resourceLocale.variant = :variant"),
    @NamedQuery(
            name = "value",
            query = "select r.value from Resource r where r.resourceBundle.name = :bundleName and "
            + "r.resourceBundle.resourceLocale.language = :language and "
            + "r.resourceBundle.resourceLocale.country = :country and "
            + "r.resourceBundle.resourceLocale.variant = :variant and "
            + "r.key = :key")
})
public class Resource implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "_key")
    private String key;

    @Column(name = "_value")
    private String value;

    @ManyToOne
    private ResourceBundle resourceBundle;

    public Resource() {
    }

    public Resource(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Resource(String key, String value, ResourceBundle resourceBundle) {
        this.key = key;
        this.value = value;
        this.resourceBundle = resourceBundle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

}
