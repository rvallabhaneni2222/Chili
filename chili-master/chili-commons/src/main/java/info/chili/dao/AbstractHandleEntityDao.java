/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.dao;

import info.chili.jpa.AbstractEntity;
import info.chili.jpa.AbstractHandleEntity;
import info.chili.jpa.validation.Validate;
import info.chili.service.jrs.exception.ServiceException;
import info.chili.spring.SpringContext;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ayalamanchili
 * @param <T>
 */
@Repository
@Scope("prototype")
//TODO should this inherit from crud dao?
public abstract class AbstractHandleEntityDao<T extends AbstractHandleEntity> {

    private static final Log log = LogFactory.getLog(AbstractHandleEntityDao.class);
    protected Class entityCls;

    public AbstractHandleEntityDao(Class cls) {
        this.entityCls = cls;
    }

    public T clone(Long id) {
        try {
            T clone = (T) entityCls.newInstance();
            String[] ignore = {"id", "version"};
            BeanUtils.copyProperties(findById(id), clone, ignore);
            return clone;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public T save(T entity) {
        return getEntityManager().merge(entity);
    }

    @Transactional(readOnly = true)
    public T find(Long id) {
        return (T) getEntityManager().find(entityCls, id);
    }

    @Validate
    @Transactional(readOnly = true)
    public T save(T entity, Long id, String targetClassName) {
        entity.setTargetEntityName(targetClassName);
        entity.setTargetEntityId(id);
        //TODO use bean mapper for merge
        return (T) getEntityManager().merge(entity);
    }

    @Validate
    public T save(T entity, AbstractEntity target) {
        if (target.getId() == null) {
            throw new RuntimeException("target id cannot be null");
        }
        entity.setTargetEntityName(target.getClass().getCanonicalName());
        entity.setTargetEntityId(target.getId());
        //TODO use bean mapper for merge
        return (T) getEntityManager().merge(entity);
    }

    @Validate
    public T save(T entity, AbstractEntity source, AbstractEntity target) {
        if (source.getId() == null) {
            throw new RuntimeException("source id cannot be null");
        }
        if (target.getId() == null) {
            throw new RuntimeException("target id cannot be null");
        }
        entity.setSourceEntityName(source.getClass().getCanonicalName());
        entity.setSourceEntityId(source.getId());
        entity.setTargetEntityName(target.getClass().getCanonicalName());
        entity.setTargetEntityId(target.getId());
        //TODO use bean mapper for merge
        return (T) getEntityManager().merge(entity);
    }

    @Transactional(readOnly = true)
    public T find(AbstractEntity target) {
        TypedQuery<T> query = getEntityManager().createQuery("from " + entityCls.getCanonicalName() + " where targetEntityName=:targetEntityNameParam and targetEntityId=:targetEntityIdParam", entityCls);
        query.setParameter("targetEntityNameParam", target.getClass().getCanonicalName());
        query.setParameter("targetEntityIdParam", target.getId());
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            //TODO throw exception
            return null;
        }
    }

    @Transactional(readOnly = true)
    public T find(AbstractEntity source, AbstractEntity target) {
        TypedQuery<T> query = getEntityManager().createQuery("from " + entityCls.getCanonicalName() + " where sourceEntityName=:sourceEntityNameParam and sourceEntityId=:sourceEntityIdParam and targetEntityName=:targetEntityNameParam and targetEntityId=:targetEntityIdParam", entityCls);
        query.setParameter("sourceEntityNameParam", source.getClass().getCanonicalName());
        query.setParameter("sourceEntityIdParam", source.getId());
        query.setParameter("targetEntityNameParam", target.getClass().getCanonicalName());
        query.setParameter("targetEntityIdParam", target.getId());
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            //TODO throw exception
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Long id, String targetClassName) {
        TypedQuery<T> query = getEntityManager().createQuery("from " + entityCls.getCanonicalName() + " where targetEntityName=:targetEntityNameParam and targetEntityId=:targetEntityIdParam", entityCls);
        query.setParameter("targetEntityNameParam", targetClassName);
        query.setParameter("targetEntityIdParam", id);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public AbstractEntity find(Long id, String targetClassName) {
        TypedQuery<AbstractEntity> query = getEntityManager().createQuery("from " + entityCls.getCanonicalName() + " where targetEntityName=:targetEntityNameParam and targetEntityId=:targetEntityIdParam", AbstractEntity.class);
        query.setParameter("targetEntityNameParam", targetClassName);
        query.setParameter("targetEntityIdParam", id);
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            //TODO throw exception
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<T> findAll(AbstractEntity source, AbstractEntity target) {
        TypedQuery<T> query = getEntityManager().createQuery("from " + entityCls.getCanonicalName() + " where sourceEntityName=:sourceEntityNameParam and sourceEntityId=:sourceEntityIdParam and targetEntityName=:targetEntityNameParam and targetEntityId=:targetEntityIdParam", entityCls);
        query.setParameter("sourceEntityNameParam", source.getClass().getCanonicalName());
        query.setParameter("sourceEntityIdParam", source.getId());
        query.setParameter("targetEntityNameParam", target.getClass().getCanonicalName());
        query.setParameter("targetEntityIdParam", target.getId());
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<T> query(int start, int limit) {
        Query findAllQuery = getEntityManager().createQuery("from " + entityCls.getCanonicalName(), entityCls);
        findAllQuery.setFirstResult(start);
        findAllQuery.setMaxResults(limit);
        return findAllQuery.getResultList();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Long size() {
        Query sizeQuery = getEntityManager().createQuery("select count (*) from " + entityCls.getCanonicalName());
        return (Long) sizeQuery.getSingleResult();
    }

    @Transactional(readOnly = true)
    public T findById(Long id) {
        return (T) getEntityManager().find(entityCls, id);
    }

    public void delete(Long id) {
        delete(findById(id));
    }

    public void delete(T entity) {
        try {
            getEntityManager().remove(entity);
            getEntityManager().flush();
        } catch (javax.persistence.PersistenceException e) {
            throw new ServiceException(ServiceException.StatusCode.INVALID_REQUEST, "DELETE", "SQLError", e.getMessage());
        }
    }

    public abstract EntityManager getEntityManager();

    public static AbstractHandleEntityDao instance() {
        return SpringContext.getBean(AbstractHandleEntityDao.class);
    }
}
