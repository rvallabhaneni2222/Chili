/**
 * System Soft Technologies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.notifications;

import info.chili.jpa.validation.Validate;
import info.chili.notifications.ReleaseNotes;
import info.chili.notifications.ReleaseNotesDao;
import info.chili.notifications.ReleaseNotesService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sudharani.bandaru
 */
@Path("secured/releaseNotes")
@Component
@Scope("request")
@Produces("application/json")
@Consumes("application/json")
@Transactional
public class ReleaseNotesResource {
    
    @Autowired
    protected ReleaseNotesService releaseNotesService;
    @Autowired
    protected ReleaseNotesDao releaseNotesDao;

    @PUT
    @Validate
    @Path("/save")
    public void save(ReleaseNotes entity) {
        releaseNotesDao.save(entity);
    }

    @Path("/{userIds}")
    @GET
    public ReleaseNotes read(@PathParam("userIds") String userIds) {
        return releaseNotesDao.findById(userIds);
    }

    @PUT
    @Path("/delete/{userIds}")
    @Validate
    public void delete(@PathParam("userIds") String userIds) {
        releaseNotesDao.delete(userIds);
    }

    @GET
    @Path("/{start}/{limit}")
    public ReleaseNotesService.ReleaseNotesTable Notes(@PathParam("start") int start, @PathParam("limit") int limit) {
        return releaseNotesService.getReleaseNotes(start, limit);
    }
}


