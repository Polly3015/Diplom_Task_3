package ru.stellarburgers.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import ru.stellarburgers.pageobjects.Constants;

public class BaseURI {

    @BeforeAll
    static void setUpBase() {
        RestAssured.baseURI = Constants.BASE_URI;
    }
}
