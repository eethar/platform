package org.eethar.platform.core.service.facade.producer;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.eethar.platform.core.repository.IAuthorityRepository;
import org.eethar.platform.core.repository.IDCaseRepository;
import org.eethar.platform.core.repository.IDonationRepository;
import org.eethar.platform.core.repository.INGORepository;
import org.eethar.platform.core.repository.IUserRepository;
import org.eethar.platform.core.repository.mongodb.AuthorityMongoRepository;
import org.eethar.platform.core.repository.mongodb.DCaseMongoRepository;
import org.eethar.platform.core.repository.mongodb.DonationMongoRepository;
import org.eethar.platform.core.repository.mongodb.NGOMongoRepository;
import org.eethar.platform.core.repository.mongodb.UserMongoRepository;
import org.eethar.platform.core.repository.sql.AuthoritySqlRepository;
import org.eethar.platform.core.repository.sql.DCaseSqlRepository;
import org.eethar.platform.core.repository.sql.DonationSqlRepository;
import org.eethar.platform.core.repository.sql.NGOSqlRepository;
import org.eethar.platform.core.repository.sql.UserSqlRepository;
import org.eethar.platform.core.repository.util.qualifier.MyRepository;
import org.eethar.platform.core.repository.util.qualifier.RepositoryType;
import org.eethar.platform.core.repository.util.qualifier.RepositoryType.RepositoryTypeEnum;

/**
 *
 * @author superyass
 */
public class RepositoryProducer {

    @Inject
    @ConfigProperty(name = "eethar.pu", defaultValue = "mysql")
    private String pu;

    @Produces
    @MyRepository
    public IUserRepository getUserRepo(@RepositoryType(RepositoryTypeEnum.MONGODB) UserMongoRepository mongoRepository,
            @RepositoryType(RepositoryTypeEnum.MYSQL) UserSqlRepository sqlRepository) {
        if (pu.equals("mysql")) {
            return sqlRepository;
        } else {
            return mongoRepository;
        }
    }

    @Produces
    public INGORepository getUserRepo(NGOMongoRepository mongoRepository, NGOSqlRepository sqlRepository) {
        if (pu.equals("mysql")) {
            return sqlRepository;
        } else {
            return mongoRepository;
        }
    }

    @Produces
    @MyRepository
    public IAuthorityRepository getAuthRepo(@RepositoryType(RepositoryTypeEnum.MONGODB) AuthorityMongoRepository mongoRepository,
            @RepositoryType(RepositoryTypeEnum.MYSQL) AuthoritySqlRepository sqlRepository) {
        System.out.println("---------------------*-*-*-*-*");
        if (pu.equals("mysql")) {
            return sqlRepository;
        } else {
            return mongoRepository;
        }
    }

    @Produces
    public IDCaseRepository getUserRepo(DCaseMongoRepository mongoRepository, DCaseSqlRepository sqlRepository) {
        if (pu.equals("mysql")) {
            return sqlRepository;
        } else {
            return mongoRepository;
        }
    }

    @Produces
    public IDonationRepository getUserRepo(DonationMongoRepository mongoRepository, DonationSqlRepository sqlRepository) {
        if (pu.equals("mysql")) {
            return sqlRepository;
        } else {
            return mongoRepository;
        }
    }

}
