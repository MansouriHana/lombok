package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.BeverageDTO;
import hm.springframework.spring6restmvc.repositories.BeverageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeverageControllerIT {

    @Autowired
    BeverageController beverageController;

    @Autowired
    BeverageRepository beverageRepository;

    @Test
    void testListBeers() {
        List<BeverageDTO> dtos = beverageController.listBeverages();

        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beverageRepository.deleteAll();
        List<BeverageDTO> dtos = beverageController.listBeverages();

        assertThat(dtos.size()).isEqualTo(0);
    }
}