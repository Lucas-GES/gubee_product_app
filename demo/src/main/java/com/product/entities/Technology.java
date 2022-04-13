package com.product.entities;

public class Technology {

    private Integer id;

    private String name;

    public Technology() {
    }

    public Technology(String name) {
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
