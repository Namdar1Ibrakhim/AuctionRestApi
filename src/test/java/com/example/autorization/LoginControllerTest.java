package com.example.autorization;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.autorization.Components.LoginProcessor;
import com.example.autorization.Controllers.LoginController;
import com.example.autorization.Models.Account;
import com.example.autorization.Services.LoggedUserManagementService;
import com.example.autorization.Services.ThreadTimerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private LoginProcessor loginProcessor;
    @Mock
    private ThreadTimerService threadTimerService;
    @Mock
    private LoggedUserManagementService loggedUserManagementService;

    @InjectMocks
    private LoginController loginController;

    private Account account;

    @Before
    public void setUp() {
        account = new Account();
        account.setEmail("email");
        account.setPassword("password");
    }

    @Test
    public void testLoginPost_loggedIn() {
        when(loginProcessor.login()).thenReturn(true);
        String result = loginController.loginPost(account);
        assertEquals("Успешный вход", result);
        verify(threadTimerService).startCheck();
    }

    @Test
    public void testLoginPost_notLoggedIn() {
        when(loginProcessor.login()).thenReturn(false);
        String result = loginController.loginPost(account);
        assertEquals("Неправильные данные или аккаунта не существует", result);
        verify(threadTimerService, never()).startCheck();
    }

    @Test
    public void testLeavePost() {
        String result = loginController.leavePost();
        assertEquals("Вы вышли из аккаунта", result);
        verify(loggedUserManagementService).setUsername(null);
    }

}