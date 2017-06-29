package org.eethar.platform.core.service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import org.eethar.platform.core.entity.NGO;
import org.eethar.platform.core.repository.mongodb.NGOMongoRepository;

@Stateless
@Named("ngoService")
public class NGOFacade extends AbstractFacade<NGO,NGOMongoRepository> {

    @Inject
    private EntityManager em;
    @Inject
    private NGOMongoRepository ngoRepository;

    public NGOFacade() {
        super(NGO.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public NGOMongoRepository getRepo() {
        return ngoRepository;
    }

    public Long count() {
        return getRepo().count();
    }


}
