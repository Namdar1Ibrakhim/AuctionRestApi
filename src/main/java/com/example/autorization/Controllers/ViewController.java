package com.example.autorization.Controllers;

import com.example.autorization.Models.Product;
import com.example.autorization.Repositories.PurchaseRepository;
//import com.example.autorization.Services.FilterService;
import com.example.autorization.Services.FilterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewController{

    private final FilterService filterService;

    public ViewController(FilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping("/filter")
    public List<Product> filter(@RequestBody Product product){
        return filterService.filter(product);
    }
}
