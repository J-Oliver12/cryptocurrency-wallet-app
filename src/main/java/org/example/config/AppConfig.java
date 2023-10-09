package org.example.config;


import org.example.dao.impl.TransactionDaoImpl;
import org.example.dao.impl.WalletDaoImpl;
import org.example.model.Transaction;
import org.example.service.impl.TransactionManagementImpl;
import org.example.service.impl.WalletManagementImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {

    /*@Bean
    public TransactionDaoImpl transactionDao() {
        return new TransactionDaoImpl();
    }

    @Bean
    public WalletDaoImpl walletDao(){
        return new WalletDaoImpl();
    }

    @Bean
    public TransactionManagementImpl transactionManagement() {
        return new TransactionManagementImpl(walletDao(), transactionDao());
    }

    @Bean
    public WalletManagementImpl walletManagement() {
        return new WalletManagementImpl(walletDao());
    }*/


}
