package org.example.dao.impl;

import org.example.dao.TransactionDao;
import org.example.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionDaoImpl implements TransactionDao {

    private List<Transaction> storage;

    public TransactionDaoImpl() {
        this.storage = new ArrayList<>();
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        storage.add(transaction);
        return transaction;
    }

    @Override
    public Optional<Transaction> findById(String transactionId) {
        return storage.stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst();
    }

    @Override
    public List<Transaction> findTransactionByWalletId(String walletId) {
        return storage.stream()
                .filter(transaction -> transaction.getWalletId().equals(walletId))
                .toList();
    }
}
