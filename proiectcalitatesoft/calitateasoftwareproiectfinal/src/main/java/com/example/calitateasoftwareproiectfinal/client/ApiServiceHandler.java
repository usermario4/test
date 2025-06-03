package com.example.calitateasoftwareproiectfinal.client;

import com.example.calitateasoftwareproiectfinal.service.JsonConfigReader;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiServiceHandler {

    private final String baseUri;
    private final String apiKey;

    private static final String DEFAULT_API_KEY = APIClient.AUTH_TOKEN;

    public ApiServiceHandler() {
        this.baseUri = JsonConfigReader.getConfig().baseUrl;
        this.apiKey = DEFAULT_API_KEY;
    }

    public ApiServiceHandler(String customApiKey) {
        this.baseUri = JsonConfigReader.getConfig().baseUrl;
        this.apiKey = customApiKey;
    }

    public Response createNewUser(String creationPath, int httpStatusCodeAsteptat, Map<String, Object> userData) {
        Response apiResponse = APIClient.submitDataToServer(creationPath, userData, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }

    public Response fetchSingleUserDetails(String userPath, int httpStatusCodeAsteptat) {
        Response apiResponse = APIClient.fetchDataFromEndpoint(userPath, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }

    public Response fetchAllUsersList(String usersListPath, int httpStatusCodeAsteptat) {
        Response apiResponse = APIClient.fetchDataFromEndpoint(usersListPath, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }

    public Response attemptFetchNonExistentUser(String nonExistentUserPath, int httpStatusCodeAsteptat) {
        Response apiResponse = APIClient.fetchDataFromEndpoint(nonExistentUserPath, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }

    public Response removeUserAccount(String userDeletionPath, int httpStatusCodeAsteptat) {
        Response apiResponse = APIClient.removeResource(userDeletionPath, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }

    public Response performUserLogin(String loginPath, int httpStatusCodeAsteptat, String email, String password) {
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);
        Response apiResponse = APIClient.submitDataToServer(loginPath, credentials, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }

    public Response performUserRegistration(String registrationPath, int httpStatusCodeAsteptat, String email, String password) {
        Map<String, Object> registrationDetails = new HashMap<>();
        registrationDetails.put("email", email);
        registrationDetails.put("password", password);
        Response apiResponse = APIClient.submitDataToServer(registrationPath, registrationDetails, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }

    public Response fetchSingleResourceDetails(String resourcePath, int httpStatusCodeAsteptat) {
        Response apiResponse = APIClient.fetchDataFromEndpoint(resourcePath, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }

    public Response fetchAllResourcesList(String resourcesListPath, int httpStatusCodeAsteptat) {
        Response apiResponse = APIClient.fetchDataFromEndpoint(resourcesListPath, this.apiKey);
        APIClient.verifyHttpResponseCode(apiResponse, httpStatusCodeAsteptat);
        return apiResponse;
    }
}
