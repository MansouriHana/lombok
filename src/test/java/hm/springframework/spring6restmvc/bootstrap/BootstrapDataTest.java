package hm.springframework.spring6restmvc.bootstrap;

import hm.springframework.spring6restmvc.entities.Beverage;
import hm.springframework.spring6restmvc.repositories.BeverageRepository;
import hm.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapDataTest {

    @Autowired
    BeverageRepository beverageRepository;

    @Autowired
    CustomerRepository customerRepository;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(beverageRepository, customerRepository);
    }

    @Test
    void Testrun() throws Exception {
        bootstrapData.run(null);

        assertThat(beverageRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}