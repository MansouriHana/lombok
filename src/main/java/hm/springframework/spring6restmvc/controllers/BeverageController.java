package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.services.BeverageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class BeverageController {

    private final BeverageService beverageService;
}
