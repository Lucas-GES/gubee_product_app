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
                    List.of(rs.getString("technologies.name")));
            products.add(product);
        }

        return getDistinctProducts(products);
    }

    private Product mergeProduct(Product a, Product b){
        a.insertTechByTech(b.getTechnology());
        return a;
    }

    private List<Product> getDistinctProducts(List<Product> products){
        Map<Integer, List<Product>> productList
                = products.stream().collect(Collectors.groupingBy(Product::getId));
        List<Product> mergedProducts = productList.values().stream()
                .map(group -> group.stream().reduce((a,b) -> mergeProduct(a,b)).get())
                .collect(Collectors.toList());
        return mergedProducts;
    }

}
