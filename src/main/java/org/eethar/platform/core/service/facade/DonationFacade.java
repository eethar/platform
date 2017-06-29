package org.eethar.platform.core.service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import org.eethar.platform.core.entity.Donation;
import org.eethar.platform.core.repository.mongodb.DonationMongoRepository;

@Stateless
@Named("donationService")
public class DonationFacade extends AbstractFacade<Donation,DonationMongoRepository> {

    @Inject
    private EntityManager em;
    @Inject
    private DonationMongoRepository donationRepository;

    public DonationFacade() {
        super(Donation.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public DonationMongoRepository getRepo() {
        return donationRepository;
    }

}
