package com.example.Housemaid.Application.controller;

import com.example.Housemaid.Application.WebResponse;
import com.example.Housemaid.Application.entity.Customer;
import com.example.Housemaid.Application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer request) {
        Customer customer = customerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") String id) {
        Customer customer = customerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping()
    public ResponseEntity<Stream<Customer>> getAllCustomer() {
        Stream<Customer> stream = customerService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(stream);
    }

    @GetMapping("/name/{customerName}")
    public ResponseEntity<Stream<Customer>> getCustomerByName(@PathVariable("customerName") String name) {
        Stream<Customer> customer = customerService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<WebResponse<String>> deleteCustomerById(@PathVariable("customerId") String id) {
        String message = customerService.deleteById(id);
        WebResponse<String> response =
                new WebResponse<>(String.format("Delete customer with id: %s", id), message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping()
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        Customer request = customerService.update(customer);
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }


}
