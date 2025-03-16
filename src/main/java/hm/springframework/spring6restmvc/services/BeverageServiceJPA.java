package hm.springframework.spring6restmvc.services;

import hm.springframework.spring6restmvc.entities.Beverage;
import hm.springframework.spring6restmvc.mappers.BeverageMapper;
import hm.springframework.spring6restmvc.model.BeverageDTO;
import hm.springframework.spring6restmvc.repositories.BeverageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeverageServiceJPA implements BeverageService{

    private final BeverageRepository beverageRepository;
    private final BeverageMapper beverageMapper;
    @Override
    public List<BeverageDTO> listBeverages() {
        return beverageRepository.findAll()
                .stream()
                .map(beverageMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BeverageDTO> getBeverageById(UUID id) {
        return Optional.ofNullable(beverageMapper.beerToBeerDto(beverageRepository.findById(id).orElse(null)));
    }

    @Override
    public BeverageDTO saveNewBeverage(BeverageDTO beverage) {
        return null;
    }

    @Override
    public void updateBeverage(UUID beverageId, BeverageDTO beverage) {

    }

    @Override
    public void deleteBeverageById(UUID beverageId) {

    }

    @Override
    public void patchBeverageById(UUID beverageId, BeverageDTO beverage) {

    }
}
