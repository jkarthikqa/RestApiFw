package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import pojo.AddPlace;
import pojo.Location;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class StepDefination extends Utils {

    RequestSpecification reqSpc;
    ResponseSpecification resSep;
    Response response;
    TestDataBuild data = new TestDataBuild();

    static String placeValue;

    @Given("add place payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws Exception {
        reqSpc = given().spec(super.requestSpecification()).body(data.AddPlacePayLoad(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        ApiResources resApi = ApiResources.valueOf(resource);
        System.out.println(resApi.getResource());

        resSep = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST"))
            response = reqSpc.when().post(resApi.getResource());
            //  .then().spec(resSep).extract().response();
        else if (method.equalsIgnoreCase("GET"))
            response = reqSpc.when().get(resApi.getResource());
        //.then().spec(resSep).extract().response();
    }

    @Then("the response Api call is success with status code {int}")
    public void the_response_api_call_is_success_with_status_code(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("{string} in response body return is as {string}")
    public void in_response_body_return_is_as(String key, String expectedValue) {

        String actualValue = getJsonPath(response, key);
        assertEquals(actualValue, expectedValue);
    }


    @Then("verify place_id created maps to {string} using {string} with {string} http request")
    public void verifyPlace_idCreatedMapsToUsingWithHttpRequest(String expectedName, String resourceName, String method) throws IOException {
        //Request Speciation
        placeValue = getJsonPath(response, "place_id");
        reqSpc= given().spec(super.requestSpecification())
                .queryParam("place_id", placeValue);
        user_calls_with_http_request(resourceName, method);
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("delete place payload")
    public void deletePlacePayload() throws IOException {
       reqSpc =given().spec(super.requestSpecification()).body(data.deletePlacePayLoad(placeValue));
    }
}
