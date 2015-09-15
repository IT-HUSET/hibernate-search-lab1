package se.ithuset.hibsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ithuset.hibsearch.dao.BarDao;
import se.ithuset.hibsearch.domain.Bar;

import java.util.List;

@Service
@Transactional
public class BarServiceImpl implements BarService {

    @Autowired
    private BarDao dao;

    @Override
    public void createBar(Bar bar) {
        dao.createBar(bar);
    }

    @Override
    public List<Bar> findAll() {
        return dao.findAll();
    }
}
