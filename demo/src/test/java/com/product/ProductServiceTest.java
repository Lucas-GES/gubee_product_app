package com.product;

import com.product.adapter.out.persistence.InMemoryProductRepository;
import com.product.application.port.in.ProductRepository;
import com.product.application.service.ProductService;
import com.product.domain.Product;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@QuarkusTest
public class ProductServiceTest {

    @InjectMocks
    private ProductRepository repository;

    @Mock
    private ProductService service;

    private List<Product> products;

    @BeforeEach
    void setUp(){
        repository = Mockito.mock(InMemoryProductRepository.class);
        service = new ProductService(repository);

        products  = new ArrayList<>();
        products.add(new Product(null, "test1", "test1", "m1", "t1"));
        products.add(new Product(null, "test2", "test2", "m1", "t1"));
        products.add(new Product(null, "test3", "test3", "m2", "t2"));
        products.add(new Product(null, "test3", "test3", "m2", "t2"));
    }

    @Test
    void shouldFindAllTest(){
        when(repository.findAll()).thenReturn(products);
        List<Product> result = service.find();
        assertEquals(result, products);
    }

    @Test
    void shouldFindMarket(){
        List<Product> p1 = new ArrayList<>();
        p1.addAll(Arrays.asList(products.get(0), products.get(1)));

        when(repository.filterMarket("m1")).thenReturn(p1);
        List<Product> result = service.findMarket("m1");
        assertEquals(result, p1);
    }

    @Test
    void shouldFindTechnology(){
        List<String> techNames = new ArrayList<>();
        techNames.addAll(Arrays.asList("Java", "Kotlin"));
        List<Product> p1 = new ArrayList<>();
        p1.addAll(Arrays.asList(products.get(2), products.get(3)));

        when(repository.filterTechnologies(techNames)).thenReturn(p1);
        List<Product> result = service.findTechnology(techNames);
        assertEquals(result, p1);
    }

}
