package org.eethar.platform.core.repository;

import java.util.Optional;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.User;
import org.eethar.platform.core.repository.util.qualifier.MyRepository;

/**
 *
 * @author superyass
 */
@MyRepository
public interface IUserRepository extends EntityRepository<User, Long> {

    public Optional<User> findByLogin(String login);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByActivationKey(String activationKey);

    public Optional<User> findByResetKey(String resetKey);

    //for mongodb
    @Query(value = "db.account.count()", isNative = true)
    public Long countAllMongo();

}
