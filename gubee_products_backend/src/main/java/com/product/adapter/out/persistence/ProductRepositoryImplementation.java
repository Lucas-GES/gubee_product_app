package com.product.adapter.out.persistence;

import com.product.adapter.out.persistence.db.Database;
import com.product.adapter.out.persistence.db.exceptions.DbException;
import com.product.application.port.in.ProductRepository;
import com.product.domain.entities.Product;
import com.product.domain.entities.Technology;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductRepositoryImplementation implements ProductRepository {

    private Connection conn;

    public ProductRepositoryImplementation(Database database){conn = database.connection();}

    private List<Product> prepareStatement(String query){
        try(PreparedStatement st = conn.prepareStatement(query)){
            try(ResultSet rs = st.executeQuery()){
                List<Product> list = new ArrayList<>();

                list.addAll(instantiateProduct(rs));

                return list;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        String query = ("""
                        SELECT product.id,product.name, product.description, market.name, technologies.name
                        FROM product
                        join market on product.marketId = market.id
                        join product_tech on product_tech.product_id = product.id
                        join technologies on product_tech.tech_id = technologies.id""");

        return prepareStatement(query);
    }

    @Override
    public List<Product>filterMarket(String name){
        return findAll().stream().filter(p -> p.getMarket().getName().equals(name)).collect(Collectors.toList());
    }


    @Override
    public List<Product> filterTechnologies(List<String> names) {
//        List<Product> list = new ArrayList<>();
//        for(Product p : findAll()){
//            for(Technology t : p.getTechnology()){
//                if(names.contains(t.getName())){
//                    list.add(p);
//                }
//            }
//        }
//        //return list;
        return findAll().stream().filter(p -> p.getTechnology().stream().map(Technology::getName).anyMatch(names::contains)).collect(Collectors.toList());

    }

    public List<Product> instantiateProduct(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while(rs.next()) {
            Product product = new Product(
                    rs.getInt("product.id"),
                    rs.getString("product.name"),
                    rs.getString("product.description"),
                    rs.getString("market.name"),
                    rs.getString("technologies.name"));
            products.add(product);
        }

        return getDistinctProducts(products);
    }

    private List<Product> getDistinctProducts(List<Product> products){
        var groupedById
                = products.stream().collect(Collectors.groupingBy(Product::getId));
        return groupedById.values().stream()
                .map(group -> {
                    var first = group.iterator().next();
                    group.forEach(next -> mergeProduct(first, next));
                    return first;
                }).collect(Collectors.toList());
    }

    public Product mergeProduct(Product a, Product b){
        var techA = a.getTechnology();
        var techB = b.getTechnology();
        var techNameA = new ArrayList<String>();
        var techNameB = new ArrayList<String>();
        var diference = new ArrayList<String>();
        techA.forEach(t -> techNameA.add(t.getName()));
        techB.forEach(t -> techNameB.add(t.getName()));

        techNameB.forEach(nameB -> { if(!techNameA.contains(nameB)) diference.add(nameB);});

        diference.forEach(a::insertTechnology);

        return a;
    }

}
