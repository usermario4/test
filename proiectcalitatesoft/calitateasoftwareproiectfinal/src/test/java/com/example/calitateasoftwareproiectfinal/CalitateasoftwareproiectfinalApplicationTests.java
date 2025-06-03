package com.example.calitateasoftwareproiectfinal;

import com.example.calitateasoftwareproiectfinal.client.ApiServiceHandler;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalitateasoftwareproiectfinalApplicationTests extends Testbase {

    private ApiServiceHandler serviceHandler = new ApiServiceHandler();

    @Test
    public void testNewUserCreationSimple() {
        Map<String, Object> userData = new HashMap<>();
        String expectedName = "Alexandru";
        String expectedJob = "Inginer Software";
        userData.put("name", expectedName);
        userData.put("job", expectedJob);

        Response response = serviceHandler.createNewUser("/users", 201, userData);

        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("name"), expectedName);
        Assert.assertNotNull(response.jsonPath().getString("createdAt"));
    }

    @Test
    public void testFetchSingleUser() {
        Response response = serviceHandler.fetchSingleUserDetails("/users/2", 200);
        Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
        Assert.assertTrue(response.jsonPath().getString("data.email").endsWith("@reqres.in"));
    }

    @Test
    public void testFetchAllUsers() {
        Response response = serviceHandler.fetchAllUsersList("/users?page=1", 200);
        List<Object> users = response.jsonPath().getList("data");
        Assert.assertFalse(users.isEmpty());
        Assert.assertTrue(response.jsonPath().getInt("total_pages") > 0);
    }

    @Test
    public void testUserNotFound() {
        Response response = serviceHandler.attemptFetchNonExistentUser("/users/230", 404); // ID comun pentru 404
        Assert.assertEquals(response.getBody().asString(), "{}");
    }

    @Test
    public void testDeleteUser() {
        Response response = serviceHandler.removeUserAccount("/users/2", 204); // reqres.in returnează 204 pentru delete
        Assert.assertEquals(response.getBody().asString(), "");
    }

    @Test
    public void testSuccessfulLogin() {
        Response response = serviceHandler.performUserLogin("/login", 200, "eve.holt@reqres.in", "cityslicka");
        Assert.assertNotNull(response.jsonPath().getString("token"));
    }

    @Test
    public void testFailedLoginDueToMissingPassword() {
        Response response = serviceHandler.performUserLogin("/login", 400, "eve.holt@reqres.in", "");
        Assert.assertEquals(response.jsonPath().getString("error"), "Missing password");
    }

    @Test
    public void testSuccessfulRegistration() {
        String userEmail = "eve.holt@reqres.in";
        String userPassword = "somePassword123"; // Parolă hardcodată

        Response response = serviceHandler.performUserRegistration("/register", 200, userEmail, userPassword);
        Assert.assertNotNull(response.jsonPath().getString("token"));
        Assert.assertTrue(response.jsonPath().getInt("id") > 0);
    }

    @Test
    public void testFailedRegistrationDueToMissingEmail() {
        Response response = serviceHandler.performUserRegistration("/register", 400, null, "anotherPassword");
        Assert.assertEquals(response.jsonPath().getString("error"), "Missing email or username");
    }

    @Test
    public void testFetchSingleResource() {
        Response response = serviceHandler.fetchSingleResourceDetails("/unknown/2", 200);
        Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
        Assert.assertNotNull(response.jsonPath().getString("data.name"));
    }

    @Test
    public void testFetchAllResources() {
        Response response = serviceHandler.fetchAllResourcesList("/unknown", 200);
        List<Object> resources = response.jsonPath().getList("data");
        Assert.assertFalse(resources.isEmpty());
        Assert.assertTrue(response.jsonPath().getInt("total") > 0);
    }
}