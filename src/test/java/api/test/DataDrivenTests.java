package api.test;

import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static api.endpoints.UserEndPoints.createUser;
import static api.endpoints.UserEndPoints.deleteUser;

public class DataDrivenTests {

    Response response;


    @Test(priority = 1, dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String firstName, String lastName, String userEmail, String password, String phone){

        UserPojo userPayload = new UserPojo();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstname(firstName);
        userPayload.setLastname(lastName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        response = createUser(userPayload);

        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName){

        response = deleteUser(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
