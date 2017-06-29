package org.eethar.platform.core.service.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.deltaspike.data.api.EntityRepository;
import org.eethar.platform.core.entity.IGenericEntity;
import org.eethar.platform.core.repository.IAuthorityRepository;

public abstract class AbstractFacade<E extends IGenericEntity, R extends EntityRepository> {

    private final Class<E> entityClass;

    public AbstractFacade(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    abstract public R getRepo();

    abstract public EntityManager getEntityManager();

    public List<E> findRange(int startPosition, int size) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(size);
        q.setFirstResult(startPosition);
        return q.getResultList();
    }

    public List<E> findRangeForNamedQuery(String namedQuery, int startPosition, int size) {
        Query cq = getEntityManager().createNamedQuery(namedQuery);
        cq.setMaxResults(size);
        cq.setFirstResult(startPosition);
        return cq.getResultList();
    }

}
