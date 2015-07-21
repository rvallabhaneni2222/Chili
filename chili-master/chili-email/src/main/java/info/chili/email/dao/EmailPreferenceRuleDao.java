/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.email.dao;

import info.chili.dao.CRUDDao;
import info.chili.email.domain.EmailPreferenceRule;
import info.chili.spring.SpringContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayalamanchili
 */
@Repository
@Scope("prototype")
public class EmailPreferenceRuleDao extends CRUDDao<EmailPreferenceRule> {

    @PersistenceContext
    protected EntityManager em;

    public EmailPreferenceRuleDao() {
        super(EmailPreferenceRule.class);
    }

    public EmailPreferenceRuleDao(Class cls) {
        super(cls);
    }

    @Override
    public EmailPreferenceRule save(EmailPreferenceRule entity) {
        entity = em.merge(entity);
        return entity;
    }

    @Cacheable(EmailPreferenceRule.EMAIL_PREF_RULE_CACHE_REGION)
    public EmailPreferenceRule find(String name) {
        TypedQuery<EmailPreferenceRule> query = em.createQuery("from " + EmailPreferenceRule.class.getCanonicalName() + " where name=:nameParam", EmailPreferenceRule.class);
        query.setParameter("nameParam", name);
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }

    public static EmailPreferenceRuleDao instance() {
        return (EmailPreferenceRuleDao) SpringContext.getBean(EmailPreferenceRuleDao.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
