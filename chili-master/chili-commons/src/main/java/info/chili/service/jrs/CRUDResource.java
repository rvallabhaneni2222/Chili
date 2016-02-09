/**
 * System Soft Technolgies Copyright (C) 2013 ayalamanchili@sstech.mobi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.service.jrs;

import info.chili.commons.pdf.PDFUtils;
import info.chili.service.jrs.types.Entry;
import info.chili.dao.CRUDDao;
import info.chili.docs.MakeHTML;
import info.chili.jpa.validation.Validate;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ayalamanchili
 */
@Produces("application/json")
@Consumes("application/json")
@Component
@Transactional
@Scope("request")
public abstract class CRUDResource<T> {

    @GET
    @Path("/{id}")
    @Transactional(readOnly = true)
    public T read(@PathParam("id") Long id) {
        return (T) getDao().findById(id);
    }

    @GET
    @Path("/clone/{id}")
    protected T clone(@PathParam("id") Long id) {
        return (T) getDao().clone(id);
    }

    @PUT
    @Validate
    public T save(T entity) {
        return (T) getDao().save(entity);
    }

    @PUT
    @Path("/delete/{id}")
    public void delete(@PathParam("id") Long id) {
        getDao().delete(id);
    }

    @PUT
    @Path("/validate")
    public void validate(T entity) {
        getDao().validate(entity);
    }

    @GET
    @Path("/dropdown/{start}/{limit}")
    @Transactional(propagation = Propagation.NEVER)
    public List<Entry> getDropDown(@PathParam("start") int start, @PathParam("limit") int limit,
            @QueryParam("column") List<String> columns) {
        List<Entry> result = new ArrayList<Entry>();
        Map<String, String> values = getDao().getEntityStringMapByParams(start, limit, columns.toArray(new String[columns.size()]));
        for (Map.Entry<String, String> entry : values.entrySet()) {
            result.add(new Entry(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    @GET
    @Path("/size")
    @Transactional(propagation = Propagation.NEVER)
    public long size() {
        return getDao().size();
    }

    @GET
    @Path("/search/{searchText}/{start}/{limit}")
    @Transactional(propagation = Propagation.NEVER)
    public List<T> search(@PathParam("searchText") String searchText, @PathParam("start") int start,
            @PathParam("limit") int limit, @QueryParam("column") List<String> columns) {
        return getDao().search(searchText, start, limit, columns, false, true);
    }

    @GET
    @Path("/search_size/{searchText}")
    @Transactional(propagation = Propagation.NEVER)
    public String searchSize(@PathParam("searchText") String searchText) {
        return getDao().searchSize(searchText).toString();
    }

    @PUT
    @Path("/search/{start}/{limit}")
    @Transactional(propagation = Propagation.NEVER)
    public List<T> search(T entity, @PathParam("start") int start, @PathParam("limit") int limit) {
        return getDao().search(entity, start, limit);
    }

    @PUT
    @Path("/search_size")
    @Transactional(propagation = Propagation.NEVER)
    public String searchSize(T entity) {
        return getDao().searchSize(entity).toString();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/print")
    @Produces({"application/pdf"})
    public Response print(@QueryParam("id") Long id) {
        if (getDao().findById(id) != null) {
            String report = MakeHTML.makeHTML(getDao().findById(id)).replaceAll("<null>", "");
            byte[] pdf = PDFUtils.convertToPDF(report);
            return Response.ok(pdf)
                    .header("content-disposition", "filename = print.pdf")
                    .header("Content-Length", pdf)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public abstract CRUDDao getDao();
}
