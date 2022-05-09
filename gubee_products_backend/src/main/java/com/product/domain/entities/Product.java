package com.product.domain.entities;


import java.util.*;

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

    public Product(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.productDescription = product.getProductDescription();
        this.market = product.getMarket();
        this.technology = product.getTechnology();
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

    public String getProductDescription() {
        return productDescription;
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

    public List<String> getTechnologyNames(){
        List<String> names = new ArrayList<>();
        for(var tech: getTechnology()){
            names.add(tech.getName());
        }
        return names;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", market=" + market +
                ", technology=" + technology +
                '}';
    }
}
