package com.product;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

@QuarkusTest
public class ProductControllerTest {

    @Test
    public void testProductEndpoint() {

        given()
                .when().get("/api/product")
                .then()
                .statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON);
    }

    @Test
    public void testFilterMarket(){
        given()
                .when().get("/api/product/market/Bis2bis")
                .then()
                .statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON);
    }

    @Test
    public void testFilterTechnologies(){
        given()
                .when().get("/api/product/technology/1")
                .then()
                .statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON);
    }

}
