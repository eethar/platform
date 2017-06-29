package org.eethar.platform.core.service.facade;

import java.util.Optional;
import org.eethar.platform.core.entity.User;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.eethar.platform.core.repository.IUserRepository;
import org.eethar.platform.core.repository.mongodb.UserMongoRepository;
import org.eethar.platform.core.repository.util.qualifier.MyRepository;

@Stateless
@Named("userService")
public class UserFacade extends AbstractFacade<User, IUserRepository>{

    @Inject
    private EntityManager em;
    @Inject
    @MyRepository
    private IUserRepository userRepository;

    public UserFacade() {
        super(User.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public IUserRepository getRepo() {
        return userRepository;
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long count() {
        return userRepository.count();
    }
    
}
