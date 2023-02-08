package com.example.autorization;

import com.example.autorization.Components.LoginProcessor;
import com.example.autorization.Components.RegisterProcessor;
import com.example.autorization.Models.Account;
import com.example.autorization.Repositories.AccountRepository;
import com.example.autorization.Services.LoggedUserManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class AutorizationApplicationTests {

    @Test
    void moduleTest1(){
        LoggedUserManagementService loggedUserManagementService = mock(LoggedUserManagementService.class);
        AccountRepository accountRepository = mock(AccountRepository.class);
        LoginProcessor loginProcessor = new LoginProcessor(loggedUserManagementService, accountRepository);

        Account account = new Account();
        account.setEmail("ibra@mail.ru");
        account.setPassword("12345");

        loginProcessor.setUsername(account.getEmail());
        loginProcessor.setPassword(account.getPassword());

        loggedUserManagementService.setUsername(account.getEmail());

        given(accountRepository.findAccount(account.getEmail())).willReturn(account);
        loginProcessor.login();
    }

    @Test
    void moduleTest2(){
        AccountRepository accountRepository = mock(AccountRepository.class);
        RegisterProcessor registerProcessor = new RegisterProcessor(accountRepository);
        Account account = new Account();
        account.setEmail("ibra@mail.ru");
        account.setPassword("12345");
        given(accountRepository.findAccount(account.getEmail()))
                .willReturn(account);


        registerProcessor.registr(account.getEmail(), account.getPassword());
        //verify(accountRepository).setData(account.getEmail(), account.getPassword());
    }

//    @Test
//    void moduleTest3(){
//
//    }

}
