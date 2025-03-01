package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.Beverage;
import hm.springframework.spring6restmvc.services.BeverageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@AllArgsConstructor
@Controller
public class BeverageController {

    private static final Logger log = LoggerFactory.getLogger(BeverageController.class);
    private final BeverageService beverageService;

    public Beverage getBeverageById(UUID id){

        log.debug("Get Beverage by Id - in controller");
        return beverageService.getBeerById(id);
    }

}
