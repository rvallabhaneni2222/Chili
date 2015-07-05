/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.bpm.dao;

import com.google.gson.Gson;
import info.chili.bpm.domain.BPMTaskDelegateRule;
import info.chili.dao.CRUDDao;
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
@Repository("bpmTaskDelegateRuleDao")
@Scope("prototype")
public class BPMTaskDelegateRuleDao extends CRUDDao<BPMTaskDelegateRule> {

    @PersistenceContext
    protected EntityManager em;

    public BPMTaskDelegateRuleDao() {
        super(BPMTaskDelegateRule.class);
    }

    public BPMTaskDelegateRuleDao(Class cls) {
        super(cls);
    }

    @Override
    public BPMTaskDelegateRule save(BPMTaskDelegateRule entity) {
        Gson gson = new Gson();
        entity.setAttributeData(gson.toJson(entity.getAttributes()));
        entity = em.merge(entity);
        return entity;
    }

    @Cacheable(BPMTaskDelegateRule.BPM_TASK_DELEGATE_RULE_CACHE_REGION)
    public BPMTaskDelegateRule find(String processId, String taskId) {
        TypedQuery<BPMTaskDelegateRule> query = em.createQuery("from " + BPMTaskDelegateRule.class.getCanonicalName() + " where bpmProcessId=:processIdParam and bpmTaskId=:taskIdParam", BPMTaskDelegateRule.class);
        query.setParameter("processIdParam", processId);
        query.setParameter("taskIdParam", taskId);
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }

    public static BPMTaskDelegateRuleDao instance() {
        return (BPMTaskDelegateRuleDao) SpringContext.getBean("bpmTaskDelegateRuleDao");
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
