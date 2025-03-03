package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.BeverageDTO;
import hm.springframework.spring6restmvc.services.BeverageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public BeverageDTO getBeverageById(@PathVariable("beverageId") UUID beverageId) {

        log.debug("Get Beverage by Id - in controller -- 1245");
        return beverageService.getBeverageById(beverageId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(BEVERAGE_PATH)
    public List<BeverageDTO> listBeverages() {
        return beverageService.listBeverages();
    }

    @PostMapping(BEVERAGE_PATH)
    public ResponseEntity<BeverageDTO> handlePost(@RequestBody BeverageDTO beverage){
        BeverageDTO saveBeverage = beverageService.saveNewBeverage(beverage);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beverages/" + saveBeverage.getId().toString());
        return new ResponseEntity<>(saveBeverage, headers, HttpStatus.CREATED);

    }

    @PutMapping(BEVERAGE_PATH_ID)
    public ResponseEntity<BeverageDTO> handlePut(@PathVariable("beverageId") UUID beverageId, @RequestBody BeverageDTO beverage){

        beverageService.updateBeverage(beverageId, beverage);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEVERAGE_PATH_ID)
    public ResponseEntity<BeverageDTO> handleDelete(@PathVariable("beverageId") UUID beverageId){

        beverageService.deleteBeverageById(beverageId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEVERAGE_PATH_ID)
    public ResponseEntity<BeverageDTO> updateBeveragePatchById(@PathVariable("beverageId")UUID beverageId, @RequestBody BeverageDTO beverage){

        beverageService.patchBeverageById(beverageId, beverage);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
