package org.example.dao;

import org.example.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDao {

    Transaction createTransaction(Transaction transaction);

    Optional<Transaction> findById(String transactionId);

    List<Transaction> findTransactionByWalletId(String walletId);
}
