package com.product.application.resources;


import com.product.application.repositories.ProductRepository;
import com.product.application.services.ProductService;
import com.product.db.DbException;
import com.product.entities.Product;
import com.product.entities.Technology;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductResource implements ProductRepository {

    private Connection conn;
    ProductService productService = new ProductService();

    public ProductResource(Connection conn){this.conn = conn;}

    private List<Product> prepareStatement(String query){
        try(PreparedStatement st = conn.prepareStatement(query)){
            try(ResultSet rs = st.executeQuery()){
                List<Product> list = new ArrayList<>();

                list.addAll(productService.instantiateProduct(rs));

                return list;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        String query = ("""
                        SELECT product.id,product.name, product.description, market.name, technologies.name
                        FROM product
                        join market on product.marketId = market.id
                        join product_tech on product_tech.product_id = product.id
                        join technologies on product_tech.tech_id = technologies.id""");

        return prepareStatement(query);
    }

    @Override
    public List<Product>filterMarket(String name){
        return findAll().stream().filter(p -> p.getMarket().getName().equals(name)).collect(Collectors.toList());
    }


    @Override
    public List<Product> filterTechnologies(List<String> names) {
        List<Product> list = new ArrayList<>();
        for(Product p : findAll()){
            for(Technology t : p.getTechnology()){
                if(names.contains(t.getName())){
                    list.add(p);
                }
            }
        }
        return list;
    }
}
