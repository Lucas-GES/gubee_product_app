package com.product;

import com.product.adapter.in.web.ProductController;
import com.product.application.service.ProductService;
import com.product.domain.entities.Product;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@QuarkusTest
public class ProductControllerTest {

    @InjectMocks
    ProductService service;

    @Mock
    ProductController controller;

    private List<Product> products;

    @BeforeEach
    void setUp(){
        service = Mockito.mock(ProductService.class);
        controller = new ProductController(service);

        products = new ArrayList<>();
        products.addAll(Arrays.asList(new Product(null, "test1", "test1", "m1", "t1"),
                new Product(null, "test2", "test2", "m1", "t2"),
                new Product(null, "test3", "test3", "m2", "t1"),
                new Product(null, "test4", "test4", "m2", "t2"),
                new Product(null, "test5", "test5", "m1", "t3")));
    }

    @Test
    public void shouldFindAllTest() {
        Mockito.when(service.find()).thenReturn(products);
        Response response = controller.findAll();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assertions.assertNotNull(response.getEntity());
        List<Product> entity = (List<Product>) response.getEntity();
        Assertions.assertFalse(entity.isEmpty());
        Assertions.assertEquals(products, entity);
    }

    @Test
    public void shouldFilterMarket(){
        Mockito.when(service.findMarket("m1")).thenReturn(Arrays.asList(products.get(0), products.get(1)));
        Response response = controller.filterMarket("m1");
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assertions.assertNotNull(response.getEntity());
        List<Product> entity = (List<Product>) response.getEntity();
        Assertions.assertFalse(entity.isEmpty());
        Assertions.assertEquals(Arrays.asList(products.get(0), products.get(1)), entity);
    }

    @Test
    public void testFilterTechnologies(){
        List<String> names = Arrays.asList("t2", "t3");
        Mockito.when(service.findTechnology(names)).thenReturn(Arrays.asList(products.get(1), products.get(3), products.get(4)));
        Response response = controller.filterTechnology(names);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assertions.assertNotNull(response.getEntity());
        List<Product> entity = (List<Product>) response.getEntity();
        Assertions.assertFalse(entity.isEmpty());
        Assertions.assertEquals(Arrays.asList(products.get(1), products.get(3), products.get(4)), entity);
    }

}
