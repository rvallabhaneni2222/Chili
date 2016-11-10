package info.chili.commons.jrs;

import info.chili.document.SerializedEntity;
import info.chili.document.dao.SerializedEntityDao;
import info.chili.jpa.validation.Validate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ayalamanchili
 */
@Path("secured/chili/serialized-entities")
@Component
@Scope("request")
@Produces("application/json")
@Consumes("application/json")
public class SerializedEntityResource {

    @Autowired
    public SerializedEntityDao serializedEntityDao;

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected Mapper mapper;

    @PUT
    @Validate
    public void save(@QueryParam("className") String className, String entity) {
        serializedEntityDao.save(className, entity);
    }

    @GET
    @Path("/find")
    public SerializedEntity find(@QueryParam("className") String className, @QueryParam("targetClassName") String targetClassName, @QueryParam("targetInstanceId") String targetInstanceId) {
        return serializedEntityDao.find(className, targetClassName, targetInstanceId);
    }

    @GET
    @Path("/find-all")
    public Response findAll(@QueryParam("className") String className, @QueryParam("targetClassName") String targetClassName, @QueryParam("targetInstanceId") String targetInstanceId) {
        return serializedEntityDao.findAll(className, targetClassName, targetInstanceId);
    }

}
