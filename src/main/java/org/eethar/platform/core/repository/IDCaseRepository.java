package org.eethar.platform.core.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.DCase;

/**
 *
 * @author superyass
 */
@Repository(forEntity = DCase.class)
public interface IDCaseRepository extends EntityRepository<DCase,Long>{
    @Query(value="db.dcase.count()",isNative = true)
    public Long countAll();
}
