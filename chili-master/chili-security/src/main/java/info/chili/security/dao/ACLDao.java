/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.dao;

import info.chili.commons.EntityQueryUtils;
import info.chili.security.domain.acl.AclSid;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anuyalamanchili
 */
@Repository
@Scope("prototype")
public class ACLDao {

    @PersistenceContext
    protected EntityManager em;

    public void createSid(String sid) {
        AclSid aclSid = EntityQueryUtils.findEntity(em, AclSid.class, "sid", sid);
        if (aclSid == null) {
            AclSid newAclSid = new AclSid();
            newAclSid.setSid(sid);
            newAclSid.setPrincipal(true);
            em.persist(newAclSid);
        }
    }
}
