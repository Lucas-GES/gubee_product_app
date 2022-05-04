package com.product.adapter.out.persistence;

import com.product.application.port.in.ProductRepository;
import com.product.domain.entities.Product;
import com.product.domain.entities.Technology;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    public InMemoryProductRepository(){
        this.AddProducts();
    }

    public void AddProducts(){
        this.products.add(new Product(1, "Amazon", "Integração com Amazon", "Moveis", "Java"));
        this.products.add(new Product(2, "Carrefour", "Integração com Carrefour", "Moveis", "Kotlin"));
        this.products.add(new Product(3, "Magalu", "Integração com Magalu", "Moveis", "Java"));
        this.products.add(new Product(4, "Magazine Luiza", "Integração com Magazine Luiza", "Moveis", "Kotlin"));
        this.products.add(new Product(5, "Muffato", "Integração com Muffato", "Eletrodomesticos", "Java"));
        this.products.add(new Product(6, "Bis2bis", "Integração com Bis2bis", "Eletronicos", "Java"));
        this.products.add(new Product(7, "Mercado Livre", "Integração com Mercado Livre", "Eletronicos", "TypeScript"));
        this.products.add(new Product(8, "Pernambucanas", "Integração com Pernambucanas", "Eletronicos", "TypeScript"));

        products.get(2).insertTechnology("Kotlin");
        products.get(2).insertTechnology("TypeScript");
        products.get(3).insertTechnology("Java");
        products.get(5).insertTechnology("TypeScript");
        products.get(6).insertTechnology("Kotlin");
        products.get(7).insertTechnology("Java");
        products.get(7).insertTechnology("Kotlin");
    }

    @Override
    public List<Product> findAll() {
        return this.products;
    }

    @Override
    public List<Product> filterMarket(String name) {
        return findAll().stream().filter(p -> p.getMarket().getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Product> filterTechnologies(List<String> names) {
        List<Product> list = new ArrayList<>();
        for(Product p : findAll()){
            for(Technology t : p.getTechnology()){
                if(names.contains(t.getName())){
                    list.add(p);
                }
            }
        }
        return list;
    }
}
