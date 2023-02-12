package com.example.autorization;

import com.example.autorization.Controllers.RegisterController;
import com.example.autorization.Models.Account;
import com.example.autorization.Components.RegisterProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RegisterControllerTest {
    private RegisterProcessor registerProcessor = Mockito.mock(RegisterProcessor.class);

    @Test
    public void registerSuccessTest() {
        Account account = new Account();
        account.setEmail("example@example.com");
        account.setPassword("password");
        when(registerProcessor.registr(anyString(), anyString())).thenReturn(true);

        RegisterController registerController = new RegisterController(registerProcessor);
        String result = registerController.register(account);
        assertEquals("Успешная регистрация, Войдите в систему", result);
    }

    @Test
    public void registerFailureTest() {
        Account account = new Account();
        account.setEmail("example@example.com");
        account.setPassword("password");
        when(registerProcessor.registr(anyString(), anyString())).thenReturn(false);

        RegisterController registerController = new RegisterController(registerProcessor);
        String result = registerController.register(account);
        assertEquals("Такой аккаунт уже существует", result);
    }
}