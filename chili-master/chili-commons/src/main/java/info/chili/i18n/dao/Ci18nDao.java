/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.i18n.dao;

import info.chili.i18n.domain.Ci18nResource;
import info.chili.i18n.domain.Ci18nResource.Ci18nResourceTable;
import info.chili.i18n.domain.Ci18nResourceBundle;
import info.chili.i18n.domain.Ci18nResourceBundle.CResourceBundleTable;
import info.chili.spring.SpringContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

    public void saveBundle(Ci18nResourceBundle bundle) {
        em.merge(bundle);
    }

    public CResourceBundleTable getBundles(int start, int limit) {
        CResourceBundleTable res = new CResourceBundleTable();
        Query findAllQuery = em.createQuery("from " + Ci18nResourceBundle.class.getCanonicalName(), Ci18nResourceBundle.class);
        findAllQuery.setFirstResult(start);
        findAllQuery.setMaxResults(limit);
        res.setEntities(findAllQuery.getResultList());
        res.setSize(new Integer(findAllQuery.getResultList().size()).longValue());
        return res;
    }

    public void addResource(Integer bundleId, Ci18nResource resource) {
        Ci18nResourceBundle bundle = em.find(Ci18nResourceBundle.class, bundleId);
        resource.setResourceBundle(bundle);
        em.merge(resource);
    }

    public void updateResource(Ci18nResource resource) {
        em.merge(resource);
    }

    public Ci18nResourceTable getResources(Integer bundleId, Integer start, Integer limit) {
        Ci18nResourceTable res = new Ci18nResourceTable();
        TypedQuery<Ci18nResource> entitiesQ = em.createQuery("from " + Ci18nResource.class.getCanonicalName() + " where resourceBundle.id=:bundleIdParam", Ci18nResource.class);
        entitiesQ.setParameter("bundleIdParam", bundleId);
        entitiesQ.setFirstResult(start);
        entitiesQ.setMaxResults(limit);
        res.setEntities(entitiesQ.getResultList());
        TypedQuery<Long> sizeQ = em.createQuery("select count(*) from " + Ci18nResource.class.getCanonicalName() + " where resourceBundle.id=:bundleIdParam", Long.class);
        sizeQ.setParameter("bundleIdParam", bundleId);
        res.setSize(sizeQ.getSingleResult());
        return res;
    }

    public static Ci18nDao instance() {
        return SpringContext.getBean(Ci18nDao.class);
    }

}
