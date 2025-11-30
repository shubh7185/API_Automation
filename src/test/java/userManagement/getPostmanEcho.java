package userManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class getPostmanEcho {

    @Test
    public void validateResponseBodyGetBasicAuth()
    {
        Response response = given()
                .auth()
                .basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth");

        int actualStatusCode = response.statusCode();
        assertEquals(StatusCode.SUCCESS.code, actualStatusCode);
        System.out.println(response.body().asString());
    }

    @Test
    public void validateWithTestDataFromJson() throws IOException, ParseException {
        String username = JsonReader.getTestData("username");
        String password = JsonReader.getTestData("password");
        System.out.println(username + ": " + password);

        Response response = given()
                .auth()
                .basic(username,password)
                .when()
                .get("https://postman-echo.com/basic-auth");

        int actualStatusCode = response.statusCode();
        assertEquals(StatusCode.SUCCESS.code, actualStatusCode);
//        System.out.println(response.body().asString());
        System.out.println("Passed !! validateWithTestDataFromJson " );
    }
    @Test(groups = "Smoke")
    public void validateResponseBodyGetDigestAuth()
    {
        Response response = given()
                .auth()
                .digest("postman","password")
                .when()
                .get("https://postman-echo.com/digest-auth");

        int actualStatusCode = response.statusCode();
        assertEquals(StatusCode.SUCCESS.code, actualStatusCode);
        System.out.println(response.body().asString());
    }

}
