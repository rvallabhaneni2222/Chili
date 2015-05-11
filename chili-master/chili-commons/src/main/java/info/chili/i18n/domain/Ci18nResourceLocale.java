/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.i18n.domain;

import java.io.Serializable;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author ayalamanchili
 */
@Embeddable
public class Ci18nResourceLocale implements Serializable {

    @Column(length = 2, name = "clanguage")
    @Length(max = 2)
    private String language = "";

    @Column(length = 2)
    @Length(max = 2)
    private String country = "";

    @Column(length = 2)
    @Length(max = 2)
    private String variant = "";

    public Ci18nResourceLocale() {
        this.language = Locale.ENGLISH.getLanguage();
    }

    public Ci18nResourceLocale(String language, String country, String variant) {
        this.language = language;
        this.country = country;
        this.variant = variant;
    }

    public Ci18nResourceLocale(Locale locale) {
        this.language = locale.getLanguage();
        this.country = locale.getCountry();
        this.variant = locale.getVariant();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

}
