/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.analytics.jrs;

import info.chili.analytics.service.EmailEventsService;
import info.chili.email.Email;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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

    @GET
    @Path("/{start}/{limit}")
    public EmailEventsTable events(@PathParam("start") int start, @PathParam("limit") int limit) {
        return emailEventsService.getEvents(start, limit);
    }

    @XmlRootElement
    @XmlType
    public static class EmailEventsTable implements java.io.Serializable {

        protected Long size;
        protected List<Email> entities;

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        @XmlElement
        public List<Email> getEntities() {
            return entities;
        }

        public void setEntities(List<Email> entities) {
            this.entities = entities;
        }
    }

}
