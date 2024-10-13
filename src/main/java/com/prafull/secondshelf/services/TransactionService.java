package com.prafull.secondshelf.services;

import com.prafull.secondshelf.dto.TransactionDto;
import com.prafull.secondshelf.model.Book;
import com.prafull.secondshelf.model.Transaction;
import com.prafull.secondshelf.model.UserEntity;
import com.prafull.secondshelf.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction saveTransaction(TransactionDto transactionDto, UserEntity seller, UserEntity buyer, Book book) {
        Transaction transaction = new Transaction(transactionDto);
        transaction.setSeller(seller);
        transaction.setBuyer(buyer);
        transaction.setBook(book);
        return transactionRepository.save(transaction);
    }
}
