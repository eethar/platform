package org.eethar.platform.core.repository.sql;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.NGO;
import org.eethar.platform.core.repository.INGORepository;
import org.eethar.platform.core.repository.util.qualifier.SqlRepository;

/**
 *
 * @author superyass
 */
@Repository(forEntity = NGO.class)
public abstract class NGOSqlRepository implements INGORepository {

}
