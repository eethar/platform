package org.eethar.platform.core.repository.sql;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.eethar.platform.core.entity.DCase;
import org.eethar.platform.core.repository.IDCaseRepository;
import org.eethar.platform.core.repository.util.qualifier.SqlRepository;

/**
 *
 * @author superyass
 */
@Repository(forEntity = DCase.class)
public abstract class DCaseSqlRepository implements IDCaseRepository {

    @Override
    public Long count() {
        return countAll();
    }
    
    

}
