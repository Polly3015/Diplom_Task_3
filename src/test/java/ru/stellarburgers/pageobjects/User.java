package ru.stellarburgers.pageobjects;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.stellarburgers.pageobjects.Constants.*;

public class User {

    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password) {
        this(email, password, "");
    }

    // Успешная регистрация пользователя + получение токена
    public String successRegisterUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(API_REGISTER)
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    // Авторизация в аккаунте
    public Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(API_LOGIN);
    }

    // Удаление пользователя
    public void deleteUser(String token) {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(API_DELETE_USER)
                .then()
                .statusCode(202);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


