package com.example.autorization;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.autorization.Components.LoginProcessor;
import com.example.autorization.Repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.autorization.Models.Account;
import com.example.autorization.Services.LoggedUserManagementService;

@ExtendWith(MockitoExtension.class)
public class LoginProcessorTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private LoggedUserManagementService loggedUserManagementService;

    private LoginProcessor loginProcessor;

    @BeforeEach
    public void setUp() {
        loginProcessor = new LoginProcessor(loggedUserManagementService, accountRepository);
    }

    @Test
    public void testLogin_success() {
        String username = "testuser";
        String password = "testpassword";

        Account account = new Account();
        account.setEmail(username);
        account.setPassword(password);

        when(accountRepository.findAccount(username)).thenReturn(account);

        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);

        boolean loginResult = loginProcessor.login();

        assertTrue(loginResult);
    }

    @Test
    public void testLogin_failure_userNotFound() {
        String username = "testuser";
        String password = "testpassword";

        when(accountRepository.findAccount(username)).thenReturn(null);

        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);

        boolean loginResult = loginProcessor.login();

        assertFalse(loginResult);
    }

    @Test
    public void testLogin_failure_incorrectPassword() {
        String username = "testuser";
        String password = "testpassword";

        Account account = new Account();
        account.setEmail(username);
        account.setPassword("incorrectPassword");

        when(accountRepository.findAccount(username)).thenReturn(account);

        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);

        boolean loginResult = loginProcessor.login();

        assertFalse(loginResult);
    }

}
