package org.eethar.platform.core.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.eethar.platform.core.entity.Authority;
import org.eethar.platform.core.repository.util.qualifier.MyRepository;

/**
 *
 * @author superyass
 */
@MyRepository
public interface IAuthorityRepository extends EntityRepository<Authority, Long> {

    @Query(value = "db.authority.count()", isNative = true)
    public Long countAll();
}
