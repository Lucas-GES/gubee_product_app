package com.product.adapter.out.persistence.factory;

import com.product.adapter.out.persistence.InMemoryProductRepository;
import com.product.application.port.in.ProductRepository;

public class InMemoryRepositoryFactory implements RepositoryFactory {

    public ProductRepository create() {
        return new InMemoryProductRepository();
    }

}
