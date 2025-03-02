package hm.springframework.spring6restmvc.services;

import hm.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    public List<Customer> findAllCustomers();
    public Customer findCustomerById(UUID id);

    Customer saveNewCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);
}
