package com.product.adapter.out.persistence.utils;

import com.product.application.port.in.ProductRepository;

public interface AbstractFactory {
    ProductRepository createInMemoryRepository();
    ProductRepository createProductRepositoryImplementation();
}
