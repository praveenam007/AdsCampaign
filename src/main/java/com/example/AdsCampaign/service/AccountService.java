package com.example.AdsCampaign.service;

import com.example.AdsCampaign.model.Account;
import com.example.AdsCampaign.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    public Account getAccountById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public void addAccount(Account account) {
        if (repository.existsById(account.getId())) {
            throw new RuntimeException("Account already exists");
        } else {
            repository.save(account);
        }
    }

    public void updateAccount(String id, String name, String status) {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setName(name);
        account.setStatus(status);
        repository.save(account);
    }
}
