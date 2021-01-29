package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws Exception {
        // write a code to get place id
        // execute above code when place id is null

        StepDefination m = new StepDefination();
        if (StepDefination.placeValue == null) {
            m.add_place_payload("Karthik", "French", "Asia");
            m.user_calls_with_http_request("Add_Place_Api", "Post");
            m.verifyPlace_idCreatedMapsToUsingWithHttpRequest("Karthik", "Get_Place_Api", "Get");

        }
    }
}
