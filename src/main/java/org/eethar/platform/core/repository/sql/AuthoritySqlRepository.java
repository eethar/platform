package org.eethar.platform.core.repository.sql;

import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.Authority;
import org.eethar.platform.core.repository.IAuthorityRepository;
import org.eethar.platform.core.repository.util.qualifier.RepositoryType;
import org.eethar.platform.core.repository.util.qualifier.RepositoryType.RepositoryTypeEnum;

/**
 *
 * @author superyass
 */
@Repository(forEntity = Authority.class)
@RepositoryType(RepositoryTypeEnum.MYSQL)
public abstract class AuthoritySqlRepository implements IAuthorityRepository  {

}
