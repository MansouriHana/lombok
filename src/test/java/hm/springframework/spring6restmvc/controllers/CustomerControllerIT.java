package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.entities.Customer;
import hm.springframework.spring6restmvc.model.CustomerDTO;
import hm.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;


    @Test
    void testGetByIdNotFound(){

        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomer(UUID.randomUUID());
        });
    }

    @Test
    void testGetById(){
      Customer customer = customerRepository.findAll().get(0);

      CustomerDTO dto = customerController.getCustomer(customer.getId());

      assertThat(dto).isNotNull();
    }


    @Test
    void testGetAllCustomer(){
        List<CustomerDTO> customerList = customerController.getCustomers();

        assertThat(customerList.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testListNotFound(){
        customerRepository.deleteAll();
        List<CustomerDTO> customerList = customerController.getCustomers();
        assertThat(customerList.size()).isEqualTo(0);

    }

}