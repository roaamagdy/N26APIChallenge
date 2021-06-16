package com.n26.helpers;

import com.n26.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;

/**
 * @author Roaa
 * This class is responsible for Restassured configurtion
 * This class will contain all common methods need in the tests.
 */
public class BaseServiceHelper {

    private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");
    private static final String PORT = ConfigManager.getInstance().getString("port");

    public BaseServiceHelper() {
        RestAssured.baseURI = BASE_URL;
        //  RestAssured.port = Integer.parseInt(PORT);
    }

    protected static Response executePost(Object body, int statusCode, String method) {

        return given().log().all()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .expect()
                .statusCode(statusCode)
                .when()
                .post(method)
                .then().log().all()
                .extract()
                .response();
    }

    public static Response executeGetById(int statusCode, String method, long id) {

        return given().pathParam("id", id).
                given().log().all().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .expect().statusCode(statusCode).when().get(method);
    }

    public static Response executeGetByUsername(int statusCode, String method, String username) {

        return given().pathParam("username", username).
                given().log().all().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .expect().statusCode(statusCode).when().get(method);
    }

    public static Response executePut(Object body, int statusCode, String method, int id) {

        return given().pathParam("id", id).log().all().body(body).contentType(ContentType.JSON)
                .accept(ContentType.JSON).expect().statusCode(statusCode).when().put(method).then().log().all()
                .extract().response();
    }

    public static Response executeDeleteById(int statusCode, String method, long id) {
        return given().pathParam("id", id).log().all().delete(method).then().log().ifError()
                .statusCode(statusCode).assertThat().extract().response();
    }

    public static String generateRandomEmail() {
        return "n26-" + UUID.randomUUID() + "@test.com";
    }

}
