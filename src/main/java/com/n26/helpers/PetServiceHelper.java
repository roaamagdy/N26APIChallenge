package com.n26.helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetServiceHelper extends BaseServiceHelper {


    public static Response findByStatus(int statusCode, String method, String status) {

        return given().queryParam("status", status).
                given().log().all().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .expect().statusCode(statusCode).when().get(method);
    }

    public static Response findByTags(int statusCode, String method, List<String> tagsList) {

        return given().queryParam("tags", tagsList).
                given().log().all().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .expect().statusCode(statusCode).when().get(method);
    }

    public static Response updatePetName(int statusCode, String method, long petId, String name) {
        return given().pathParam("id", petId)
                .queryParam("name", name)
                .log().all()
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

    public static Response updatePetById(int statusCode, String method, long petId, Map<String, Object> searchMap) {
        return given().pathParam("id", petId)
                .queryParams(searchMap)
                .log().all()
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

    public static Response uploadImage(int statusCode, String method, long petId) {

        return given().pathParam("id", petId)
                .contentType(ContentType.MULTIPART)
                //    .formParam("additionalMetadata", "image")
                .multiPart(new File(System.getProperty("user.dir") + File.separator
                        + "resources" + File.separator + "petImg.jpg"))
                .log().all()
                .contentType(ContentType.MULTIPART)
                .accept(ContentType.JSON)
                .expect()
                .statusCode(statusCode)
                .when()
                .post(method)
                .then().log().all()
                .extract()
                .response();

    }
}
