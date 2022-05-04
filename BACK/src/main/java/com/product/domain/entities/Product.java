package com.product.domain.entities;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {

    private Integer id;

    private String name;

    private String productDescription;

    private Market market;

    private List<Technology> technology = new ArrayList<>();

    public Product(){
    }

    public Product(Integer id, String name, String productDescription, String market, String technology) {
        this.id = id;
        this.name = name;
        this.productDescription = productDescription;
        this.market = new Market(market);
        this.insertTechnology(technology);
    }

    public void insertTechnology(String technology){
        this.technology.add(new Technology(technology));
    }

    public void insertTechByTech(List<Technology> technologies){
        this.technology.addAll(technologies);
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

    public Market getMarket() {
        return market;
    }

    public List<Technology> getTechnology() {
        return technology;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(productDescription, product.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productDescription);
    }
}
