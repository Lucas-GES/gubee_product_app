package com.product.adapter.out.persistence.factory;

import com.product.application.port.in.ProductRepository;

import static com.product.adapter.out.persistence.factory.RepositoryFactoryEnum.H2FACTORY;
import static com.product.adapter.out.persistence.factory.RepositoryFactoryEnum.MEMORYREPOSITORY;

public interface RepositoryFactory {

    String defaultRepository = "MEMORYREPOSITORY";

    static ProductRepository createDefaultFactory() {
        return createFactory(RepositoryFactoryEnum.valueOf(defaultRepository));
    }

    static ProductRepository createFactory(RepositoryFactoryEnum repository) {
        ProductRepository productRepository;
        switch (repository){
            case MEMORYREPOSITORY:
                productRepository =  new InMemoryRepositoryFactory().create();
                break;
            case H2FACTORY:
                productRepository =  new DBRepositoryFactory().create();
                break;
            default: productRepository =  createDefaultFactory();
        }
        return productRepository;
    }
}
