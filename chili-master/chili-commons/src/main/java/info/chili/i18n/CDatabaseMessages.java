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
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Component("cDatabaseMessages")
public class CDatabaseMessages implements MessageSource {

    protected String bundleName;

    @PersistenceContext
    protected EntityManager em;

    public Object handleGetObject(String key) {
        return handleGetObject(bundleName, key, Locale.ENGLISH);
    }

    public Object handleGetObject(String key, Locale locale) {
        return handleGetObject(bundleName, key, locale);
    }

    public String handleGetObject(String bundleName, String key, Locale locale) {
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

    @Override
    public String getMessage(String string, Object[] os, String string1, Locale locale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage(String string, Object[] os, Locale locale) throws NoSuchMessageException {
        return handleGetObject(bundleName, string, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable msr, Locale locale) throws NoSuchMessageException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
