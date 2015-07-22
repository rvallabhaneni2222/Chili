/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.email.dao;

import info.chili.dao.CRUDDao;
import info.chili.email.domain.EmailPreferenceRule;
import info.chili.service.jrs.types.Entry;
import info.chili.spring.SpringContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.cache.annotation.CacheEvict;
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
    @CacheEvict(value = EmailPreferenceRule.EMAIL_PREF_RULE_CACHE_REGION, allEntries = true)
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    @CacheEvict(value = EmailPreferenceRule.EMAIL_PREF_RULE_CACHE_REGION, allEntries = true)
    public EmailPreferenceRule save(EmailPreferenceRule entity) {
        entity = em.merge(entity);
        return entity;
    }

    @Cacheable(value = EmailPreferenceRule.EMAIL_PREF_RULE_CACHE_REGION, key = "{#root.methodName,#id}")
    public EmailPreferenceRule find(String id) {
        TypedQuery<EmailPreferenceRule> query = em.createQuery("from " + EmailPreferenceRule.class.getCanonicalName() + " where ruleId=:ruleIdParam", EmailPreferenceRule.class);
        query.setParameter("ruleIdParam", id);
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }

    @Cacheable(value = EmailPreferenceRule.EMAIL_PREF_RULE_CACHE_REGION, key = "{#root.methodName}")
    public List<Entry> getDropDown() {
        List<Entry> result = new ArrayList<>();
        String[] columns = {"ruleId", "name"};
        Map<String, String> values = getEntityStringMapByParams(0, 1000, columns);
        for (String key : values.keySet()) {
            result.add(new Entry(key, values.get(key)));
        }
        return result;
    }

    public static EmailPreferenceRuleDao instance() {
        return (EmailPreferenceRuleDao) SpringContext.getBean(EmailPreferenceRuleDao.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
