/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.identity.jrs;

import info.chili.identity.service.IdentityService;
import info.chili.security.domain.CUser;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ayalamanchili
 */
@Path(IdentityResource.PATH)
@Produces("application/json")
@Consumes("application/json")
@Component
@Transactional
@Scope("request")
public class IdentityResource {

    public static final String PATH = "secured/identity";
    public static final String CREATE_USER = "/user";

    @Autowired
    protected IdentityService identityService;

    @POST
    @Path(IdentityResource.CREATE_USER)
    public CUser createUser(CUser user) {
        return identityService.createUser(user);
    }
}
