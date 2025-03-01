package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.Customer;
import hm.springframework.spring6restmvc.services.CustomerService;
import hm.springframework.spring6restmvc.services.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public List<Customer> getCustomers() {
        return customerService.findAllCustomers();
    }
    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") UUID customerId) {
        return customerService.findCustomerById(customerId);
    }
}
