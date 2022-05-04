package com.product.application.port.in;

import com.product.domain.entities.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> filterMarket(String name);
    List<Product> filterTechnologies(List<String> name);
}
