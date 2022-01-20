package com.bugred.api;

import static io.qameta.allure.Allure.addAttachment;
import static io.restassured.RestAssured.given;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.equalTo;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.mapping.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Тесты API
 * @author Константин Рыбаков
 */
@Epic("API")
@Feature("Users")
public class ApiTest {

    @DataProvider(name = "user")
    public static Object[] user() {
        String username = Faker.instance().name().username();
        String email = Faker.instance().internet().emailAddress();
        ArrayList<Integer> tasks = new ArrayList<Integer>();
        tasks.add(Integer.valueOf(12));
        ArrayList<Integer> companies = new ArrayList<Integer>();
        companies.add(Integer.valueOf(40));

        User user = User
                .builder()
                .name(username)
                .email(email)
                .tasks(tasks)
                .companies(companies)
                .hobby("diving")
                .phone("+79952354856")
                .inn("132156412231")
                .build();

        User[] users = {
                user
        };
        return users;
    }

    @BeforeClass
    public void setUp() {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));
    }

    @Test(dataProvider = "user", description = "createUser")
    public void createUserTest(User user) {
        step("Отправка запроса", () -> {
            String request = new Gson().toJson(user);
            addAttachment("Request", "text/plain", request);
            String response = given()
                    .contentType(ContentType.JSON).body(request)
                .when()
                    .post("http://users.bugred.ru/tasks/rest/createuser")
                .then()
                    .body("name", equalTo(user.getName()),
                            "email", equalTo(user.getEmail()),
                            "hobby", equalTo(user.getHobby()),
                            "phone", equalTo(user.getPhone()),
                            "inn", equalTo(user.getInn()))
                    .extract()
                    .asString();
            addAttachment("Response", "text/plain", response);
        });
    }
}
