package com.product;

import com.product.adapter.out.persistence.ProductRepositoryImplementation;
import com.product.adapter.out.persistence.db.DB;
import com.product.domain.entities.Product;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.sql.Connection;
import java.util.ArrayList;

@QuarkusTest
public class ProductRepositoryImplementationTest {

    @InjectMocks
    DB db = new DB();

    @Test
    @DisplayName("Deveria adicionar somente itens nao repetidos")
    void mergeProductNotEqualsTest(){
        ProductRepositoryImplementation repositoryImplementation = new ProductRepositoryImplementation(db);
        Product a = new Product(null, "test1", "test1", "m1", "t1");
        a.insertTechnology("t2");
        Product b = new Product(null, "test1", "test1", "m1", "t2");
        b.insertTechnology("t3");

        var expected = new Product(null, "test1", "test1", "m1", "t1");
        expected.insertTechnology("t2");
        expected.insertTechnology(b.getTechnology().get(1).getName());
        repositoryImplementation.mergeProduct(a, b);
        expected.getTechnology().forEach(t -> System.out.println("Expected: " + t.getName()));
        a.getTechnology().forEach(t -> System.out.println("Result: " + t.getName()));
        Assertions.assertEquals(expected.getTechnology(), a.getTechnology());

    }

    @Test
    @DisplayName("Deveria ficar vazio se itens nao coincidem")
    void shouldNotMergeProductTest(){
        ProductRepositoryImplementation repositoryImplementation = new ProductRepositoryImplementation(db);
        Product a = new Product(null, "test1", "test1", "m1", "t1");
        a.insertTechnology("t2");
        Product b = new Product(null, "test1", "test1", "m1", "t2");
        b.insertTechnology("t1");

        var expected = new Product(null, "test1", "test1", "m1", "t1");
        expected.insertTechnology("t2");
        expected.getTechnology().forEach(t -> System.out.println("Expected: " + t.getName()));
        a.getTechnology().forEach(t -> System.out.println("Result: " + t.getName()));
        Assertions.assertEquals(expected.getTechnology(), a.getTechnology());

    }



}
