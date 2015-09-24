/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.dao;

import info.chili.security.SecurityUtils;
import info.chili.security.domain.CIPAddress;
import info.chili.security.domain.CIPAddressType;
import info.chili.security.domain.CUserIPAddress;
import info.chili.security.domain.IPAddressRecord;
import info.chili.spring.SpringContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayalamanchili
 */
@Repository("cIPAddressDao")
@Scope("prototype")
public class CIPAddressDao {
    
    @PersistenceContext
    protected EntityManager em;
    
    @Autowired
    protected MongoOperations mongoTemplate;
    
    public void addUserIPAddress(String userId, String ipAddress) {
        CUserIPAddress userip = new CUserIPAddress();
        userip.setUserId(userId);
        userip.setIpAddress(ipAddress);
        em.merge(userip);
    }
    
    public void addIPAddress(CIPAddress ipAddress) {
        em.merge(ipAddress);
    }
    
    public boolean isValidIP(String ipAddress) {
        IPAddressRecord rec = new IPAddressRecord();
        rec.setIpAddress(ipAddress);
        rec.setUserId(SecurityUtils.getCurrentUser());
        mongoTemplate.save(rec);
        if (isValidIP(ipAddress, CIPAddressType.GLOBAL_VALIDATED)) {
            return true;
        } else if (isValidUserIP(ipAddress)) {
            return true;
        }
        return false;
    }
    
    protected boolean isValidUserIP(String userId, String ipAddress) {
        TypedQuery<Long> qry = em.createQuery("select count(*) from " + CUserIPAddress.class.getCanonicalName() + " where userId=:userIdParam and ipAddress=:ipAddressParam", Long.class);
        qry.setParameter("userIdParam", userId);
        qry.setParameter("ipAddressParam", ipAddress);
        return qry.getSingleResult() > 0;
    }
    
    protected boolean isValidUserIP(String ipAddress) {
        TypedQuery<Long> qry = em.createQuery("select count(*) from " + CUserIPAddress.class.getCanonicalName() + " where userId=:userIdParam and ipAddress=:ipAddressParam", Long.class);
        qry.setParameter("userIdParam", SecurityUtils.getCurrentUser());
        qry.setParameter("ipAddressParam", ipAddress);
        return qry.getSingleResult() > 0;
    }
    
    protected boolean isValidIP(String ipAddress, CIPAddressType type) {
        TypedQuery<Long> qry = em.createQuery("select count(*) from " + CIPAddress.class.getCanonicalName() + " where ipAddress=:ipAddressParam and addressType=:typeParam", Long.class);
        qry.setParameter("ipAddressParam", ipAddress);
        qry.setParameter("typeParam", type);
        return qry.getSingleResult() > 0;
    }
    
    public static CIPAddressDao instance() {
        return (CIPAddressDao) SpringContext.getBean("cIPAddressDao");
    }
    
}
