package com.example.AdsCampaign.controller;

import com.example.AdsCampaign.model.Account;
import com.example.AdsCampaign.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAccounts() {
        when(accountService.getAllAccounts()).thenReturn(Collections.singletonList(new Account()));
        List<Account> accounts = accountController.getAllAccounts();
        assertEquals(1, accounts.size());
        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    void testGetAccountById() {
        String id = "testId";
        Account account = new Account();
        when(accountService.getAccountById(id)).thenReturn(account);
        ResponseEntity<Account> response = accountController.getAccountById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account, response.getBody());
        verify(accountService, times(1)).getAccountById(id);
    }

    @Test
    void testAddAccount() {
        Account account = new Account();
        ResponseEntity<String> response = accountController.addAccount(account);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(accountService, times(1)).addAccount(account);
    }

    @Test
    void testUpdateAccount() {
        String id = "testId";
        Account account = new Account();
        ResponseEntity<String> response = accountController.updateAccount(id, account);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(accountService, times(1)).updateAccount(id, account.getName(), account.getStatus());
    }
}
