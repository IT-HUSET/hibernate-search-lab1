package se.ithuset.hibsearch.dao;

import se.ithuset.hibsearch.domain.Bar;

import java.util.List;

public interface BarDao {
    void createBar(Bar bar);

    List<Bar> findAll();
}
