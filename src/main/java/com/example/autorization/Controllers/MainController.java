package com.example.autorization.Controllers;

import com.example.autorization.Models.Product;
import com.example.autorization.Repositories.ProductRepository;
import com.example.autorization.Services.LoggedUserManagementService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class MainController {

    private final ProductRepository productRepository;
    private final LoggedUserManagementService loggedUserManagementService;


    public MainController(ProductRepository productRepository, LoggedUserManagementService loggedUserManagementService) {
        this.productRepository = productRepository;
        this.loggedUserManagementService = loggedUserManagementService;

    }


    @GetMapping("/main")
    public Iterable<Product> bulletinBoard(){
        // (loggedUserManagementService.getUsername()!=null) {
            return productRepository.findAll();
      //  }else{
       //     return new ArrayList<>();
        //}
    }
    @PostMapping("/sell")
    public String sellProduct(@RequestBody Product product){
        product.setSeller(loggedUserManagementService.getUsername());
        if(product.getHeader()!=null && product.getDescription()!=null && product.getPrice()!=null && product.getSeller()!=null){
            productRepository.setProduct(product.getHeader(), product.getDescription(), product.getPrice(), product.getSeller());
            return "Обьявление опубликовано";
        }else if (product.getSeller()==null) {
            return "Войдите в систему";
        }else{
            return "Проверьте заполненность данных";
        }
    }
    @PostMapping("buy")
    public String buyProduct(@RequestParam int id, String price){
        Product product = productRepository.findProductById(id);

        if(loggedUserManagementService.getUsername()!=null && !loggedUserManagementService.getUsername().equals(product.getSeller())) {
            if(Long.parseLong(price) > Long.parseLong(product.getPrice())) {
                productRepository.updateBuyers(id, loggedUserManagementService.getUsername(), price);

                if(product.getEnd_date() == null){
                    long time = System.currentTimeMillis() + 18000000;
                    String needTime = Long.toString(time);
                    productRepository.setDate(id, needTime);
                }
                return "Ставка поставлена, если в течении 5 часов вас не перебьют вы выйграете слот";
            }else {
                return "Ставка не принята поставьте большую цену";
            }
        }else{
            return "Отказано";
        }
    }

}
