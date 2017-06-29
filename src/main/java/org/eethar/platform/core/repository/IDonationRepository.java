package org.eethar.platform.core.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.Donation;

/**
 *
 * @author superyass
 */
public interface IDonationRepository extends EntityRepository<Donation, Long> {

    @Query(value = "db.dcase.count()", isNative = true)
    public Long countAll();
}
