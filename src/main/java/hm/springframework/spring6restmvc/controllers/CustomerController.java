package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.Customer;
import hm.springframework.spring6restmvc.services.CustomerService;
import hm.springframework.spring6restmvc.services.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = "/{customerId}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> getCustomers() {

        return customerService.findAllCustomers();
    }
    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomer(@PathVariable("customerId") UUID customerId) {

        return customerService.findCustomerById(customerId);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<Customer> handleCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity<>(savedCustomer, headers, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> handlePutCustomer(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {

        customerService.updateCustomerById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> handleDelete(@PathVariable("customerId") UUID customerId) {

        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomerById(@PathVariable("customerId") UUID customerId,
                                            @RequestBody Customer customer){

        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
