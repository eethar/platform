package org.eethar.platform.core.rest;

import org.eethar.platform.core.service.facade.AuthorityFacade;
import org.eethar.platform.core.entity.User;
import org.eethar.platform.app.service.mail.MailService;
import org.eethar.platform.app.service.UserService;
import org.eethar.platform.core.rest.dto.ManagedUserDTO;
import org.eethar.platform.core.rest.dto.UserDTO;
import org.eethar.platform.core.rest.util.HeaderUtil;
import org.eethar.platform.core.rest.util.Page;
import org.eethar.platform.core.rest.util.PaginationUtil;
import org.eethar.platform.app.security.Secured;
import org.eethar.platform.app.security.AuthoritiesConstants;
import org.slf4j.Logger;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import static java.util.stream.Collectors.toList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.eethar.platform.core.service.facade.UserFacade;

/**
 * REST controller for managing users.
 *
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of
 * authorities.</p>
 */
@Api(value = "/api")
@Path("/api")
public class UserController {

    @Inject
    private Logger log;

    @Inject
    private UserFacade userFacade;

    @Inject
    private MailService mailService;

    @Inject
    private AuthorityFacade authorityFacade;

    @Inject
    private UserService userService;

    @Context
    private HttpServletRequest request;

    /**
     * POST /users : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends
     * an mail with an activation link. The user needs to be activated on
     * creation.
     * </p>
     *
     * @param managedUserDTO the user to create
     * @return the Response with status 201 (Created) and with body the new
     * user, or with status 400 (Bad Request) if the login or email is already
     * in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    
    @ApiOperation(value = "create a new user")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @Path(value = "/users")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(AuthoritiesConstants.ADMIN)
    public Response createUser(ManagedUserDTO managedUserDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", managedUserDTO);

        //Lowercase the user login before comparing with database
        if (userFacade.findByLogin(managedUserDTO.getLogin().toLowerCase()).isPresent()) {
            return HeaderUtil.createFailureAlert(Response.status(BAD_REQUEST), "userManagement", "userexists", "Login already in use").build();
        } else if (userFacade.findByEmail(managedUserDTO.getEmail()).isPresent()) {
            return HeaderUtil.createFailureAlert(Response.status(BAD_REQUEST), "userManagement", "emailexists", "Email already in use").build();
        } else {
            User newUser = userService.createUser(managedUserDTO);
            String baseUrl = request.getScheme()
                    + // "http"
                    "://"
                    + // "://"
                    request.getServerName()
                    + // "myhost"
                    ":"
                    + // ":"
                    request.getServerPort()
                    + // "80"
                    request.getContextPath();
            // "/myContextPath" or "" if deployed in root context

            mailService.sendCreationEmail(newUser, baseUrl);
            return HeaderUtil.createAlert(Response.created(new URI("/resources/api/users/" + newUser.getLogin())),
                    "userManagement.created", newUser.getLogin()).entity(new UserDTO(newUser)).build();
        }
    }
    
    /**
     * GET /users : get all users.
     *
     * @param page the pagination information
     * @param size the pagination size information
     * @return the Response with status 200 (OK) and with body all users
     * @throws URISyntaxException if the pagination headers couldn't be
     * generated
     */
    
    @ApiOperation(value = "get all the users")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @Path(value = "/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getAllUsers(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        List<User> userList = userFacade.findRange(page * size, size);
        List<ManagedUserDTO> managedUserDTOs = userList.stream()
                .map(ManagedUserDTO::new)
                .collect(toList());

        ResponseBuilder builder = Response.ok(managedUserDTOs);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, userFacade.count().intValue()), "/resources/api/users");
        return builder.build();
    }

    /**
     * GET /users/:login : get the "login" user.
     *
     * @param login the login of the user to find
     * @return the Response with status 200 (OK) and with body the "login" user,
     * or with status 404 (Not Found)
     */
    
    @ApiOperation(value = "get the user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path(value = "/users/{login}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getUser(@PathParam("login") String login) {
        log.debug("REST request to get User : {}", login);
        return userService.getUserWithAuthoritiesByLogin(login)
                .map(ManagedUserDTO::new)
                .map(managedUserDTO -> Response.ok(managedUserDTO).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    /**
     * DELETE USER :login : remove the "login" User.
     *
     * @param login the login of the user to remove
     * @return the Response with status 200 (OK) or with status 404 (Not Found)
     */
    
    @ApiOperation(value = "remove the user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path(value = "/users/{login}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(AuthoritiesConstants.ADMIN)
    public Response deleteUser(@PathParam("login") String login) {
        log.debug("REST request to delete User: {}", login);
        userService.deleteUserInformation(login);
        return HeaderUtil.createAlert(Response.ok(), "userManagement.deleted", login).build();
    }
}
