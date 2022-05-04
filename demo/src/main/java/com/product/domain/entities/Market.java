package com.product.domain.entities;


import java.util.Objects;

public class Market {

    private Integer id;

    private String name;

    public Market() {
    }

    public Market(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market market = (Market) o;
        return Objects.equals(id, market.id) && Objects.equals(name, market.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
