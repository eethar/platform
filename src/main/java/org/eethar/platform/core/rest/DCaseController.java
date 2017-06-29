package org.eethar.platform.core.rest;

import org.eethar.platform.core.entity.DCase;
import org.eethar.platform.core.service.facade.DCaseFacade;
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
 * REST controller for managing DCase.
 */
@Api(value = "/api/d-case", description = "D Case Controller")
@Path("/api/d-case")
@Secured
public class DCaseController {

    @Inject
    private Logger log;

    @Inject
    private DCaseFacade DCaseFacade;

    /**
     * POST : Create a new DCase.
     *
     * @param DCase the DCase to create
     * @return the Response with status 201 (Created) and with body the new
     * DCase, or with status 400 (Bad Request) if the DCase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "create a new DCase", notes = "Create a new DCase")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createDCase(DCase dCase) throws URISyntaxException {
        log.debug("REST request to save DCase : {}", dCase);
        DCaseFacade.getRepo().save(dCase);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/d-case/" + dCase.getId())),
                "DCase", dCase.getId().toString())
                .entity(dCase).build();
    }

    /**
     * PUT : Updates an existing DCase.
     *
     * @param dCase the DCase to update
     * @return the Response with status 200 (OK) and with body the updated
     * DCase, or with status 400 (Bad Request) if the DCase is not valid, or
     * with status 500 (Internal Server Error) if the DCase couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "update DCase", notes = "Updates an existing DCase")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateDCase(DCase dCase) throws URISyntaxException {
        log.debug("REST request to update DCase : {}", dCase);
        DCaseFacade.getRepo().save(dCase);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "DCase", dCase.getId().toString())
                .entity(dCase).build();
    }

    /**
     * GET : get all the DCases.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of DCases in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @ApiOperation(value = "get all the DCases")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllDCases(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all DCases");
        List<DCase> DCases = DCaseFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(DCases);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, DCaseFacade.getRepo().countAll().intValue()), "/resources/api/d-case");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" DCase.
     *
     * @param id the id of the DCase to retrieve
     * @return the Response with status 200 (OK) and with body the DCase, or
     * with status 404 (Not Found)
     */
    @ApiOperation(value = "get the DCase")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getDCase(@PathParam("id") Long id) {
        log.debug("REST request to get DCase : {}", id);
        DCase dCase = DCaseFacade.getRepo().findBy(id);
        return Optional.ofNullable(dCase)
                .map(result -> Response.status(Response.Status.OK).entity(dCase).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" DCase.
     *
     * @param id the id of the DCase to delete
     * @return the Response with status 200 (OK)
     */
    @ApiOperation(value = "remove the DCase")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeDCase(@PathParam("id") Long id) {
        log.debug("REST request to delete DCase : {}", id);
        DCaseFacade.getRepo().remove(DCaseFacade.getRepo().findBy(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "DCase", id.toString()).build();
    }

}
