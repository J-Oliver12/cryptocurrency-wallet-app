package org.example;

import org.example.config.AppConfig;
import org.example.model.CryptoCurrency;
import org.example.model.Transaction;
import org.example.model.Wallet;
import org.example.service.TransactionManagement;
import org.example.service.WalletManagement;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WalletManagement walletManagement = context.getBean(WalletManagement.class);
        TransactionManagement transactionManagement = context.getBean(TransactionManagement.class);

        Wallet myWallet = walletManagement.create("my-first-wallet");
        Transaction depositTransactionForBTC = transactionManagement.createDepositTransaction(
                myWallet.getId(),
                CryptoCurrency.BTC,
                new BigDecimal(1),
                "Test");

        System.out.println(walletManagement.getById(myWallet.getId()));

        Transaction depositTransactionForETH = transactionManagement.createDepositTransaction(
                myWallet.getId(),
                CryptoCurrency.ETH,
                new BigDecimal(10),
                "Test");

        System.out.println(walletManagement.getById(myWallet.getId()));

        Transaction depositTransactionForBTC2 = transactionManagement.createDepositTransaction(
                myWallet.getId(),
                CryptoCurrency.BTC,
                new BigDecimal(2),
                "Test");

        System.out.println(walletManagement.getById(myWallet.getId()));


    }

}
