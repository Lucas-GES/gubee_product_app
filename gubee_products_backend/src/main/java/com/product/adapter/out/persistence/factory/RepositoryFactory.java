package com.product.adapter.out.persistence.factory;

import com.product.application.port.in.ProductRepository;

import static com.product.adapter.out.persistence.factory.RepositoryFactoryEnum.H2FACTORY;
import static com.product.adapter.out.persistence.factory.RepositoryFactoryEnum.MEMORYREPOSITORY;

public interface RepositoryFactory {

    static ProductRepository createFactory(Enum repository) {
        if(repository == MEMORYREPOSITORY){
            return new InMemoryRepositoryFactory().create();
        }else if(repository == H2FACTORY){
            return new DBRepositoryFactory().create();
        }else{
            throw new RuntimeException("Invalid repository");
        }
    }
}
