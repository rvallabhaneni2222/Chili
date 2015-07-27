/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.email.dao;

import com.google.common.base.Strings;
import info.chili.dao.CRUDDao;
import info.chili.email.CEmail;
import info.chili.email.domain.EmailPreferenceRule;
import info.chili.email.domain.UserEmailPreferenceRule;
import info.chili.spring.SpringContext;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    @Caching(evict = {
        @CacheEvict(value = UserEmailPreferenceRule.USER_EMAIL_PREF_RULE_CACHE_REGION, allEntries = true),
        @CacheEvict(value = CEmail.EMAILS_CACHE_KEY, allEntries = true)
    })
    public void delete(Long id) {
        super.delete(id);
    }

    @Caching(evict = {
        @CacheEvict(value = UserEmailPreferenceRule.USER_EMAIL_PREF_RULE_CACHE_REGION, allEntries = true),
        @CacheEvict(value = CEmail.EMAILS_CACHE_KEY, allEntries = true)
    })
    public UserEmailPreferenceRule save(String userId, String ruleId) {
        UserEmailPreferenceRule entity = new UserEmailPreferenceRule();
        if (Strings.isNullOrEmpty(userId)) {
            entity.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        } else {
            entity.setUserId(userId);
        }
        if (findRuleForUser(entity.getUserId(), ruleId) != null) {
            return findRuleForUser(entity.getUserId(), ruleId);
        }
        entity.setEmailPreferenceRule(EmailPreferenceRuleDao.instance().find(ruleId));
        return em.merge(entity);
    }

    @Autowired
    protected Mapper mapper;

    @Cacheable(value = UserEmailPreferenceRule.USER_EMAIL_PREF_RULE_CACHE_REGION, key = "{#root.methodName,#userId}")
    public List<UserEmailPreferenceRuleDto> findRulesForUser(String userId) {
        List<UserEmailPreferenceRuleDto> res = new ArrayList();
        TypedQuery<UserEmailPreferenceRule> query = em.createQuery("from " + UserEmailPreferenceRule.class.getCanonicalName() + " where userId=:userIdParam", UserEmailPreferenceRule.class);
        query.setParameter("userIdParam", userId);
        for (UserEmailPreferenceRule entity : query.getResultList()) {
            UserEmailPreferenceRuleDto obj = mapper.map(entity.getEmailPreferenceRule(), UserEmailPreferenceRuleDto.class);
            obj.setId(entity.getId());
            res.add(obj);

        }
        return res;
    }

    @Cacheable(value = UserEmailPreferenceRule.USER_EMAIL_PREF_RULE_CACHE_REGION, key = "{#root.methodName,#userId,#ruleId}")
    public UserEmailPreferenceRule findRuleForUser(String userId, String ruleId) {
        TypedQuery<UserEmailPreferenceRule> query = em.createQuery("from " + UserEmailPreferenceRule.class.getCanonicalName() + " where userId=:userIdParam and emailPreferenceRule.ruleId=:ruleIdParam", UserEmailPreferenceRule.class);
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

    @XmlRootElement
    @XmlType
    public static class UserEmailPreferenceRuleDto extends EmailPreferenceRule {

        private Long id;

        @Override
        public Long getId() {
            return id;
        }

        @XmlElement
        @Override
        public void setId(Long id) {
            this.id = id;
        }
    }

}
