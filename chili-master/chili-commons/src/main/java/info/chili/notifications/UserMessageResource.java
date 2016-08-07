/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import info.chili.security.SecurityService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Path("secured/notifications/user")
@Component
@Scope("request")
@Produces("application/json")
@Consumes("application/json")
public class UserMessageResource {

    @Autowired
    protected UserMessageService userMessageService;

    @GET
    @Path("/messages/{start}/{limit}")
    public List<UserMessage> messages(@PathParam("start") int start, @PathParam("limit") int limit) {
        return userMessageService.getMessages();
    }
}
