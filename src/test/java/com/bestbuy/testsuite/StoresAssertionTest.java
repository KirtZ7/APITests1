package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/Stores")
                .then().statusCode(200);
    }
    // 1. Verify the if the total is equal to 1561
    @Test
    public void test01(){
        response.body("total", equalTo(1561));

    }

    // 2. Verify the if the stores of limit is equal to 10

    @Test
    public void test02(){
        response.body("limit", equalTo(10));
    }

    // 3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void test03(){
        response.body("data[1].name", equalTo("Inver Grove Heights"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void test004() {

        response.body("data.name", hasItem("Roseville"));
        response.body("data[3].name", equalTo("Burnsville"));
        response.body("data[4].name", equalTo("Maplewood"));
    }

    //5. Verify the store id = 7 inside store services of the third store of second services
    @Test
    public void test05(){
        response.body("data[2].services[2].storeservices.storeId", equalTo(7));
    }

    //6. Check hash map values ‘createdAt’ inside store services map where store name = Roseville
    @Test
    public void test06(){
        response.body("data[2].createdAt", equalTo("2016-11-17T17:57:05.853Z"));
    }

    //7. Verify the state = MN of forth store
     @Test
    public void test07(){
        response.body("data[4].state", equalTo("MN"));
     }

    //8. Verify the store name = Rochester of 9th store

    @Test
    public void test08(){
        response.body("data[8].name", equalTo("Rochester"));
    }

    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void test09(){
        response.body("data[5].id", equalTo(11));
    }

    //10. Verify the serviceId = 4 for the 7th store of forth service

    @Test
    public void test10(){
        response.body("data[7].services[3].storeservices.serviceId", equalTo(7));
    }
}
