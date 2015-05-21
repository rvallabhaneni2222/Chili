/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.i18n;

import info.chili.spring.SpringContext;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Component("cDatabaseMessages")
//TODO implement //TODO implement 
public class CDatabaseMessages {

    public static final String CMESSAGES_CACHE_REGION = "cmessages";

    protected String bundleName;

    @PersistenceContext
    protected EntityManager em;

    protected Object handleGetObject(String key) {
        return handleGetObject(bundleName, key, Locale.ENGLISH);
    }

    protected Object handleGetObject(String key, Locale locale) {
        return handleGetObject(bundleName, key, locale);
    }

    protected String handleGetObject(String bundleName, String key, Locale locale) {
        try {
            return (String) em.createNamedQuery("value")
                    .setParameter("bundleName", bundleName)
                    .setParameter("language", locale.getLanguage())
                    .setParameter("country", locale.getCountry())
                    .setParameter("variant", locale.getVariant())
                    .setParameter("key", key)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public static CDatabaseMessages instance(String bundleName) {
        CDatabaseMessages res = (CDatabaseMessages) SpringContext.getBean("cDatabaseMessages");
        res.setBundleName(bundleName);
        return res;
    }

    @Cacheable(CMESSAGES_CACHE_REGION)
    public String getMessage(String string, Object[] os, Locale locale) throws NoSuchMessageException {
        return handleGetObject(bundleName, string, locale);
    }

}
