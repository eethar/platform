package org.eethar.platform.core.repository.mongodb;

import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.Authority;
import org.eethar.platform.core.entity.User;
import org.eethar.platform.core.repository.IUserRepository;
import org.eethar.platform.core.repository.util.qualifier.MongoRepository;
import org.eethar.platform.core.repository.util.qualifier.RepositoryType;

/**
 *
 * @author superyass
 */
@Repository(forEntity = User.class)
@RepositoryType(RepositoryType.RepositoryTypeEnum.MONGODB)
public abstract class UserMongoRepository implements IUserRepository {

    @Override
    public Long count() {
        return countAllMongo();
    }

    

}
