package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postRequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class postUsers {

    private  static FileInputStream fileInputStream(String requestBodyFileName) throws FileNotFoundException {
        FileInputStream fileInputStream =
        new FileInputStream(new File(System.getProperty("user.dir") +
                "/resources/TestData/" + requestBodyFileName));

        return fileInputStream;

    }


    @Test
    public void validatePostWithString()
    {
    Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePutWithString()
    {
        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body("{\"name\":\"Shubh\",\"job\":\"leadSDET\"}")
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePutWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePatchWithString()
    {
        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body("{\"name\":\"morpheus\"}")
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithJsonFile() throws IOException
    {
        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(IOUtils.toString(fileInputStream("postRequestBody.json")))
                .when()
                .post("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }


    @Test
    public void validatePatchWithJsonFile() throws IOException
    {
        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(IOUtils.toString(fileInputStream("patchRequestBody.json")))
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePutWithJsonFile() throws IOException
    {
        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(IOUtils.toString(fileInputStream("putRequestBody.json")))
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePuttWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPOJO() throws IOException
    {
        postRequestBody postRequest = new postRequestBody();

        postRequest.setName("morpheus");
        postRequest.setJob("leader");

        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }


    @Test
    public void validatePostWithPOJOArray() throws IOException
    {
        List<String> listLanguages = new ArrayList<>();
        listLanguages.add("java");
        listLanguages.add("python");
        postRequestBody postRequest = new postRequestBody();

        postRequest.setName("morpheus");
        postRequest.setJob("leader");
        postRequest.setLanguages(listLanguages);

        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPOJOArray executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePutWithPOJO() throws IOException
    {
        postRequestBody putRequest = new postRequestBody();
        putRequest.setName("Shubh");
        putRequest.setJob("lead SDET");
        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(putRequest)
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePuttWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePatchWithPOJO() throws IOException
    {
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setName("shub");
        patchRequest.setJob("Leader");
        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePostWithPOJOComplexArray() throws IOException
    {
        List<String> listLanguages = new ArrayList<>();
        listLanguages.add("java");
        listLanguages.add("python");
        cityRequest cityRequests = new cityRequest();
        cityRequests.setName("Bangalore");
        cityRequests.setTemperature("30");
        cityRequest cityRequests1 = new cityRequest();
        cityRequests1.setName("Delhi");
        cityRequests1.setTemperature("40");

        List<cityRequest> cityRequests2 = new ArrayList<>();
        cityRequests2.add(cityRequests1);
        cityRequests2.add(cityRequests);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setName("morpheus");
        postRequest.setJob("leader");
        postRequest.setLanguages(listLanguages);
        postRequest.setCityRequestsBody(cityRequests2);

        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPOJOArray executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePatchWithPOJODeserialization() throws IOException
    {
        String name = "morpheus";

        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setName(name);
//        patchRequest.setJob("QA");
        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");

        postRequestBody responseBody = response.as(postRequestBody.class); // deserialization
        System.out.println(responseBody.getName());
        assertEquals(name, responseBody.getName());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithString executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

    @Test(description = "This  is used to Validate post with POJO")
    public void validatePostWithPOJODeserializationComplexArray() throws IOException
    {
        List<String> listLanguages = new ArrayList<>();
        listLanguages.add("java");
        listLanguages.add("python");
        cityRequest cityRequests = new cityRequest();
        cityRequests.setName("Bangalore");
        cityRequests.setTemperature("30");
        cityRequest cityRequests1 = new cityRequest();
        cityRequests1.setName("Delhi");
        cityRequests1.setTemperature("40");

        List<cityRequest> cityRequests2 = new ArrayList<>();
        cityRequests2.add(cityRequests1);
        cityRequests2.add(cityRequests);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setName("morpheus");
        postRequest.setJob("leader");
        postRequest.setLanguages(listLanguages);
        postRequest.setCityRequestsBody(cityRequests2);

        Response response = given()
                .headers("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");

        postRequestBody responseBody = response.as(postRequestBody.class); // deserialization
        System.out.println(responseBody.getCityRequestsBody().get(0).getName());
        System.out.println(responseBody.getCityRequestsBody().get(0).getTemperature());

        System.out.println(responseBody.getLanguages());
        System.out.println(responseBody.getName());
        System.out.println(responseBody.getJob());
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPOJOArray executed successfully !!!!");
        System.out.println(response.getBody().asString());
    }

}
