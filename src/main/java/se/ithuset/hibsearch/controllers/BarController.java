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
        Beer beer = new Beer("Punk IPA", "Nice IPA");
        Address address = new Address("S:t Eriksg. 56 A", "112 34", "Stockholm");

        Bar bar = new Bar("Brewdog Sthlm", "At the epicentre of the mainstream lager scene, BrewDog Kungsholmen is the hop-flecked, full-throttle alternative. Drop by, give us a hearty 'hej!' and enjoy the best selection of craft beer in the city.", 4, 59.336422, 18.034675);
        bar.addAddress(address);
        bar.addBeer(beer);

        service.createBar(bar);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Bar> findAllBars() {
        return service.findAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Bar> search() {
        return service.search();
    }
}
