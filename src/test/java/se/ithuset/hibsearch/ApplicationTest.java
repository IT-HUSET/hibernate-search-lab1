package se.ithuset.hibsearch;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.ithuset.hibsearch.domain.Bar;
import se.ithuset.hibsearch.service.BarService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private BarService service;

    @Test
    @Sql({"/small-sample.sql"})
    public void test() {
        List<Bar> all = service.findAll();
        Assert.assertEquals(1, all.size());
    }
}
