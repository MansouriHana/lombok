package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.Beverage;
import hm.springframework.spring6restmvc.services.BeverageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@AllArgsConstructor
@RestController
public class BeverageController {

    public static final String BEVERAGE_PATH = "/api/v1/beverage";
    public static final String BEVERAGE_PATH_ID = "/{beverageId}";
    private final BeverageService beverageService;

    @GetMapping(BEVERAGE_PATH_ID)
    public Beverage getBeverageById(@PathVariable("beverageId") UUID beverageId) {

        log.debug("Get Beverage by Id - in controller -- 1245");
        return beverageService.getBeverageById(beverageId);
    }

    @GetMapping(BEVERAGE_PATH)
    public List<Beverage> listBeverages() {
        return beverageService.listBeverages();
    }

    @PostMapping(BEVERAGE_PATH)
    public ResponseEntity<Beverage> handlePost(@RequestBody Beverage beverage){
        Beverage saveBeverage = beverageService.saveNewBeverage(beverage);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beverages/" + saveBeverage.getId().toString());
        return new ResponseEntity<>(saveBeverage, headers, HttpStatus.CREATED);

    }

    @PutMapping(BEVERAGE_PATH_ID)
    public ResponseEntity<Beverage> handlePut(@PathVariable("beverageId") UUID beverageId, @RequestBody Beverage beverage){

        beverageService.updateBeverage(beverageId, beverage);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEVERAGE_PATH_ID)
    public ResponseEntity<Beverage> handleDelete(@PathVariable("beverageId") UUID beverageId){

        beverageService.deleteBeverageById(beverageId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEVERAGE_PATH_ID)
    public ResponseEntity<Beverage> updateBeveragePatchById(@PathVariable("beverageId")UUID beverageId, @RequestBody Beverage beverage){

        beverageService.patchBeverageById(beverageId, beverage);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
