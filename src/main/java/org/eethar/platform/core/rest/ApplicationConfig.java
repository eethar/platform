/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eethar.platform.core.rest;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import javax.inject.Inject;
import javax.ws.rs.core.Application;

/**
 *
 * @author superyass
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }


    @Override
    public Set<Object> getSingletons() {
        final Set<Object> instances = new HashSet<>();
        instances.add(new JacksonJsonProvider());
        return instances;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.eethar.platform.app.security.SecurityUtils.class);
        resources.add(org.eethar.platform.app.security.jwt.JWTAuthenticationFilter.class);
        resources.add(org.eethar.platform.core.rest.AccountController.class);
        resources.add(org.eethar.platform.core.rest.DCaseController.class);
        resources.add(org.eethar.platform.core.rest.DonationController.class);
        resources.add(org.eethar.platform.core.rest.LogsResource.class);
        resources.add(org.eethar.platform.core.rest.NGOController.class);
        resources.add(org.eethar.platform.core.rest.TestController.class);
        resources.add(org.eethar.platform.core.rest.UserController.class);
        resources.add(org.eethar.platform.core.rest.UserJWTController.class);
    }
    
}
