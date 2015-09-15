package se.ithuset.hibsearch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.ithuset.hibsearch.domain.Address;
import se.ithuset.hibsearch.domain.Bar;
import se.ithuset.hibsearch.domain.Beer;
import se.ithuset.hibsearch.service.BarService;

import java.util.List;

@RestController
@RequestMapping("/bars")
public class BarController {
    @Autowired
    private BarService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpStatus addBar() {
        Beer beer = new Beer("Apan", "Smarrig APA");
        Address address = new Address("Apstigen 1", "111 30", "Sthlm");

        Bar bar = new Bar("Baren", "Trevliga baren", 4);
        bar.addAddress(address);
        bar.addBeer(beer);

        service.createBar(bar);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Bar> findAllBars() {
        return service.findAll();
    }
}
