package com.product.adapter.out.persistence.utils;

public class H2Factory implements DBFactory{

    @Override
    public Database getDatabase() {
        return new DB();
    }
}
