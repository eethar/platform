package org.eethar.platform.core.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.NGO;

/**
 *
 * @author superyass
 */
@Repository(forEntity = NGO.class)
public interface INGORepository extends EntityRepository<NGO, Long> {

    @Query(value="db.ngo.count()",isNative = true)
    public Long countAllMongo();
    
}
