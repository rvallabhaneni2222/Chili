/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.i18n.dao;

import info.chili.i18n.domain.Ci18nResourceBundle;
import info.chili.i18n.domain.Ci18nResourceBundle.CResourceBundleTable;
import info.chili.spring.SpringContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayalamanchili
 */
@Repository
@Scope("prototype")
public class Ci18nDao {

    @PersistenceContext
    protected EntityManager em;

    public void save(Ci18nResourceBundle bundle) {
        em.merge(bundle);
    }

    public CResourceBundleTable queryAll(int start, int limit) {
        CResourceBundleTable res = new CResourceBundleTable();
        Query findAllQuery = em.createQuery("from " + Ci18nResourceBundle.class.getCanonicalName(), Ci18nResourceBundle.class);
        findAllQuery.setFirstResult(start);
        findAllQuery.setMaxResults(limit);
        res.setEntities(findAllQuery.getResultList());
        res.setSize(new Integer(findAllQuery.getResultList().size()).longValue());
        return res;
    }

    public static Ci18nDao instance() {
        return SpringContext.getBean(Ci18nDao.class);
    }

}
