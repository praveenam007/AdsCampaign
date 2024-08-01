
package com.example.AdsCampaign.service;

import com.example.AdsCampaign.model.Account;
import com.example.AdsCampaign.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAccounts() {
        when(accountRepository.findAll()).thenReturn(Collections.singletonList(new Account()));
        List<Account> accounts = accountService.getAllAccounts();
        assertFalse(accounts.isEmpty());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void testGetAccountById() {
        String id = "testId";
        Account account = new Account();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        Account found = accountService.getAccountById(id);
        assertEquals(account, found);
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    void testAddAccount() {
        Account account = new Account();
        accountService.addAccount(account);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testUpdateAccount() {
        String id = "testId";
        String name = "UpdatedName";
        String status = "UpdatedStatus";
        Account account = new Account();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        accountService.updateAccount(id, name, status);
        assertEquals(name, account.getName());
        assertEquals(status, account.getStatus());
        verify(accountRepository, times(1)).save(account);
    }
}
