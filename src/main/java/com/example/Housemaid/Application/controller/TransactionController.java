package com.example.Housemaid.Application.controller;

import com.example.Housemaid.Application.entity.Transaction;
import com.example.Housemaid.Application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction request){
        Transaction transaction = transactionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionId") String transactionId){
        Transaction transaction = transactionService.getById(transactionId);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @GetMapping()
    public ResponseEntity<Stream<Transaction>> getAllTransaction(){
        Stream<Transaction> stream = transactionService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(stream);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Stream<Transaction>> getAllTransactionByCustomerId(@PathVariable("customerId") String id){
        Stream<Transaction> stream = transactionService.findAllByCustomerId(id);
        return ResponseEntity.status(HttpStatus.OK).body(stream);
    }

    @GetMapping("/maid/{maidId}")
    public ResponseEntity<Stream<Transaction>> getAllTransactionByMaidId(@PathVariable("maidId") String id){
        Stream<Transaction> stream = transactionService.findAllByMaidId(id);
        return ResponseEntity.status(HttpStatus.OK).body(stream);
    }

    @PutMapping("/maid/accept/{transactionId}")
    public ResponseEntity<Transaction> maidAcceptJob(@PathVariable("transactionId") String id){
        Transaction transaction = transactionService.maidAcceptJob(id);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @PutMapping("/cancellation/{transactionId}")
    public ResponseEntity<Transaction> cancellation(@PathVariable("transactionId") String id){
        Transaction transaction = transactionService.cancellation(id);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @PutMapping("/payment/{transactionId}")
    public ResponseEntity<Transaction> payment(@PathVariable("transactionId") String id){
        Transaction transaction = transactionService.payment(id);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }
}
