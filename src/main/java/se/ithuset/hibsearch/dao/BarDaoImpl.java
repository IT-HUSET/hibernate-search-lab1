package se.ithuset.hibsearch.dao;

import org.hibernate.search.FullTextQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import org.springframework.stereotype.Repository;
import se.ithuset.hibsearch.domain.Bar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BarDaoImpl implements BarDao {
    public static final int MAX_DISTANCE = 50;
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
        return search("Punk", 59.339865, 18.058047);
    }

    @SuppressWarnings("unchecked")
    public List<Bar> search(String token, Double latitude, Double longitude) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                        getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Bar.class).get();


        BooleanJunction<BooleanJunction> bool = queryBuilder.bool();

        bool.must(queryBuilder
                .keyword()
                .fuzzy()
                .onFields("name", "description", "beers.name", "beers.description", "address.addressRow", "address.city")
                .matching(token)
                .createQuery());

        if (latitude != null && longitude != null) {
            bool.must(queryBuilder
                    .spatial()
                    .onField("location") // .location")
                    .within(MAX_DISTANCE, Unit.KM)
                    .ofLatitude(latitude)
                    .andLongitude(longitude)
                    .createQuery());
        }

        org.hibernate.search.jpa.FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(bool.createQuery(), Bar.class);
        jpaQuery.setProjection(FullTextQuery.SPATIAL_DISTANCE, FullTextQuery.THIS);
        jpaQuery.setSpatialParameters(latitude, longitude, "location");


        @SuppressWarnings("unchecked")
        List results = jpaQuery.getResultList();

        return results;
    }
}
