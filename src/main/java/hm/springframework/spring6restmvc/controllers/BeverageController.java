package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.Beverage;
import hm.springframework.spring6restmvc.services.BeverageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class BeverageController {

    private static final Logger log = LoggerFactory.getLogger(BeverageController.class);
    private final BeverageService beverageService;

    public BeverageController(BeverageService beverageService) {
        this.beverageService = beverageService;
    }

    public Beverage getBeverageById(UUID id) {

        log.debug("Get Beverage by Id - in controller");
        return beverageService.getBeerById(id);
    }

    @GetMapping("api/v1/beverages")
    public List<Beverage> listBeverages() {
        return beverageService.listBeverages();
    }

}
