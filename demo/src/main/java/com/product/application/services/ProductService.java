package com.product.application.services;

import com.product.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {

    public List<Product> instantiateProduct(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while(rs.next()) {
            Product product = new Product(
                    rs.getInt("product.id"),
                    rs.getString("product.name"),
                    rs.getString("product.description"),
                    rs.getString("market.name"),
                    rs.getString("technologies.name"));
            products.add(product);
        }

        return getDistinctProducts(products);
    }

    private Product mergeProduct(Product a, Product b){
        if(a.getTechnology() != b.getTechnology()) {
            a.insertTechByTech(b.getTechnology());
        }
        return a;
    }

    private List<Product> getDistinctProducts(List<Product> products){
        var groupedById
                = products.stream().collect(Collectors.groupingBy(Product::getId));
        return groupedById.values().stream()
                .map(group -> {
                    var first = group.iterator().next();
                    group.forEach(next -> mergeProduct(first, next));
                    return first;
                }).collect(Collectors.toList());
    }

}
