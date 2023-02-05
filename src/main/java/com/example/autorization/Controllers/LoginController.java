package com.example.autorization.Controllers;

import com.example.autorization.Components.LoginProcessor;
import com.example.autorization.Models.Account;
import com.example.autorization.Services.LoggedUserManagementService;
import com.example.autorization.Services.ThreadTimerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private final LoggedUserManagementService loggedUserManagementService;


    private final ThreadTimerService threadTimerService;
    private final LoginProcessor loginProcessor;

    public LoginController(LoggedUserManagementService loggedUserManagementService, ThreadTimerService threadTimerService, LoginProcessor logginProcessor){
        this.loggedUserManagementService = loggedUserManagementService;
        this.threadTimerService = threadTimerService;
        this.loginProcessor = logginProcessor;
    }


    @PostMapping("/login")
    public String loginPost(@RequestBody Account account) {
        loginProcessor.setUsername(account.getEmail());
        loginProcessor.setPassword(account.getPassword());
        boolean loggedIn = loginProcessor.login();
        if (loggedIn) {
            threadTimerService.startCheck();
            return "Успешный вход";
        }
        return "Неправильные данные или аккаунта не существует";
    }

    @GetMapping("/leave")
    public String leavePost(){
            loggedUserManagementService.setUsername(null);
            return "Вы вышли из аккаунта";

        }
    }



