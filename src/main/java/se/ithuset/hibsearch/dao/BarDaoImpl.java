package se.ithuset.hibsearch.dao;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
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
        return entityManager.createQuery(" select b " +
                "from Bar b").getResultList();
    }

    @Override
    public List<Bar> search() {
        return search("Sthln");
    }

    @SuppressWarnings("unchecked")
    public List<Bar> search(String token) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                        getFullTextEntityManager(entityManager);

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Bar.class).get();

        // a very basic query by keywords
        org.apache.lucene.search.Query query =
                queryBuilder
                        .keyword()
                        .fuzzy()
                        .onFields("name", "description", "beers.name", "beers.description", "address.addressRow", "address.city")
                        .matching(token)
                        .createQuery();

        // wrap Lucene query in an Hibernate Query object
        org.hibernate.search.jpa.FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, Bar.class);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List results = jpaQuery.getResultList();

        return results;
    }
}
