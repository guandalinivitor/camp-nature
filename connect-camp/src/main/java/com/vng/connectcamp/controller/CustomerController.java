package com.vng.connectcamp.controller;

import com.vng.connectcamp.model.Customer;
import com.vng.connectcamp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> listAllCustomers() { return customerRepository.findAll(); }

    @GetMapping("/{id}")
    public Optional<Customer> findCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody Customer customer) {
        customer = customerRepository.save(customer);
        customer.add(WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customer.getId()).withSelfRel());

        customer.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .listAllCustomers()).withRel(IanaLinkRelations.COLLECTION));
        return customer;
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
}
