package org.example.service.impl;

import org.example.dao.WalletDao;
import org.example.exception.WalletNotFoundException;
import org.example.exception.WalletValidationException;
import org.example.model.Wallet;
import org.example.service.WalletManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WalletManagementImpl implements WalletManagement {

    private WalletDao walletDao;

    @Autowired
    public WalletManagementImpl(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    @Override
    public Wallet create(String walletName) {
        if (walletName == null) throw new WalletValidationException("Param is not valid.", "Wallet Name");
        Wallet wallet = new Wallet(walletName);
        return walletDao.createWallet(wallet);
    }

    @Override
    public Wallet getById(String id) {
        Optional<Wallet> optionalWallet = walletDao.findWallet(id);
        if (optionalWallet.isEmpty()) throw new WalletNotFoundException("Wallet not found.");
        return optionalWallet.get();
    }
}
