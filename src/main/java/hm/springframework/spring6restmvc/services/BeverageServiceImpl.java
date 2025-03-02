package hm.springframework.spring6restmvc.services;

import hm.springframework.spring6restmvc.model.Beverage;
import hm.springframework.spring6restmvc.model.BeverageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeverageServiceImpl implements BeverageService {

    private Map<UUID, Beverage> beverageMap;

    public BeverageServiceImpl() {
        this.beverageMap = new HashMap<>();

        Beverage bev1 = Beverage.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beverageName("Galaxy Cat")
                .beverageStyle(BeverageStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beverage bev2 = Beverage.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beverageName("Crank")
                .beverageStyle(BeverageStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beverage bev3 = Beverage.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beverageName("Sunshine City")
                .beverageStyle(BeverageStyle.IPA)
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
        return beverageMap.get(id);

    }

    @Override
    public Beverage saveNewBeverage(Beverage beverage) {
      Beverage saveBevr = Beverage.builder()
              .id(UUID.randomUUID())
              .createdDate(LocalDateTime.now())
              .updateDate(LocalDateTime.now())
              .beverageName(beverage.getBeverageName())
              .beverageStyle(beverage.getBeverageStyle())
              .quantityOnHand(beverage.getQuantityOnHand())
              .upc(beverage.getUpc())
              .price(beverage.getPrice())
              .version(beverage.getVersion())
              .build();
      beverageMap.put(saveBevr.getId(), saveBevr);
      return saveBevr;
    }

    @Override
    public void updateBeverage(UUID beverageId, Beverage beverage) {
        Beverage existing = beverageMap.get(beverageId);

        existing.setBeverageName(beverage.getBeverageName());
        existing.setBeverageStyle(beverage.getBeverageStyle());
        existing.setQuantityOnHand(beverage.getQuantityOnHand());
        existing.setUpc(beverage.getUpc());
        existing.setPrice(beverage.getPrice());

        beverageMap.put(existing.getId(), existing);
    }

    @Override
    public void deleteBeverageById(UUID beverageId) {
        beverageMap.remove(beverageId);
    }
}
