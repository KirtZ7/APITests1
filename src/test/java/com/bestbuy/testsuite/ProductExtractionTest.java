package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ProductExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //11. Extract the limit
    @Test
    public void test11(){
        int limit = response.extract().path("limit");
        System.out.println("value of limit is : " + limit);
    }

    //12. Extract the total
    @Test
    public void test12(){
        int total = response.extract().path("total");
        System.out.println(total);
    }
    //13. Extract the name of 5th product
    @Test
    public void test13(){
        String name = response.extract().path("data[4].name");
        System.out.println(name);
    }

    //14. Extract the names of all the products
    @Test
    public void test14(){
        List<Map<String,?>> names = response.extract().path("data.name");
        System.out.println(names);
    }
    //15. Extract the productId of all the products
    @Test
    public void test15(){
        List<Map<String,?>> productId = response.extract().path("data.id");
        System.out.println(productId);
    }

    //16. Print the size of the data list
    @Test
    public void test16(){
        List<Map<?,?>> data = response.extract().path("data");
        int size = data.size();
        System.out.println("Size of data list :" + size);
    }
    //17. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test17(){
        HashMap<?,?> productData = response.extract().path("data[3]");
        System.out.println(productData);
    }

    //18. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test18(){
        String data = response.extract().path("data[8].model");
        System.out.println("Model of Energizer - N Cell E90 Batteries (2-Pack): " + data);
    }
    //19. Get all the categories of 8th products
    @Test
    public void test19(){
        List<Map<?,?>> eighthProduct = response.extract().path("data[8].categories");
        System.out.println(eighthProduct);
    }
    //20. Get categories of the store where product id = 150115
    @Test
    public void test20(){
        List<Map<?,?>> one50115 = response.extract().path("data[3].categories");
        System.out.println(one50115);
    }
    //21. Get all the descriptions of all the products
    @Test
    public void test21(){
        List<HashMap<?,?>> descriptionsAllProducts = response.extract().path("data.description");
        System.out.println(descriptionsAllProducts);
    }
    //22. Get id of all the categories of all the products
    @Test
    public void test22(){
        List<HashMap<?,?>> allIdsAllProducts = response.extract().path("data.categories.id");
        System.out.println(allIdsAllProducts);
    }
    //23. Find the product names Where type = HardGood
    @Test
    public void test23(){
        List<HashMap<?,?>> typeHardGood = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println(typeHardGood);
    }
    //24. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test24(){
        List<HashMap<?,?>> totalCategory = response.extract().path("data[1].categories");
        int totalSize = totalCategory.size();
        System.out.println(totalSize);
    }
    //25. Find the createdAt for all products whose price < 5.49
    @Test
    public void test25(){
        List<HashMap<?,?>> createdAtLess = response.extract().path("data.findAll{it.price <= 5.49}.createdAt");
        System.out.println(createdAtLess);
    }
    //26. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test26(){
        List<HashMap<?,?>> nameCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println(nameCategories);
    }
    //27. Find the manufacturer of all the products
    @Test
    public void test27(){
        List<HashMap<?,?>> manufacturerAllProducts = response.extract().path("data.manufacturer");
        System.out.println(manufacturerAllProducts);

    }
    //28. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test28(){
        List<HashMap<?,?>> imagesViaManufacturer = response.extract().path("data.findAll{it.name.startsWith('Energizer')}.image");
        System.out.println(imagesViaManufacturer );
    }
    //29. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test29(){
        List<HashMap<?,?>> createdAtMore = response.extract().path("data.findAll{it.price >= 5.99}.createdAt");
        System.out.println(createdAtMore);
    }
    //30. Find the url of all the products
    @Test
    public void test30(){
        List<HashMap<?,?>> urlOfAll= response.extract().path("data.url");
        System.out.println(urlOfAll);
    }
}
