package hm.springframework.spring6restmvc.services;

import hm.springframework.spring6restmvc.model.Beverage;

import java.util.UUID;

public interface BeverageService {
    Beverage getBeerById(UUID id);

}
