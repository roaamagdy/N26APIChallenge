package com.n26.api.tests;

import com.n26.constants.APIEndPoints;
import com.n26.helpers.BaseServiceHelper;
import com.n26.models.OrderStatus;
import com.n26.models.Store;
import com.n26.utils.JsonParser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreTests extends BaseServiceHelper {
    JsonParser jsonTestData = new JsonParser("TestData");

    @Test
    public void placeOrder() {
        Store store = new Store();
        store.setId((Long) (jsonTestData.getData("StoreData").get("id")));
        store.setPetId((Long) (jsonTestData.getData("StoreData").get("petId")));
        store.setQuantity((Long) (jsonTestData.getData("StoreData").get("quantity")));
        store.setShipDate(String.valueOf(jsonTestData.getData("StoreData").get("shipDate")));
        store.setStatus(OrderStatus.valueOf(String.valueOf(jsonTestData.getData("StoreData").get("status"))));

        store.setComplete((Boolean) (jsonTestData.getData("StoreData").get("complete")));

        Response restResponse = executePost(store, HttpStatus.SC_OK, APIEndPoints.CREATE_ORDER);
        Store storeResponse = restResponse.as(Store.class);

        Assert.assertEquals(storeResponse, store);
//        //Delete test data
        executeDeleteById(HttpStatus.SC_OK, APIEndPoints.DELETE_ORDER, storeResponse.getId());

    }

}
