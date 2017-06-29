package org.eethar.platform.core.repository.mongodb;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.NGO;
import org.eethar.platform.core.entity.User;
import org.eethar.platform.core.repository.INGORepository;
import org.eethar.platform.core.repository.util.qualifier.MongoRepository;

/**
 *
 * @author superyass
 */
@Repository(forEntity = NGO.class)
public abstract class NGOMongoRepository implements INGORepository {

    @Override
    public Long count() {
        return countAllMongo();
    }

}
