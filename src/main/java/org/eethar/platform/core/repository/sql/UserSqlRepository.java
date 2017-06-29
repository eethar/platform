package org.eethar.platform.core.repository.sql;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.User;
import org.eethar.platform.core.repository.IUserRepository;
import org.eethar.platform.core.repository.util.qualifier.RepositoryType;
import org.eethar.platform.core.repository.util.qualifier.SqlRepository;

/**
 *
 * @author superyass
 */
@Repository(forEntity = User.class)
@RepositoryType(RepositoryType.RepositoryTypeEnum.MYSQL)
public abstract class UserSqlRepository implements IUserRepository {

}
