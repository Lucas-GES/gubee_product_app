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
        controller = Mockito.mock(ProductController.class);

        products = new ArrayList<>();
        products.addAll(Arrays.asList(new Product(null, "test1", "test1", "m1", "t1"),
                new Product(null, "test2", "test2", "m1", "t2"),
                new Product(null, "test3", "test3", "m2", "t1"),
                new Product(null, "test4", "test4", "m2", "t2")));
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
    public void testFilterMarket(){

    }

    @Test
    public void testFilterTechnologies(){

    }

}
