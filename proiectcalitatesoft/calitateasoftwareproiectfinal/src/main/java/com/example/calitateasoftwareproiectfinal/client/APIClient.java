package com.example.calitateasoftwareproiectfinal.client;

import com.example.calitateasoftwareproiectfinal.service.JsonConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class APIClient {

    public static final String AUTH_TOKEN = "reqres-free-v1";

    private APIClient() {
    }

    public static Response submitDataToServer(String resourcePath, Map<String, Object> payload, String apiToken) {
        return RestAssured.given()
                .baseUri(JsonConfigReader.getConfig().baseUrl)
                .header("x-api-key", apiToken)
                .contentType("application/json")
                .body(payload)
                .when()
                .log().all()
                .post(resourcePath)
                .then()
                .log().all()
                .extract().response();
    }

    public static void verifyHttpResponseCode(Response apiResponse, int targetStatusCode) {
        Assert.assertEquals(apiResponse.getStatusCode(), targetStatusCode,
                "Cod de status HTTP neasteptat. Asteptat: " + targetStatusCode + ", primit: " + apiResponse.getStatusCode() +
                        ". Corp raspuns: " + apiResponse.asString());
    }

    public static Response fetchDataFromEndpoint(String resourcePath, String apiToken) {
        return RestAssured.given()
                .baseUri(JsonConfigReader.getConfig().baseUrl)
                .header("x-api-key", apiToken)
                .when()
                .log().all()
                .get(resourcePath)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response removeResource(String resourcePath, String apiToken) {
        return RestAssured.given()
                .baseUri(JsonConfigReader.getConfig().baseUrl)
                .header("x-api-key", apiToken)
                .when()
                .log().all()
                .delete(resourcePath)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response updateExistingResource(String resourcePath, Map<String, Object> payload, String apiToken) {
        return RestAssured.given()
                .baseUri(JsonConfigReader.getConfig().baseUrl)
                .header("x-api-key", apiToken)
                .contentType("application/json")
                .body(payload)
                .when()
                .log().all()
                .put(resourcePath)
                .then()
                .log().all()
                .extract().response();
    }
}