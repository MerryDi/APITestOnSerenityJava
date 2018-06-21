package com.umarkets.steps;

import com.umarkets.models.UserModel;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import io.restassured.RestAssured;
import net.thucydides.core.annotations.Steps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.umarkets.steps.serenity.EndUserSteps;
import static com.umarkets.steps.serenity.EndUserSteps.getUserData;
import static net.serenitybdd.rest.SerenityRest.then;


public class DefinitionSteps {

    @Before
    public void test_setup(){
        RestAssured.useRelaxedHTTPSValidation();
    }


    @Steps
    UserModel user = getUserData();

    @Steps
    EndUserSteps endUserSteps;


    @Given("^User's registered in the system$")
    public void user_s_registered_in_the_system()  {
        endUserSteps.apiRegisration(user.getEmail(), user.getPassword());
        then().assertThat().statusCode(200);
    }

    @When("^User logged out from the system$")
    public void userLoggedOutFromTheSystem() {
        endUserSteps.apiLogout(user.getEmail(), user.getPassword());
        then().assertThat().statusCode(200).assertThat().contentType("application/json");
    }


    @And("^user gets special token$")
    public void userGetsSpecialToken() {
        user.setToken(endUserSteps.getToken(user.getEmail(), user.getPassword()));
        then().assertThat().statusCode(200);

    }

    @Then("^user can log into the other related system$")
    public void userCanLogIntoTheOtherRelatedSystem() {
        endUserSteps.apiLogin(user.Token);
        then().assertThat().statusCode(200);

    }


}
