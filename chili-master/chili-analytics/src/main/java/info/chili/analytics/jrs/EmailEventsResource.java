/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.analytics.jrs;

import info.chili.analytics.service.EmailEventsService;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Path("secured/analytics/email")
@Component
@Scope("request")
@Produces("application/json")
@Consumes("application/json")
public class EmailEventsResource {
    
    @Autowired
    protected EmailEventsService emailEventsService;
}
