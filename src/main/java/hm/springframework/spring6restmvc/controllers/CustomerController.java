package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.Customer;
import hm.springframework.spring6restmvc.services.CustomerService;
import hm.springframework.spring6restmvc.services.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<Customer> handleCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity<>(savedCustomer, headers, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> handlePutCustomer(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {

        customerService.updateCustomerById(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Customer> handleDelete(@PathVariable("customerId") UUID customerId) {

        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{customerId}")
    public ResponseEntity patchCustomerById(@PathVariable("customerId") UUID customerId,
                                            @RequestBody Customer customer){

        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
