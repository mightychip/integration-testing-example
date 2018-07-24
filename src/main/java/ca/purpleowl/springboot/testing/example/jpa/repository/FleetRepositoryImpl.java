package ca.purpleowl.springboot.testing.example.jpa.repository;

import ca.purpleowl.springboot.testing.example.jpa.entity.Fleet;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional(readOnly = true)
public class FleetRepositoryImpl implements FleetRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> listSimilarFleetNames(String similarTo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteriaQuery = cb.createQuery(String.class);
        Root<Fleet> root = criteriaQuery.from(Fleet.class);

        criteriaQuery.select(root.get("fleetName"))
                     .where(cb.like(root.get("fleetName"), similarTo));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
