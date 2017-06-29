package org.eethar.platform.core.service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import org.eethar.platform.core.entity.Authority;
import org.eethar.platform.core.repository.IAuthorityRepository;
import org.eethar.platform.core.repository.util.qualifier.MyRepository;

@Stateless
@Named("authority")
public class AuthorityFacade extends AbstractFacade<Authority,IAuthorityRepository> {

    @Inject
    private EntityManager em;
    
    @Inject
    @MyRepository
    private IAuthorityRepository authorityRepository;

    public AuthorityFacade() {
        super(Authority.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public IAuthorityRepository getRepo() {
        return authorityRepository;
    }
}
