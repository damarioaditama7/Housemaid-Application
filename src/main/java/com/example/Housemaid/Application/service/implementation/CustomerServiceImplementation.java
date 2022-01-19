package com.example.Housemaid.Application.service.implementation;

import com.example.Housemaid.Application.entity.Customer;
import com.example.Housemaid.Application.exception.NotFoundException;
import com.example.Housemaid.Application.repository.CustomerRepository;
import com.example.Housemaid.Application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        customer.setIsDeleted(false);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public Stream<Customer> getByName(String name) {
        List<Customer> customerList = customerRepository.findCustomerByName(name.toLowerCase());
        Stream.Builder<Customer> builder = Stream.builder();
        for (Customer customer : customerList) {
            builder.add(customer);
        }
        return builder.build();
    }

    @Override
    public Customer update(Customer customer) {
        Customer validation = getById(customer.getId());
        customer.setCreatedAt(validation.getCreatedAt());
        customer.setIsDeleted(validation.getIsDeleted());
        return customerRepository.save(customer);
    }

    @Override
    public String deleteById(String id) {
        Customer customer = getById(id);
        if (!customer.getIsDeleted()){
            customer.setIsDeleted(true);
            customerRepository.save(customer);
            return "Delete Success!";
        }
        else{
            throw new NotFoundException("Already deleted!");
        }

    }

    @Override
    public Stream<Customer> findAll() {
        List<Customer> customerList= customerRepository.findAll();
        Stream.Builder<Customer> builder = Stream.builder();
        for (Customer customer : customerList) {
            builder.add(customer);
        }
        return builder.build();
    }

}
