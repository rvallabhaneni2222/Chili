/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.email.dao;

import info.chili.dao.CRUDDao;
import info.chili.email.domain.EmailPreferenceRule;
import info.chili.email.domain.UserEmailPreferenceRule;
import info.chili.spring.SpringContext;
import java.util.List;
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
public class UserEmailPreferenceRuleDao extends CRUDDao<UserEmailPreferenceRule> {

    @PersistenceContext
    protected EntityManager em;

    public UserEmailPreferenceRuleDao() {
        super(UserEmailPreferenceRule.class);
    }

    public UserEmailPreferenceRuleDao(Class cls) {
        super(cls);
    }

    @CacheEvict(value = EmailPreferenceRule.EMAIL_PREF_RULE_CACHE_REGION, allEntries = true)
    public UserEmailPreferenceRule save(String userId, String ruleId) {
        UserEmailPreferenceRule entity = new UserEmailPreferenceRule();
        entity.setUserId(userId);
        entity.setEmailPreferenceRuleId(EmailPreferenceRuleDao.instance().find(ruleId));
        return em.merge(entity);
    }

    @Cacheable(EmailPreferenceRule.EMAIL_PREF_RULE_CACHE_REGION)
    public List<UserEmailPreferenceRule> findRulesForUser(String userId) {
        TypedQuery<UserEmailPreferenceRule> query = em.createQuery("from " + UserEmailPreferenceRule.class.getCanonicalName() + " where userId=:userIdParam", UserEmailPreferenceRule.class);
        query.setParameter("userIdParam", userId);
        return query.getResultList();
    }

    @Cacheable(EmailPreferenceRule.EMAIL_PREF_RULE_CACHE_REGION)
    public UserEmailPreferenceRule findRulesForUser(String userId, String ruleId) {
        TypedQuery<UserEmailPreferenceRule> query = em.createQuery("from " + UserEmailPreferenceRule.class.getCanonicalName() + " where userId=:userIdParam and ruleId=:ruleIdParam", UserEmailPreferenceRule.class);
        query.setParameter("ruleIdParam", ruleId);
        query.setParameter("userIdParam", userId);
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }

    public static UserEmailPreferenceRuleDao instance() {
        return (UserEmailPreferenceRuleDao) SpringContext.getBean(UserEmailPreferenceRuleDao.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
