package com.example.autorization;

import com.example.autorization.Controllers.MainController;
import com.example.autorization.Repositories.ProductRepository;
import com.example.autorization.Services.LoggedUserManagementService;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.autorization.Models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@SpringBootTest
public class MainControllerTest {
    @Autowired
    private MainController mainController;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private LoggedUserManagementService loggedUserManagementService;

    @Test
    public void testBulletinBoard() {
        Product product = new Product();
        product.setHeader("Test Header");
        product.setDescription("Test Description");
        product.setPrice("1000");
        product.setSeller("Test Seller");

        when(productRepository.findAll()).thenReturn(new ArrayList<Product>() {{
            add(product);
        }});

        Iterable<Product> products = mainController.bulletinBoard();
        assertNotNull(products);
        assertEquals("Test Header", products.iterator().next().getHeader());
    }

    @Test
    public void testSellProduct_withValidData() {
        Product product = new Product();
        product.setHeader("Test Header");
        product.setDescription("Test Description");
        product.setPrice("1000");
        product.setSeller(null);

        when(loggedUserManagementService.getUsername()).thenReturn("Test Seller");
        Mockito.doNothing().when(productRepository).setProduct(anyString(), anyString(), anyString(), anyString());

        String result = mainController.sellProduct(product);
        assertEquals("Обьявление опубликовано", result);
    }

    @Test
    public void testSellProduct_withoutLogin() {
        Product product = new Product();
        product.setHeader("Test Header");
        product.setDescription("Test Description");
        product.setPrice("1000");
        product.setSeller(null);

        when(loggedUserManagementService.getUsername()).thenReturn(null);

        String result = mainController.sellProduct(product);
        assertEquals("Войдите в систему", result);
    }
    Product product;

    @Before
    public void setup() {
        product = new Product();
        product.setId(1);
        product.setSeller("seller");
        product.setPrice("100");
        product.setEnd_date(null);
    }

    @Test
    public void testBuyProduct_userNotLoggedIn() {
        when(loggedUserManagementService.getUsername()).thenReturn(null);
        String result = mainController.buyProduct(1, "150");
        assertEquals("Отказано", result);
    }

}




