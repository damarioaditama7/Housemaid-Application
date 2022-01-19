package com.example.Housemaid.Application.service;

import com.example.Housemaid.Application.entity.Transaction;

import java.util.List;
import java.util.stream.Stream;

public interface TransactionService {
    Transaction create(Transaction transaction);

    Transaction getById(String id);

    Stream<Transaction> findAll();

    Stream<Transaction> findAllByCustomerId(String customerId);

    Stream<Transaction> findAllByMaidId(String maidId);

    Transaction maidAcceptJob(String id);

    Transaction cancellation(String id);

    Transaction payment(String id);
}
