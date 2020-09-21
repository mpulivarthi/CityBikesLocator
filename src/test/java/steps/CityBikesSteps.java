package steps;


import common.AppConstants;
import dto.LocationDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.JsonReader;
import utils.RestAssuredClient;

import static org.junit.Assert.*;

public class CityBikesSteps {

    LocationDTO inputData = null;
    LocationDTO responseData = null;
    Response response = null;

    @Given("^I have the city to locate on CityBike network$")
    public void i_have_the_city_to_locate_on_city_bike_network() {
        inputData = JsonReader.getInstance().getLocationDTO();
       assertNotNull(inputData);
    }

    @When("^I access CityBike network$")
    public void i_access_city_bike_network() {
        response = new RestAssuredClient().doGetRequest();
        assertNotNull(response);
    }
    @Then("^I verify Response is successful$")
    public void i_verify_response_is_successful(){
        assertEquals(response.getStatusCode(), AppConstants.HTTP_STATUS_200);
    }
    @Then("^I verify the city is in the respective Country$")
    public void i_verify_the_city_is_in_the_respective_country() {
        responseData = JsonReader.getInstance().processJsonResponse(
                new RestAssuredClient().parseResponse(response), inputData.getCity());
        assertNotNull(responseData);
        assertEquals(responseData.getCountry(), inputData.getCountry());

    }

    @Then("^I get the corresponding latitude and longitude$")
    public void i_get_the_corresponding_latitude_and_longitude() {
        Float latitude = responseData.getLatitude();
        Float longitude = responseData.getLongitude();
        assertNotNull(String.valueOf(latitude));
        assertNotNull(String.valueOf(longitude));
        System.out.println("Latitude for requested City: "+ latitude);
        System.out.println("Longitude for requested City: "+ longitude);
    }


}
