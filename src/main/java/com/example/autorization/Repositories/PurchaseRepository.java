package com.example.autorization.Repositories;

import com.example.autorization.Models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepository {

    private final JdbcTemplate jdbs;


    public PurchaseRepository(JdbcTemplate jdbs) {
        this.jdbs = jdbs;
    }


    public List<Product> findAllPurchases(Product product) { //Определяем запрос SELECT для получения всех записей из таблицы покупок

        String sql = "SELECT * FROM products WHERE ";
        if (product.getId()!=0){
            sql+="id = "+ product.getId();
        }
        if(product.getHeader()!=null){
            if(product.getId()!=0){
                sql+=" and header = \'"+product.getHeader()+"\'";
            }else{
                sql+="header = \'"+product.getHeader() + "\'";
            }
        }
        if(product.getDescription()!=null){
            if(product.getId()!=0 || product.getHeader()!=null){
                sql+=" and description = \'"+product.getDescription()+"\'";
            }else{
                sql+="description = \'"+product.getDescription()+"\'";
            }
        }
        if(product.getPrice()!=null){
            if(product.getId()!=0 || product.getHeader()!=null || product.getDescription()!=null){
                sql+=" and price = \'"+product.getPrice()+"\'";
            }else{
                sql+="price = \'"+product.getPrice()+"\'";
            }
        }
        if(product.getStatus()!=null){
            if(product.getId()!=0 || product.getHeader()!=null || product.getDescription()!=null || product.getPrice()!=null){
                sql+=" and status = \'"+product.getStatus()+"\'";
            }else{
                sql+="status = "+product.getStatus();
            }
        }
        if(product.getSeller()!=null){
            if(product.getId()!=0 || product.getHeader()!=null || product.getDescription()!=null || product.getPrice()!=null || product.getStatus()!=null){
                sql+=" and seller = \'"+product.getSeller()+"\'";
            }else{
                sql+="seller = \'"+product.getSeller()+"\'";
            }
        }
        RowMapper<Product> purchaseRowMapper = (r, i) -> {
            Product rowObject = new Product();
            rowObject.setId(r.getInt("id"));
            rowObject.setHeader(r.getString("header"));
            rowObject.setDescription(r.getString("description"));
            rowObject.setPrice(r.getString("price"));
            rowObject.setSeller(r.getString("seller"));
            rowObject.setBuyer(r.getString("buyer"));
            rowObject.setStatus(r.getString("status"));
            rowObject.setEnd_date(r.getString("end_date"));

            return rowObject;
        };

        return jdbs.query(sql, purchaseRowMapper);

}
}
