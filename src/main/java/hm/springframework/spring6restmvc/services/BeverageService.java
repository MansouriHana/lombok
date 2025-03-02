package hm.springframework.spring6restmvc.services;

import hm.springframework.spring6restmvc.model.Beverage;

import java.util.List;
import java.util.UUID;

public interface BeverageService {
    List<Beverage> listBeverages();

    Beverage getBeerById(UUID id);
    Beverage saveNewBeverage(Beverage beverage);

    void updateBeverage(UUID beverageId, Beverage beverage);

    void deleteBeverageById(UUID beverageId);

    void patchBeverageById(UUID beverageId, Beverage beverage);
}
