package org.eethar.platform.core.rest;

import org.eethar.platform.core.rest.dto.LoggerVM;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * Controller for view and managing Log Level at runtime.
 */
@Path("/management")
public class LogsResource {

    @Path("/logs")
    @GET
    
    public List<LoggerVM> getList() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        return context.getLoggerList()
                .stream()
                .map(LoggerVM::new)
                .collect(Collectors.toList());
    }

    @Path("/logs")
    @PUT
    
    public void changeLevel(LoggerVM jsonLogger) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getLogger(jsonLogger.getName()).setLevel(Level.valueOf(jsonLogger.getLevel()));
    }
}
