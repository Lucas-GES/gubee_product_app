package com.product.adapter.out.persistence.factory;

public enum RepositoryFactoryEnum {

    MEMORYREPOSITORY(new InMemoryRepositoryFactory()),
    H2FACTORY(new DBRepositoryFactory());

    private RepositoryFactory repository;

    RepositoryFactoryEnum(RepositoryFactory repository){
        this.repository = repository;
    }

    public RepositoryFactory getRepository(){return repository;}
}
