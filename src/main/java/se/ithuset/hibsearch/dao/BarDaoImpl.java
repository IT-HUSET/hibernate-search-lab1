package se.ithuset.hibsearch.dao;

import org.springframework.stereotype.Repository;
import se.ithuset.hibsearch.domain.Bar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BarDaoImpl implements BarDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createBar(Bar bar) {
        entityManager.persist(bar);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Bar> findAll() {
        return entityManager.createQuery("from Bar").getResultList();
    }
}
