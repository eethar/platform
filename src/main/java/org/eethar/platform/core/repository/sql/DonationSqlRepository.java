package org.eethar.platform.core.repository.sql;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.DCase;
import org.eethar.platform.core.entity.Donation;
import org.eethar.platform.core.repository.IDonationRepository;
import org.eethar.platform.core.repository.util.qualifier.SqlRepository;

/**
 *
 * @author superyass
 */
@Repository(forEntity = DCase.class)
public abstract class DonationSqlRepository implements IDonationRepository {


}
