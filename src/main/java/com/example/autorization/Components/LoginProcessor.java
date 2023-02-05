package com.example.autorization.Components;


import com.example.autorization.Models.Account;
import com.example.autorization.Repositories.AccountRepository;
import com.example.autorization.Services.LoggedUserManagementService;
import org.springframework.stereotype.Component;


@Component
public class LoginProcessor {
    private final LoggedUserManagementService loggedUserManagementService;
    private final AccountRepository accountRepository;


    private String username;
    private String password;

    public LoginProcessor(LoggedUserManagementService loggedUserManagementService, AccountRepository accountRepository) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.accountRepository = accountRepository;
    }

    public boolean login(){

        String username = this.getUsername();
        String password = this.getPassword();

        boolean loginResult = false;

        Account account = accountRepository.findAccount(username);
        if(account==null){
            return loginResult;
        }else {
            if (account.getEmail().equals(username) && account.getPassword().equals(password)) {
                loginResult = true;
                loggedUserManagementService.setUsername(username);
            }
        }
        return loginResult;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

