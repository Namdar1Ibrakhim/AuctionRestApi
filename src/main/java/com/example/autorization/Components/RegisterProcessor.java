package com.example.autorization.Components;

import com.example.autorization.Models.Account;
import com.example.autorization.Repositories.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisterProcessor{

    private final AccountRepository accountRepository;

    public RegisterProcessor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean registr(String email, String password) {
        Account account = accountRepository.findAccount(email);
        if (account != null) {
            return false;
        }else {
            accountRepository.setData(email, password);
            return true;
        }
    }
}
