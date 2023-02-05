package com.example.autorization.Controllers;

import com.example.autorization.Components.RegisterProcessor;
import com.example.autorization.Models.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final RegisterProcessor registerProcessor;

    public RegisterController(RegisterProcessor registerProcessor) {
        this.registerProcessor = registerProcessor;
    }
    @PostMapping("/register")
    public String register(@RequestBody Account account){
        boolean otv;
        otv = registerProcessor.registr(account.getEmail(), account.getPassword());
        if(otv==true){
            return "Успешная регистрация, Войдите в систему";
        }else{
            return "Такой аккаунт уже существует";
        }
    }
}
