package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBaseStores;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@OrderWith(Alphanumeric.class)
public class StoresCURDTest extends TestBaseStores {

    @Test //get list first
    public void aAllList(){
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test //post new and retrieve the ID
    public void bPostNewDataAndCheck(){

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("Huge Store");
        storesPojo.setType("Glass Box");
        storesPojo.setAddress("12345 london road");
        storesPojo.setAddress2("");
        storesPojo.setCity("London");
        storesPojo.setState("Central London");
        storesPojo.setZip("Post codes zzz");
        storesPojo.setLat((int) 54.6526325);
        storesPojo.setLng((int) -25.6252145);
        storesPojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9;");

//        List<String> services = new ArrayList<>();
//        services.add("this better work");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .post();
        response.then().log().all().statusCode(201);
        response.prettyPrint();
    }

    @Test //update id via patch
    public void cUpdateId() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("Huge Stasdasdaore");
        storesPojo.setType("Glass Boasdadasdsadax");

        Response response = given()
                .header("content-Type","Application/json")
                .pathParams("id","4")
                .body(storesPojo)
                .patch("/{id}");
        response.then().log().all().statusCode(200);
        response.prettyPrint();

    }

    @Test //put test
    public void cPutDataTest(){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("Huge Store");
        storesPojo.setType("Glass Box");
        storesPojo.setAddress("12345 london road");
        storesPojo.setAddress2("");
        storesPojo.setCity("London");
        storesPojo.setState("Central London");
        storesPojo.setZip("Post codes zzz");
        storesPojo.setLat((int) 54.6526325);
        storesPojo.setLng((int) -25.6252145);
        storesPojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9;");

//        List<String> services = new ArrayList<>();
//        services.add("this better work");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParams("id","4")
                .body(storesPojo)
                .put("/{id}");
        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }

    @Test //delete ID
    public void eDeleteId() {
        Response response = given()
                .pathParams("id","4")
                .when()
                .delete("/{id}");
        response.then().log().all().statusCode(404);
        response.prettyPrint();

    }

}
