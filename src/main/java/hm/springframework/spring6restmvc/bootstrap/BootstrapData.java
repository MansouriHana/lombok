package hm.springframework.spring6restmvc.bootstrap;

import hm.springframework.spring6restmvc.entities.Beverage;
import hm.springframework.spring6restmvc.entities.Customer;
import hm.springframework.spring6restmvc.model.BeverageStyle;
import hm.springframework.spring6restmvc.repositories.BeverageRepository;
import hm.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final BeverageRepository beverageRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
    }

    private void loadBeerData() {
        if (beverageRepository.count() == 0){
            Beverage beverage1 = Beverage.builder()
                    .beverageName("Galaxy Cat")
                    .beverageStyle(BeverageStyle.PALE_ALE)
                    .upc("12356")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(122)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Beverage beverage2 = Beverage.builder()
                    .beverageName("Crank")
                    .beverageStyle(BeverageStyle.PALE_ALE)
                    .upc("12356222")
                    .price(new BigDecimal("11.99"))
                    .quantityOnHand(392)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Beverage beverage3 = Beverage.builder()
                    .beverageName("Sunshine City")
                    .beverageStyle(BeverageStyle.PALE_ALE)
                    .upc("12356")
                    .price(new BigDecimal("13.99"))
                    .quantityOnHand(144)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            beverageRepository.save(beverage1);
            beverageRepository.save(beverage2);
            beverageRepository.save(beverage3);
        }

    }

    private void loadCustomerData() {

        if (customerRepository.count() == 0) {
            Customer customer1 = Customer.builder()
                    .name("Customer 1")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Customer customer2 = Customer.builder()
                    .name("Customer 2")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Customer customer3 = Customer.builder()
                    .name("Customer 3")
                    .version(1)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        }

    }


}