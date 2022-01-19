package com.example.Housemaid.Application.service;

import com.example.Housemaid.Application.entity.Customer;

import java.util.List;
import java.util.stream.Stream;

public interface CustomerService {
    Customer create(Customer customer);

    Customer getById(String id);

    Stream<Customer> getByName(String name);

    Customer update(Customer customer);

    String deleteById(String id);

    Stream<Customer> findAll();
}
