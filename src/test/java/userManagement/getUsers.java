package userManagement;

import core.StatusCode;
import core.baseTest;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ExtentReport;
import utils.JsonReader;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.*;

import org.json.simple.parser.ParseException;
import utils.PropertyReader;
import utils.SoftAssertionUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.JsonReader.getJsonArray;

public class getUsers  extends baseTest {

//    SoftAssertionUtils softAssertion = new SoftAssertionUtils();

    @Test (groups = "RegressionSuite")
    public void getUserData()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        given()
                 .header("x-api-key","reqres-free-v1")
                .when().get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                statusCode( 200);
    }

    @Test (groups = "RegressionSuite")
    public void validateGetResponseBody()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
        given().
                when().get("/todos/1").
                then().
                assertThat().
                statusCode(StatusCode.SUCCESS.code)
                .body(not(isEmptyString()))
                .body("title",equalTo("delectus aut autem"))
                .body("userId",equalTo(1));
    }

    @Test (groups = "RegressionSuite")
    public void validateResponseHasItems()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        RestAssured.baseURI="https://jsonplaceholder.typicode.com";

        Response response = given()
                .when().get("/posts")
                .then()
                .extract().response();


//        use Hamcrest to check that the response body contains specific items

        assertThat(response.jsonPath().getList("title"),hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit","qui est esse"));
    }

    @Test (groups = "RegressionSuite")
    public void validateResponseSize()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        RestAssured.baseURI ="https://jsonplaceholder.typicode.com";

        Response response = given()
                .when()
                .get("/comments")
                .then()
                .extract().response();

        assertThat(response.jsonPath().getList(""),hasSize(500));
    }

    @Test (groups = "RegressionSuite")
    public void validateListContainsInOrder()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = given()
                .when()
                .get("/comments?postId=1")
                .then()
                .extract().response();

        List<String> expectedEmail = Arrays.asList("Eliseo@gardner.biz","Jayne_Kuhic@sydney.com","Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"),contains(expectedEmail.toArray(new String[0])));

    }

    @Test (groups = "RegressionSuite")
    public void testGetUsersWithQueryParameters()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        RestAssured.baseURI = "https://reqres.in/api";

        Response response = given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("page",2)
                .when()
                .get("/users")
                .then()
                .statusCode(StatusCode.SUCCESS.code)
                .extract().response();

        response.then().body("data",hasSize(6));
        response.then().body("data[0].id", is(7));
        response.then().body("data[0].email", is("michael.lawson@reqres.in"));
        response.then().body("data[0].first_name", is("Michael"));
        response.then().body("data[0].last_name", is("Lawson"));
        response.then().body("data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"));

        response.then().body("data[1].id", equalTo(8));
        response.then().body("data[1].email", equalTo("lindsay.ferguson@reqres.in"));
        response.then().body("data[1].first_name", equalTo("Lindsay"));
        response.then().body("data[1].last_name", equalTo("Ferguson"));
        response.then().body("data[1].avatar", equalTo("https://reqres.in/img/faces/8-image.jpg"));

        response.then().body("data[2].id", equalTo(9));
        response.then().body("data[2].email", equalTo("tobias.funke@reqres.in"));
        response.then().body("data[2].first_name", equalTo("Tobias"));
        response.then().body("data[2].last_name", equalTo("Funke"));
        response.then().body("data[2].avatar", equalTo("https://reqres.in/img/faces/9-image.jpg"));
    }

    @Test (groups = "RegressionSuite")
    public void validateStatusCodeGetUsers() {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

//        System.out.println("***************" + Helper.propertyReader("qaBaseUrl"));

        Response resp = given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("page",2)
                .when()
                .get("https://reqres.in/api/users");


        int actualStatusCode = resp.statusCode();
        assertEquals((double) StatusCode.SUCCESS.code, actualStatusCode);
        System.out.println(resp.body().asString());
    }

    @Test (groups = "RegressionSuite")
    public void testGetUsersWithMultipleQueryParam() {

        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        Response response = given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("page",2)
                .queryParam("per_page",3)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(StatusCode.SUCCESS.code)
                .extract().response();

//        response.then().body("data",hasSize(3));
    }

//    @Test
//    public void validateResponseBodyGetPathParam()
//    {
//        String raceSeasonValue = "2017";
//
//        Response resp = given().pathParam("raceSeason" , raceSeasonValue)
//                .when()
//                .get("http://ergast.com/api/f1/{raceSeason}/circuits.json");
//
//        int actualStatusCode = resp.statusCode();
//        assertEquals(actualStatusCode,200);
//        System.out.println(resp.body().asString());
//
//    }

//    @Test
//    public void testCreateUserWithFormParam() {
//        Response response = given()
//                .header("x-api-key", "reqres-free-v1")
//                .header("Content-Type","application/json")
////                .contentType("application/x-www-form-urlencoded")
//                .formParam("first_name", "George")
//                .formParam("last_name", "Bluth")
//                .when()
//                .post("https://reqres.in/api/users")
//                .then()
//                .statusCode(400)
//                .body("first_name", equalTo("George"))
//                .body("last_name", equalTo("Bluth"))
//                .extract().response();
//
////        response.then().body("name",equalTo("John Doe"));
////        response.then().body("job",equalTo("Developer"));


@Test (groups = "RegressionSuite")
    public  void testGetUserListWithHeader()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        given()
                .header("Content-Type","application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(StatusCode.SUCCESS.code)
                .body("page",equalTo(2))
                .body("data[0].first_name",equalTo("Michael"))
                .body("data[0].last_name",equalTo("Lawson"));

    }

    @Test (groups = "RegressionSuite")
    public void testWithTwoHeader()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        given()
                .header("Authorization","bearer vwtefdu13tx4fdub1tyqdxuy3gnx1i"  )
                .header("Content-Type","application/json")
