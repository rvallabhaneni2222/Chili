/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.identity.service;

import info.chili.security.domain.CUser;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Component
@Scope("prototype")
public class IdentityService {

    @PersistenceContext
    protected EntityManager em;

    public CUser createUser(CUser user) {
        return em.merge(user);
    }
}
