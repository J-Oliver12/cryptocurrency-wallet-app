package org.example.service;

import org.example.model.Wallet;

public interface WalletManagement {

    Wallet create(String walletName);

    Wallet getById(String id);

    //To add more methods...

}
