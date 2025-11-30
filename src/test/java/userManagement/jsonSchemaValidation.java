package userManagement;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class jsonSchemaValidation {

    @Test
    public void getUserData() {
        File schema = new File("resources/ExpectedSchema.json");

        given().
                header("x-api-key", "reqres-free-v1");
        when().get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(schema));

    }

}
