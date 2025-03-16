package hm.springframework.spring6restmvc.mappers;

import hm.springframework.spring6restmvc.entities.Beverage;
import hm.springframework.spring6restmvc.model.BeverageDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeverageMapper {
    Beverage beerDtoToBeer(BeverageDTO dto);

    BeverageDTO beerToBeerDto(Beverage beer);
}
