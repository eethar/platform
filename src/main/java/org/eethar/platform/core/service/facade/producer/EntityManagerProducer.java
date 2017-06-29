package org.eethar.platform.core.service.facade.producer;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.deltaspike.core.api.config.ConfigProperty;

/**
 * Producer for injectable EntityManager
 *
 */
public class EntityManagerProducer {

    @PersistenceContext(unitName = "MONGO_PU")
    private EntityManager mongoEm;

//    @PersistenceContext(unitName = "MYSQL_PU")
//    private EntityManager mySqlEm;

    @ConfigProperty(name = "eethar.pu", defaultValue = "mysql")
    private String pu;

    @Produces
    public EntityManager getMyPU() {
//        if (pu.equals("mysql")) {
//            return mySqlEm;
//        } else {
            return mongoEm;
//        }
    }

}
