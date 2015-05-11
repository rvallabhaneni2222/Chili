/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.i18n;

import info.chili.i18n.domain.ResourceBundle;
import info.chili.spring.SpringContext;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Component
public class DatabaseMessages {

    public static final String CHILI_DB_MESSAGES_BUNDLE = "chili-db-messages";
    @PersistenceContext
    protected EntityManager em;

    protected String bundleName = CHILI_DB_MESSAGES_BUNDLE;

    public Object handleGetObject(String key) {
        return handleGetObject(CHILI_DB_MESSAGES_BUNDLE, key, Locale.ENGLISH);
    }

    public Object handleGetObject(String key, Locale locale) {
        return handleGetObject(CHILI_DB_MESSAGES_BUNDLE, key, locale);
    }

    public Object handleGetObject(String bundleName, String key, Locale locale) {
        try {
            System.out.println("asdfasdf" + em.find(ResourceBundle.class, 1).getName());
            System.out.println("asdfasdf" + em.find(ResourceBundle.class, 1).getResources().size());
            return em.createNamedQuery("value")
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

    public static DatabaseMessages instance() {
        return SpringContext.getBean(DatabaseMessages.class);
    }

}
