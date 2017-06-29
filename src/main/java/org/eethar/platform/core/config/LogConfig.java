package org.eethar.platform.core.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.LoggerFactory;

/**
 *
 * @author superyass
 */
@Startup
@Singleton
public class LogConfig {

    @PostConstruct
    public void init() {
        System.out.println("disabling mongo driver debug logs");
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.WARN);
    }

}
