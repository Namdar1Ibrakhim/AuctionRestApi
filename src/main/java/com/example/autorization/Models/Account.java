package com.example.autorization.Models;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;

public class Account {

    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
