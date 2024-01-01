package api.endpoints;

//This class is created to perform the CRUD(Create Retrieve Update Delete) requests operations for user module APIs.

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(UserPojo payload){
        Response response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when().post(user_post_url);
        return response;
    }

    public static Response readUser(String userName){
        Response response =  given()
                .pathParams("username",userName)
                .when().get(user_get_url);
        return response;
    }

    public static Response updateUser(UserPojo payload,String userName){
        Response response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParams("username",userName)
                .when().put(user_put_url);
        return response;
    }

    public static Response deleteUser(String userName){
        Response response =  given()
                .pathParams("username",userName)
                .when().delete(user_delete_url);
        return response;
    }

}
