package com.example.Housemaid.Application.repository;

import com.example.Housemaid.Application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "SELECT * FROM mst_customer WHERE is_deleted=false", nativeQuery = true)
    List<Customer> findAll();

    @Query(value = "SELECT * FROM mst_customer WHERE id = :id AND is_deleted=false", nativeQuery = true)
    Customer findCustomerById(String id);

    @Query(value = "SELECT * FROM mst_customer WHERE LOWER(name) LIKE %:name% AND is_deleted=false",nativeQuery = true)
    List<Customer> findCustomerByName(String name);


}
