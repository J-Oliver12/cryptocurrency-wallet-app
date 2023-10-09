package org.example.dao.impl;

import org.example.dao.WalletDao;
import org.example.model.Wallet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WalletDaoImpl implements WalletDao {


    private List<Wallet> storage;

    public WalletDaoImpl() {
        System.out.println("Wallet list is initialized an ready to use...");
        this.storage = new ArrayList<>();
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        storage.add(wallet);
        return wallet;
    }

    @Override
    public Optional<Wallet> findWallet(String id) {
        return storage.stream()
                .filter(wallet -> wallet.getId().equals(id))
                .findFirst();
    }
}
