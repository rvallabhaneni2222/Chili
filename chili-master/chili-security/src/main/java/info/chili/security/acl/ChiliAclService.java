/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.acl;

import info.chili.security.SecurityUtils;
import info.chili.spring.SpringContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Component
@Scope("request")
public class ChiliAclService {

    @Autowired
    JdbcMutableAclService aclSercice;

    public JdbcMutableAclService getAclService() {
        return aclSercice;
    }
//TODO add sid to input

    public void addBasicPermissions(String className, Long id, String... permissions) {
        //get mutable ACL
        MutableAcl acl = findAcl(className, id);
        //Set SID
        Sid sid = new PrincipalSid(SecurityUtils.getCurrentUser());
        acl.setOwner(sid);

        for (String permString : permissions) {
            Permission p = mapPermisson(permString);
            if (p != null) {
                acl.insertAce(acl.getEntries().size(), p, sid, true);
                aclSercice.updateAcl(acl);
            }
        }
    }

    public void removePermissions(String className, Long id, List<String> permissions) {
        //get mutable ACL
        MutableAcl acl = findAcl(className, id);
        //Set SID
        Sid sid = new PrincipalSid(SecurityUtils.getCurrentUser());
        acl.setOwner(sid);

        for (String permString : permissions) {
            Permission p = mapPermisson(permString);
            if (p != null) {
                int i = 0;
                for (AccessControlEntry ace : acl.getEntries()) {
                    if (ace.getPermission().getMask() == p.getMask()) {
                        acl.deleteAce(i);
                        aclSercice.updateAcl(acl);
                        i++;
                    }
                }

            }
        }
    }

    private MutableAcl findAcl(String className, Long id) {
        Class entityCls;
        try {
            entityCls = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            //TODO throw service exception with code 400 (invalid request)
            throw new RuntimeException(ex);
        }
        ObjectIdentity oi = new ObjectIdentityImpl(entityCls, id);
        try {
            return (MutableAcl) aclSercice.readAclById(oi);
        } catch (NotFoundException nfe) {
            return aclSercice.createAcl(oi);
        }
    }

    private Permission mapPermisson(String prmsString) {
        if (!prmsString.isEmpty()) {
            if (prmsString.trim().equalsIgnoreCase("READ")) {
                return BasePermission.READ;
            }
            if (prmsString.trim().equalsIgnoreCase("UPDATE")) {
                return BasePermission.WRITE;
            }
            if (prmsString.trim().equalsIgnoreCase("CREATE")) {
                return BasePermission.CREATE;
            }
            if (prmsString.trim().equalsIgnoreCase("DELETE")) {
                return BasePermission.DELETE;
            }
            if (prmsString.trim().equalsIgnoreCase("ADMIN")) {
                return BasePermission.ADMINISTRATION;
            }
        }
        return null;
    }

    public static ChiliAclService instance() {
        return SpringContext.getBean(ChiliAclService.class);
    }
}
