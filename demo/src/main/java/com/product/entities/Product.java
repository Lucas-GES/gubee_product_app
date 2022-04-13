package com.product.entities;


import java.util.ArrayList;
import java.util.List;

public class Product {

    private Integer id;

    private String name;

    private String productDescription;

    private Market market;

    private List<Technology> technology = new ArrayList<>();

    public Product(){
    }

    public Product(Integer id, String name, String productDescription, String market, List<String> technology) {
        this.id = id;
        this.name = name;
        this.productDescription = productDescription;
        this.market = new Market(market);
        this.insertTechnology(technology);
    }

    public void insertTechnology(List<String> technology){
        for(String t : technology){
            this.technology.add(new Technology(t));
        }
    }

    public void insertTechByTech(List<Technology> technologies){
        for(Technology tech: technology){
            this.technology.add(new Technology(tech.getName()));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public List<Technology> getTechnology() {
        return technology;
    }
}
