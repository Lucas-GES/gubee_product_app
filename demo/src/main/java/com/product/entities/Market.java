package com.product.entities;


public class Market {

    private Integer id;

    private String name;

    public Market() {
    }

    public Market(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
