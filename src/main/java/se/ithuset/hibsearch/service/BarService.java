package se.ithuset.hibsearch.service;

import se.ithuset.hibsearch.domain.Bar;

import java.util.List;

public interface BarService {
    void createBar(Bar bar);

    List<Bar> findAll();
}
