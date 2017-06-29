package org.eethar.platform.core.rest;

import org.eethar.platform.core.entity.Donation;
import org.eethar.platform.core.service.facade.DonationFacade;
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
 * REST controller for managing Donation.
 */
@Api(value = "/api/donation", description = "Donation Controller")
@Path("/api/donation")
@Secured
public class DonationController {

    @Inject
    private Logger log;

    @Inject
    private DonationFacade donationFacade;

    /**
     * POST : Create a new donation.
     *
     * @param donation the donation to create
     * @return the Response with status 201 (Created) and with body the new
     * donation, or with status 400 (Bad Request) if the donation has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "create a new donation", notes = "Create a new donation")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createDonation(Donation donation) throws URISyntaxException {
        log.debug("REST request to save Donation : {}", donation);
        donationFacade.getRepo().save(donation);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/donation/" + donation.getId())),
                "donation", donation.getId().toString())
                .entity(donation).build();
    }

    /**
     * PUT : Updates an existing donation.
     *
     * @param donation the donation to update
     * @return the Response with status 200 (OK) and with body the updated
     * donation, or with status 400 (Bad Request) if the donation is not valid,
     * or with status 500 (Internal Server Error) if the donation couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "update donation", notes = "Updates an existing donation")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateDonation(Donation donation) throws URISyntaxException {
        log.debug("REST request to update Donation : {}", donation);
        donationFacade.getRepo().save(donation);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "donation", donation.getId().toString())
                .entity(donation).build();
    }

    /**
     * GET : get all the donations.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of donations in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @ApiOperation(value = "get all the donations")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllDonations(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Donations");
        List<Donation> donations = donationFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(donations);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, donationFacade.getRepo().countAll().intValue()), "/resources/api/donation");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" donation.
     *
     * @param id the id of the donation to retrieve
     * @return the Response with status 200 (OK) and with body the donation, or
     * with status 404 (Not Found)
     */
    @ApiOperation(value = "get the donation")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getDonation(@PathParam("id") Long id) {
        log.debug("REST request to get Donation : {}", id);
        Donation donation = donationFacade.getRepo().findBy(id);
        return Optional.ofNullable(donation)
                .map(result -> Response.status(Response.Status.OK).entity(donation).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" donation.
     *
     * @param id the id of the donation to delete
     * @return the Response with status 200 (OK)
     */
    @ApiOperation(value = "remove the donation")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeDonation(@PathParam("id") Long id) {
        log.debug("REST request to delete Donation : {}", id);
        donationFacade.getRepo().remove(donationFacade.getRepo().findBy(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "donation", id.toString()).build();
    }

}
