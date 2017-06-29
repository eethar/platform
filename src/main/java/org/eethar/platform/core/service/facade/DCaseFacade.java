package org.eethar.platform.core.service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import org.eethar.platform.core.entity.DCase;
import org.eethar.platform.core.repository.mongodb.DCaseMongoRepository;

@Stateless
@Named("DCaseService")
public class DCaseFacade extends AbstractFacade<DCase,DCaseMongoRepository> {

    @Inject
    private EntityManager em;
    @Inject
    private DCaseMongoRepository dCaseRepository;

    public DCaseFacade() {
        super(DCase.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public DCaseMongoRepository getRepo() {
        return dCaseRepository;
    }

    

}
