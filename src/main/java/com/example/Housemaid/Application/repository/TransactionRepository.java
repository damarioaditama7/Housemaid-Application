package com.example.Housemaid.Application.repository;

import com.example.Housemaid.Application.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction,String> {
    @Query(value = "SELECT * FROM trx_service", nativeQuery = true)
    List<Transaction> findAll();

    @Query(value = "SELECT * FROM trx_service WHERE id = :id", nativeQuery = true)
    Transaction findTransactionById(String id);

    @Query(value = "SELECT * FROM trx_service WHERE customer_id = :customerId", nativeQuery = true)
    List<Transaction> findAllTransactionByCustomerId(String customerId);

    @Query(value = "SELECT * FROM trx_service WHERE maid_id = :maidId", nativeQuery = true)
    List<Transaction> findAllTransactionByMaidId(String maidId);
}
