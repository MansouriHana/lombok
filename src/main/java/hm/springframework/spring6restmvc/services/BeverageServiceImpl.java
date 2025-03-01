package hm.springframework.spring6restmvc.services;

import hm.springframework.spring6restmvc.model.Beverage;
import hm.springframework.spring6restmvc.model.BeverageStyle;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BeverageServiceImpl implements BeverageService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BeverageServiceImpl.class);
    private Map<UUID, Beverage> beverageMap;

    public BeverageServiceImpl() {
        this.beverageMap = new HashMap<>();

        Beverage bev1 = Beverage.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeverageStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beverage bev2 = Beverage.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeverageStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beverage bev3 = Beverage.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeverageStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beverageMap.put(bev1.getId(), bev1);
        beverageMap.put(bev2.getId(), bev2);
        beverageMap.put(bev3.getId(), bev3);
    }

    @Override
    public List<Beverage> listBeverages() {
        return new ArrayList<>(beverageMap.values());
    }


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
