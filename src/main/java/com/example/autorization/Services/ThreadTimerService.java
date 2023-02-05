package com.example.autorization.Services;

import com.example.autorization.Models.Product;
import com.example.autorization.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class ThreadTimerService {

    private final ProductRepository productRepository;

    public ThreadTimerService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void startCheck() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run(){
                        Iterable<Product> products1 = productRepository.findProducts();
                        for (Product product : products1) {
                            if (product.getEnd_date() != null) {
                                if (product.getStatus().equals("active") && Long.parseLong(product.getEnd_date()) < System.currentTimeMillis()) {
                                    productRepository.prodano(product.getId());
                                    System.out.println("Товар слота " + product.getId() + "продан. Победитель слота" + product.getBuyer());
                                }
                            }
                        }
                        //System.out.println("Проверяется");
                    }
                };
                timer.scheduleAtFixedRate(timerTask, 0, 1000);

            }
        });
        thread.start();
    }

}