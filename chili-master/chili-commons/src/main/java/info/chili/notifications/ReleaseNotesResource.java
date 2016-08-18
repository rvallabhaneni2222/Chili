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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @Path("/{id}")
    @GET
    public ReleaseNotes read(@PathParam("id") String id) {
        return releaseNotesDao.findById(id);
    }

    @PUT
    @Path("/delete/{id}")
    @Validate
    public void delete(@PathParam("id") String id) {
        releaseNotesDao.delete(id);
    }

    @GET
    @Path("/{start}/{limit}")
    public ReleaseNotesService.ReleaseNotesTable Notes(@PathParam("start") int start, @PathParam("limit") int limit) {
        return releaseNotesService.getReleaseNotes(start, limit);
    }
}
