package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.codehaus.groovy.util.ListHashMap;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoresExtractionTest {

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

    // 1. Extract the limit
    @Test
    public void test01(){
        int limit = response.extract().path("limit");
        System.out.println("Value of limit is :" + limit);
        Assert.assertEquals(10,limit);
        response.body("limit",equalTo(10));
    }


    //2. Extract the total
    @Test
    public void test02(){
        int total = response.extract().path("total");
        System.out.println(("Value of total is :"+ total));
        Assert.assertEquals(1561,total);
        response.body("total", equalTo(1561));
    }

    //3. Extract the name of 5th store
    @Test
    public void test03(){
        String name5 = response.extract().path("data[4].name");
        System.out.println("Name of 5th store is: " + name5);
        Assert.assertEquals("Maplewood", name5);
        response.body("name", equalTo("Maplewood"));
    }

    //4. Extract the names of all the store
    @Test
    public void test04(){
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("List of store names :" +storeNames);

    }


    //5. Extract the storeId of all the store
    @Test
    public void test05(){
        List<String> storeID = response.extract().path("data.id");
        System.out.println("List of ID names :" +storeID);
    }

    //6. Print the size of the data list
    @Test
    public void test06(){
        List<Integer> dataSize = response.extract().path("data");
        int size = dataSize.size();
        System.out.println(size);
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test07(){
        List<Integer> storeNameStCloud =  response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("St Cloud values :"+storeNameStCloud);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test08(){
        List<Integer> addressRochester =  response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("Rochester address :"+addressRochester);
    }

    //9. Get all the services of 8th store
    @Test
    public void test09(){
        List<Map<String,?>> services8thStore = response.extract().path("data[8].services");
        System.out.println("8th store data :" +services8thStore);
    }

    //10. Get store services of the store where service name = Windows Store
    @Test
    public void test10() {
        List<Map<String, ?>> windowsStore = response.extract().path("data.services.findAll{it.name == 'Windows store'}");
        System.out.println(windowsStore);
    }

    @Test
    public void test1010() {
        List<Map<String, ?>> storeServices = response.extract().path("data.services");
        System.out.println(storeServices);
//        for (Map<String, ?> service : storeServices) {
//            if (service.get("name").equals("Windows Store")) {
//                Assert.assertTrue(true);
//                System.out.println("Windows Store services found in store: " + service);
//            }
//        }
    }


        //11. Get all the storeId of all the store
    @Test
    public void test11(){
        List<Map<String,?>> storeIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println(storeIds);
    }

    //12. Get id of all the store

    @Test
    public void test12(){
        List<Map<String,?>> storeIds = response.extract().path("data.id");
        System.out.println(storeIds);
    }
    //13. Find the store names Where state = ND
    @Test
    public void test13(){
        List<String> storeNames = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println(storeNames);
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test14() {
        List<Map<String, ?>> servicesRoch = response.extract().path("data[8].services");
        int size = servicesRoch.size();
        System.out.println(size);
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test15(){
        List<Map<String,?>> createdAt = response.extract().path("data.services.findAll{it.name == 'Windows store'}.createAt");
        System.out.println(createdAt);
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test16(){
        List<Map<String,?>> servicesFargo = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println(servicesFargo);
    }

    //17. Find the zip of all the store
    @Test
    public void test17(){
        List<Map<String,?>> allZips = response.extract().path("data.zip");
        System.out.println(allZips);
    }
    //18. Find the zip of store name = Roseville

    @Test
    public void test18(){
        List<Map<String,?>> rosevilleZips = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println(rosevilleZips);
    }

    //19. Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void test19(){
        List<Map<String,?>> homeTheater= response.extract().path("data.services");
//        for (Map<String, ?> service : homeTheater) {
//            if (service.get("name").equals("Magnolia Home Theater")) {
//                System.out.println(service);
//            }
//        }
        System.out.println(homeTheater);
    }

    //20. Find the lat of all the stores
    @Test
    public void test20(){
        List<Map<String,?>> storeLats = response.extract().path("data.lat");
        System.out.println(storeLats);
    }

}
