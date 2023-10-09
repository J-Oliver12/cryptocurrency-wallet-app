package org.example.service.impl;

import org.example.dao.TransactionDao;
import org.example.dao.WalletDao;
import org.example.exception.InsufficientBalanceException;
import org.example.exception.WalletNotFoundException;
import org.example.model.CryptoCurrency;
import org.example.model.Transaction;
import org.example.model.Wallet;
import org.example.service.TransactionManagement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionManagementImpl implements TransactionManagement {

    private WalletDao walletDao;
    private TransactionDao transactionDao;

    public TransactionManagementImpl(WalletDao walletDao, TransactionDao transactionDao) {
        this.walletDao = walletDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public Transaction createDepositTransaction(String walletId, CryptoCurrency cryptoCurrency, BigDecimal amount, String description) {

        if (walletId == null || cryptoCurrency == null || amount == null)
            throw new IllegalArgumentException("Wallet params were not valid.");

        Optional<Wallet> optionalWallet = walletDao.findWallet(walletId);
        if (optionalWallet.isEmpty()) throw new WalletNotFoundException("Wallet not found.");

        Wallet wallet = optionalWallet.get();
        wallet.deposit(cryptoCurrency, amount);

        Transaction transaction = new Transaction("DEPOSIT", amount, walletId, cryptoCurrency.getName());
        transaction.setDescription(description);

        Transaction createdTransaction = transactionDao.createTransaction(transaction);

        return createdTransaction;
    }


    @Override
    public Transaction createWithdrawalTransaction(String walletId, CryptoCurrency cryptoCurrency, BigDecimal amount, String description) {

        if (walletId == null || cryptoCurrency == null || amount == null)
            throw new IllegalArgumentException("Wallet params were not valid.");

        Optional<Wallet> optionalWallet = walletDao.findWallet(walletId);
        if (optionalWallet.isEmpty()) throw new WalletNotFoundException("Wallet not found.");

        Wallet wallet = optionalWallet.get();

        if (wallet.getBalance(cryptoCurrency).compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
        }

        wallet.withdraw(cryptoCurrency, amount);

        Transaction transaction = new Transaction("WITHDRAWAL", amount.negate(), walletId, cryptoCurrency.getName());
        transaction.setDescription(description);

        Transaction createdTransaction = transactionDao.createTransaction(transaction);

        return createdTransaction;
    }


    @Override
    public List<Transaction> getTransactionByWalletId(String walletId) {
        List<Transaction> transactions = transactionDao.findTransactionByWalletId(walletId);
        return transactions;
    }

}
