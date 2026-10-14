package ru.stellarburgers.pageobjects;

import io.restassured.RestAssured;

public class Constants {

    // Базовый URL
    public static final String BASE_URI = "https://qa-stellarburgers.education-services.ru/";

    // API запросы
    public static final String API_REGISTER = "/api/auth/register";
    public static final String API_LOGIN = "/api/auth/login";
    public static final String API_DELETE_USER = "/api/auth/user";

}
