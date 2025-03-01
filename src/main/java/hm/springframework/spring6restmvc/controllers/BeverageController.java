package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.Beverage;
import hm.springframework.spring6restmvc.services.BeverageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/beverages")
public class BeverageController {

    private static final Logger log = LoggerFactory.getLogger(BeverageController.class);
    private final BeverageService beverageService;

    public BeverageController(BeverageService beverageService) {
        this.beverageService = beverageService;
    }

    @GetMapping("/{beverageId}")
    public Beverage getBeverageById(@PathVariable("beverageId") UUID beverageId) {

        log.debug("Get Beverage by Id - in controller");
        return beverageService.getBeerById(beverageId);
    }

    @GetMapping()
    public List<Beverage> listBeverages() {
        return beverageService.listBeverages();
    }

}
