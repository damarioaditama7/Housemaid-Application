package com.example.Housemaid.Application.service.implementation;

import com.example.Housemaid.Application.entity.Maid;
import com.example.Housemaid.Application.entity.Transaction;
import com.example.Housemaid.Application.exception.NotFoundException;
import com.example.Housemaid.Application.repository.TransactionRepository;
import com.example.Housemaid.Application.service.CustomerService;
import com.example.Housemaid.Application.service.MaidService;
import com.example.Housemaid.Application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TransactionServiceImplementation implements TransactionService {
    private final TransactionRepository transactionRepository;

    private final MaidService maidService;

    private final CustomerService customerService;

    @Override
    public Transaction create(Transaction transaction) {
        Maid maid = maidService.getById(transaction.getMaid().getId());
        if (maid.getIsEnable()) {
            transaction.setPaymentStatus(false);
            transaction.setCancellation(false);
            transaction.setIsMaidAccept(false);
            transaction.setDownPayment(maid.getPricePerMonth()/5);
            return transactionRepository.save(transaction);
        } else {
            throw new NotFoundException("Maid not available");
        }
    }

    @Override
    public Transaction getById(String id) {
        return transactionRepository.findTransactionById(id);
    }

    @Override
    public Stream<Transaction> findAll() {
        List<Transaction> transactionList = transactionRepository.findAll();
        Stream.Builder<Transaction> builder = Stream.builder();
        for (Transaction transaction : transactionList) {
            builder.add(transaction);
        }
        return builder.build();
    }

    @Override
    public Stream<Transaction> findAllByCustomerId(String customerId) {
        List<Transaction> transactionList = transactionRepository.findAllTransactionByCustomerId(customerId);
        Stream.Builder<Transaction> builder = Stream.builder();
        for (Transaction transaction : transactionList) {
            builder.add(transaction);
        }
        return builder.build();
    }

    @Override
    public Stream<Transaction> findAllByMaidId(String maidId) {
        List<Transaction> transactionList = transactionRepository.findAllTransactionByMaidId(maidId);
        Stream.Builder<Transaction> builder = Stream.builder();
        for (Transaction transaction : transactionList) {
            builder.add(transaction);
        }
        return builder.build();
    }

    @Override
    public Transaction maidAcceptJob(String id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        Maid maid = maidService.getById(transaction.getMaid().getId());
        if(!transaction.getIsMaidAccept()){
            transaction.setIsMaidAccept(true);
            maid.setIsEnable(false);
            return transactionRepository.save(transaction);
        }else{
            throw new NotFoundException("Already accepted");
        }
    }

    @Override
    public Transaction cancellation(String id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        Maid maid = maidService.getById(transaction.getMaid().getId());
        if(!transaction.getCancellation()){
            transaction.setCancellation(true);
            maid.setIsEnable(true);
            return transactionRepository.save(transaction);
        }else{
            throw new NotFoundException("Already canceled");
        }
    }

    @Override
    public Transaction payment(String id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        if(!transaction.getPaymentStatus()){
            transaction.setPaymentStatus(true);
            return transactionRepository.save(transaction);
        }else{
            throw new NotFoundException("Already paid");
        }
    }
}
