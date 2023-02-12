package com.example.autorization.Services;

import com.example.autorization.Models.Product;
import com.example.autorization.Repositories.ProductRepository;
import com.example.autorization.Repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {
    private final PurchaseRepository purchaseRepository;

    public FilterService(ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Product> filter(Product product){

        return purchaseRepository.findAllPurchases(product);
    }

}
