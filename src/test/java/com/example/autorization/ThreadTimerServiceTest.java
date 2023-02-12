package com.example.autorization;

import com.example.autorization.Models.Product;
import com.example.autorization.Repositories.ProductRepository;
import com.example.autorization.Services.ThreadTimerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ThreadTimerServiceTest {
    @Mock
    private ProductRepository productRepository;

    private ThreadTimerService threadTimerService;

    @Before
    public void setUp() {
        threadTimerService = new ThreadTimerService(productRepository);
    }

    @Test
    public void startCheck_whenCalled_shouldChangeProductStatusToSold() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        product.setStatus("active");
        product.setEnd_date(String.valueOf(System.currentTimeMillis() - 1000));
        product.setBuyer("buyer1");
        products.add(product);

        when(productRepository.findProducts()).thenReturn(products);

        threadTimerService.startCheck();

        verify(productRepository, timeout(3000).atLeastOnce()).prodano(1);
    }
}