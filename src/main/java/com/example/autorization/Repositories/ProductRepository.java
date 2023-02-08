package com.example.autorization.Repositories;

import com.example.autorization.Models.Product;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ProductRepository extends CrudRepository<Product, Long> {


    @Modifying
    @Query("INSERT INTO products values(NULL , :header, :description, :price, :seller, NULL, 'active', NULL)")
    void setProduct(String header, String description, String price, String seller);

    @Query("SELECT * FROM products where status='active' and end_date!=null")
    Iterable<Product> findAll();

    @Query("SELECT * from products where id =:id")
    Product findProductById(int id);

    @Modifying
    @Query("UPDATE products SET buyer = :buyer, price = :price  where id = :id")
    void updateBuyers(int id, String buyer, String price);

    @Modifying
    @Query("UPDATE products SET status = 'inactive' where id = :id")
    void prodano(int id);

    @Query("SELECT * FROM products")
    Iterable<Product> findProducts();

    @Modifying
    @Query("UPDATE products set end_date = :date where id = :id")
    void setDate(int id, String date);
}
