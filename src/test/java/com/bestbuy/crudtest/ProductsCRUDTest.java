package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBaseProducts;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static io.restassured.RestAssured.given;

@OrderWith(Alphanumeric.class)
public class ProductsCRUDTest extends TestBaseProducts {

    @Test //get all list
    public void aAllList(){
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test //post new and retrieve the ID
    public void bPostNewIdAndCheck(){

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Nuclear Bombs");
        productPojo.setType("VeryNiceBoom");
        productPojo.setPrice(new BigDecimal("6.99"));
        productPojo.setUpc("0413564564019");
        productPojo.setShipping(69);
        productPojo.setDescription("it goes big boom... idk what else to tell you");
        productPojo.setManufacturer("Kirtan");
        productPojo.setModel("BB6969X7Z");
        productPojo.setUrl("https://www.bestbuy.com/site/trust-no-one-lp-vinyl/35474875.p?skuId=35474875");
        productPojo.setImage("https://bigthink.com/wp-content/uploads/2022/08/AdobeStock_81049143.jpeg");

        List<String> categories = new ArrayList<>();
        categories.add("Kirtan524136");
        categories.add("Nuclear Batteries");
        categories.add("2023-02-22 05:53 zzz");
        categories.add("2023-02-22 05:55 zzz");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .post();
        response.then().log().all().statusCode(201);
        response.prettyPrint();
    }

    @Test //update id via patch
    public void cUpdateId() {
    ProductPojo productPojo = new ProductPojo();
    productPojo.setManufacturer("Kirtan v2");

    Response response = given()
            .header("content-Type","Application/json")
            .pathParams("id","43900")
            .body(productPojo)
            .patch("/{id}");
    response.then().log().all().statusCode(200);
    response.prettyPrint();

    }



    @Test //retrieve id and validate
    public void dRetrieveIdAndValidate() {
        Response response = given()
                .pathParams("id","127687")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test //put test
    public void cPutDataTest(){
        List<String> categories = new ArrayList<>();
        categories.add("Kirtqdasdawewqeqwan524136");
        categories.add("Nuclasdasear Batteqewqeries");
        categories.add("2023-02-22 05:53 zzz");
        categories.add("2023-02-22 05:55 zzz");

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Nucleaasdaqweqwer Bombs");
        productPojo.setType("VeasdryNiqewceBoom");
        productPojo.setPrice(new BigDecimal("6.99"));
        productPojo.setUpc("0414564019");
        productPojo.setShipping(69);
        productPojo.setDescription("it qweqasdes big boom... idk what elseqwewqe to tell you");
        productPojo.setManufacturer("Kirqwasdan");
        productPojo.setModel("BB696qweqweasdX7Z");
        productPojo.setUrl("https://www.bestbuy.com/site/trust-no-one-lp-vinyl/35474875.p?skuId=35474875");
        productPojo.setImage("https://bigthink.com/wp-content/uploads/2022/08/AdobeStock_81049143.jpeg");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParams("id","9999685")
                .body(productPojo)
                .put("/{id}");
        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }

    @Test //delete ID
    public void eDeleteId() {
        Response response = given()
                .pathParams("id","69696")
                .when()
                .delete("/{id}");
        response.then().log().all().statusCode(404);
        response.prettyPrint();

    }

}
