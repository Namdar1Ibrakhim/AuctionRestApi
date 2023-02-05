package com.example.autorization.Models;

import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.time.LocalDate;

public class Product {

    @Id
    private int id;

    private String header;
    private String description;
    private String price;
    private String seller;
    private String buyer;
    private String status;
    private String end_date;

    public String getEnd_date() {
        return end_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String getHeader() {
        return header;
    }

    public String getPrice() {
        return price;
    }

    public void setDescrtiption(String descrtiption) {
        this.description = descrtiption;
    }
    public void setHeader(String header) {
        this.header = header;
    }
}