//                .body("{\"name\": \"morpheus\", \"job\" : \"leader\" }")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(StatusCode.SUCCESS.code);
//                .body("name", equalTo("morpheus"))
//                .body("job",equalTo("leader"));
    }


    @Test (groups = "RegressionSuite")
    public void testWithHeaderGetList()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

//        RestAssured.baseURI = "https://reqres.in/api";
//      Create a  Map to hold headers
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","bearer vwtefdu13tx4fdub1tyqdxuy3gnx1i");

        given()
                .headers(headers)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(StatusCode.SUCCESS.code);

        System.out.println("Tested Successfully testWithHeaderGetList !! ");
    }

    @Test (groups = "RegressionSuite")
    public void testFetchHeaders()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract().response();

        Headers headers = response.getHeaders();
        for (Header h : headers) {
            if (h.getName().contains("Server")) {
                System.out.println(h.getName() + " : " + h.getValue());
                assertEquals("cloudflare", h.getValue());
                System.out.println("PASSED !! ");

            }
        }
    }

    @Test (groups = "RegressionSuite")
    public void testUseCookies()
    {
        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        Cookie cookies = new Cookie.Builder("cookieKey1","cookieValue1")
                .setComment("using cookie key")
                        .build();
        given()
                .cookie(cookies)
//                .cookie("cookieKey1","cookieValue1")
//                .cookie("cookieKey1","cookieValue1")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(StatusCode.SUCCESS.code);
        System.out.println("tsetUseCookie Executed successfully");
    }

    @Test (groups = "RegressionSuite")
    public void testFetchCookies()
    {

        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");


        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract().response();

        Map<String, String> cookies = response.getCookies();
        Cookies cookies1 = response.getDetailedCookies();
        cookies1.getValue("server");
        assertEquals("server","cloudflare");
        assertThat(cookies,hasKey("JSESSIONID"));
        assertThat(cookies,hasValue("ABCDEF123456"));
    }



    @Test (groups = "RegressionSuite")
    public void verifyStatusCodeDelete()
    {

        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("verifyStatusCodeDelete", "Validate 204 status code for DELETE");

        Response response = given()
                .header("x-api-key","reqres-free-v1")
                .delete("https://reqres.in/api/users/2");

        assertEquals(response.getStatusCode(), 204);  // Correct
        System.out.println("***************************************");
    }

    @Test(groups = "RegressionSuite")
    public void validateWithDataFromProperties() throws IOException, ParseException {

        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("validateWithDataFromProperties", "Validate 204 status code for DELETE");


        String serverAddress = PropertyReader.propertyReader("config.properties","server");
        String endpoint = JsonReader.getTestData("endpoint");

        System.out.println(serverAddress);
        Response resp = given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("page",2)
                .when()
                .get(serverAddress+endpoint);


        int actualStatusCode = resp.statusCode();
        assertEquals((double) StatusCode.SUCCESS.code, actualStatusCode);
    }

    @Test (groups = "RegressionSuite")
    public void validateWithDataFromPropertiesAndTestData() throws IOException, ParseException {

        ExtentReport.extentlog = ExtentReport.extentreport
                .startTest("validateWithDataFromPropertiesAndTestData", "Validate  status code ");

        String serverAddress = PropertyReader.propertyReader("config.properties","server");

        String endPoint = JsonReader.getTestData("endpoint");
        String URL = serverAddress + endPoint;
        System.out.println("Server Address is: " + URL);
        Response resp =
                given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("page",2)
                .when()
                .get(URL);


        int actualStatusCode = resp.statusCode();
        assertEquals((double) StatusCode.SUCCESS.code, actualStatusCode);
        System.out.println("validateWithDataFromPropertiesAndTestData PASSED !!!! " + URL);
    }


    @Test
    public void hardAssertion()
    {
        System.out.println("hardAssertion");
        Assert.fail();
        System.out.println("hardAssert");
    }

    @Test
    public void softAssertion()
    {
//        SoftAssert softAssertion = new SoftAssert();

        System.out.println("softAssertion");
        SoftAssertionUtils.assertTrue(false,"");
        SoftAssertionUtils.assertTrue(true,"");
        System.out.println("softAssertion");
        SoftAssertionUtils.assertAll();
    }

    @Test
    public void testGetUsersWithQueryParametersSoftAssertion()
    {
        RestAssured.baseURI = "https://reqres.in/api";

        Response response = given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("page",2)
                .when()
                .get("/users")
                .then()
                .statusCode(StatusCode.SUCCESS.code)
                .extract().response();
        SoftAssertionUtils.assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code,"Failed validation !!");
        SoftAssertionUtils.assertEquals(response.getStatusCode(), StatusCode.NO_CONTENT.code,"Failed validation !!");

        SoftAssertionUtils.assertAll();

    }

    @DataProvider(name="testdata")
    public Object[][] testData() {
        return new Object[][]
                {
                        {"1","John"},
                        {"2","Jane"},
                        {"3","Bob"}
                };

    }

    @Test(dataProvider = "testdata")
    @Parameters({"id","name"})
    public void testEndpoint(String id , String name){
        given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("id",id)
                .queryParam("name",name)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void Test() throws IOException , ParseException{
        JsonReader.getJsonArrayData("languages",1);
        JSONArray jsonArray = getJsonArray("contact");
        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

}
