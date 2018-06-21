package com.umarkets.steps.serenity;

import com.umarkets.models.UserModel;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Step;
import static net.serenitybdd.rest.SerenityRest.then;
import static net.serenitybdd.rest.SerenityRest.with;



public class EndUserSteps {


    String API_BASE_URL = "https://test-api.umarkets.com";
    String API_REGISTRATION_ENDPOINT = "/Registration/account";
    String API_LOGOFF_ENDPOINT = "/Account/logoff";
    String API_TRADING_URL = "https://test-trading.umarkets.com";
    String API_TRADING_ENDPOINT = "/auth/token";
    String API_TRADING_LOGIN_ENDPOINT = "/login.html";
    @Step
    public static UserModel getUserData() {
        UserModel user1 = new UserModel();

        user1.getEmail();
        user1.getPassword();

        return user1;
    }

    @Step
    public void apiRegisration(String email, String password) {
        System.out.println("email = [" + email + "], password = [" + password + "]");
        Response response = (Response) with().parameters(
                "model.email", email,
                "model.password", password,
                "api_key", "LeadRedirectionHandler%20")
                .get(API_BASE_URL + API_REGISTRATION_ENDPOINT);
        ValidatableResponse jsonResponse = then().assertThat().statusCode(200);

        String jsonStrResponse = jsonResponse.extract().asString();
        System.out.println("response: \n" + jsonStrResponse + "\n");

        System.out.println("AUTH Cookie : " + response.getCookie(".ASPXAUTHAPI"));
    }

    @Step
    public void apiLogout(String email, String password) {
        System.out.println("email = [" + email + "], password = [" + password + "]");
        Response response = (Response) with().contentType("application/json")
                .post(API_BASE_URL + API_LOGOFF_ENDPOINT);

        ValidatableResponse jsonResponse = then().assertThat().statusCode(200);

        String jsonStrResponse = jsonResponse.extract().asString();
        System.out.println("response: \n" + jsonStrResponse + "\n");
        System.out.println("AUTH Cookie : " + response.getCookie(".ASPXAUTHAPI"));
    }


    @Step
    public String getToken(String email, String password) {

        String hashPass = String.valueOf(UserModel.hash(password));

        System.out.println(API_TRADING_URL + API_TRADING_ENDPOINT + "?" + "user=" +  email +"&" + "pwd=" +  hashPass);

        Response response = (Response) with().parameters(
                "user", email,
                "pwd",  hashPass )
                .get(API_TRADING_URL + API_TRADING_ENDPOINT);
        ValidatableResponse jsonResponse = then().assertThat().statusCode(200);

        String jsonStrResponse = jsonResponse.extract().asString();
        System.out.println("response: \n" + jsonStrResponse + "\n");

        System.out.println("response token: \n" + response.path("a_token") + "\n");
        return  response.path("a_token");
    }


    @Step
    public void apiLogin( String token) {

        Response response = (Response) with().parameters(
                "a_token", token )
                .get(API_TRADING_URL + API_TRADING_LOGIN_ENDPOINT);
        ValidatableResponse jsonResponse = then().assertThat().statusCode(200);

        String jsonStrResponse = jsonResponse.extract().asString();
        System.out.println("response: \n" + jsonStrResponse + "\n");


    }

}