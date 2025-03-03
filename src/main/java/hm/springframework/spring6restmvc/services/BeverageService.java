package hm.springframework.spring6restmvc.services;

import hm.springframework.spring6restmvc.model.BeverageDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeverageService {
    List<BeverageDTO> listBeverages();

    Optional<BeverageDTO> getBeverageById(UUID id);
    BeverageDTO saveNewBeverage(BeverageDTO beverage);

    void updateBeverage(UUID beverageId, BeverageDTO beverage);

    void deleteBeverageById(UUID beverageId);

    void patchBeverageById(UUID beverageId, BeverageDTO beverage);
}
