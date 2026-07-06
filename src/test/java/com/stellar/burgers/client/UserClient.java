package com.stellar.burgers.client;

import com.stellar.burgers.model.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URI = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String USER_PATH = "/api/auth/user";

    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .post(REGISTER_PATH);
    }

    public Response deleteUser(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(BASE_URI)
                .delete(USER_PATH);
    }

    public String getAccessToken(Response response) {
        return response.jsonPath().getString("accessToken");
    }
}