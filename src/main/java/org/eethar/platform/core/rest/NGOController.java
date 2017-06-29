package org.eethar.platform.core.rest;

import org.eethar.platform.core.entity.NGO;
import org.eethar.platform.core.service.facade.NGOFacade;
import org.eethar.platform.core.rest.util.HeaderUtil;
import org.eethar.platform.app.security.Secured;
import org.slf4j.Logger;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.eethar.platform.core.rest.util.Page;
import org.eethar.platform.core.rest.util.PaginationUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * REST controller for managing NGO.
 */
@Api(value = "/api/ngo", description = "NGO Controller")
@Path("/api/ngo")
@Secured
public class NGOController {

    @Inject
    private Logger log;

    @Inject
    private NGOFacade NGOFacade;

    /**
     * POST : Create a new NGO.
     *
     * @param NGO the NGO to create
     * @return the Response with status 201 (Created) and with body the new NGO,
     * or with status 400 (Bad Request) if the NGO has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "create a new NGO", notes = "Create a new NGO")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createNGO(NGO NGO) throws URISyntaxException {
        log.debug("REST request to save NGO : {}", NGO);
        NGOFacade.getRepo().save(NGO);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/ngo/" + NGO.getId())),
                "NGO", NGO.getId().toString())
                .entity(NGO).build();
    }

    /**
     * PUT : Updates an existing NGO.
     *
     * @param ngo the NGO to update
     * @return the Response with status 200 (OK) and with body the updated NGO,
     * or with status 400 (Bad Request) if the NGO is not valid, or with status
     * 500 (Internal Server Error) if the NGO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "update NGO", notes = "Updates an existing NGO")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateNGO(NGO ngo) throws URISyntaxException {
        log.debug("REST request to update NGO : {}", ngo);
        NGOFacade.getRepo().save(ngo);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "NGO", ngo.getId().toString())
                .entity(ngo).build();
    }

    /**
     * GET : get all the NGOes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of NGOes in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @ApiOperation(value = "get all the NGOes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllNGOes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all NGOes");
        List<NGO> NGOes = NGOFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(NGOes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, NGOFacade.count().intValue()), "/resources/api/ngo");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" NGO.
     *
     * @param id the id of the NGO to retrieve
     * @return the Response with status 200 (OK) and with body the NGO, or with
     * status 404 (Not Found)
     */
    @ApiOperation(value = "get the NGO")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getNGO(@PathParam("id") Long id) {
        log.debug("REST request to get NGO : {}", id);
        NGO ngo = NGOFacade.getRepo().findBy(id);
        return Optional.ofNullable(ngo)
                .map(result -> Response.status(Response.Status.OK).entity(ngo).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" NGO.
     *
     * @param id the id of the NGO to delete
     * @return the Response with status 200 (OK)
     */
    @ApiOperation(value = "remove the NGO")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeNGO(@PathParam("id") Long id) {
        log.debug("REST request to delete NGO : {}", id);
        NGOFacade.getRepo().remove(NGOFacade.getRepo().findBy(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "NGO", id.toString()).build();
    }

}
