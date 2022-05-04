package com.product.adapter.out.persistence.utils;

import com.product.adapter.out.persistence.InMemoryProductRepository;
import com.product.adapter.out.persistence.ProductRepositoryImplementation;
import com.product.application.port.in.ProductRepository;

public class RepositoryFactory implements AbstractFactory{
    @Override
    public ProductRepository create() {
        return new InMemoryProductRepository();
    }
}
