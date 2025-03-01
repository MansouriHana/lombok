package hm.springframework.spring6restmvc.services;

import hm.springframework.spring6restmvc.model.Beverage;
import hm.springframework.spring6restmvc.model.BeverageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeverageServiceImpl implements BeverageService {
    @Override
    public Beverage getBeerById(UUID id) {
        log.debug("Get Beverage by Id - in service. Id: " + id.toString());
        return Beverage.builder()
                .id(id)
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeverageStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
