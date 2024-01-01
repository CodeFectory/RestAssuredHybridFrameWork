package api.test;

import api.payload.UserPojo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import static api.endpoints.UserEndPoints.*;

public class UserTests {

    Faker faker;
    UserPojo payloadData;
    Response response;

    public Logger logger;


    //before test execution we need to generate the data
    @BeforeClass
    public void setUp(){

        faker = new Faker();
        payloadData = new UserPojo();

        payloadData.setId(faker.idNumber().hashCode()); //hashCode is used for random id generation
        payloadData.setUsername(faker.name().username());
        payloadData.setFirstname(faker.name().firstName());
        payloadData.setLastname(faker.name().lastName());
        payloadData.setEmail(faker.internet().safeEmailAddress());
        payloadData.setPassword(faker.internet().password(5,10));
        payloadData.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void createUserTest(){

        logger.info("***************** Creating the User *****************");
        response = createUser(payloadData);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***************** User is created *****************");
    }

    @Test(priority = 2)
    public void getUserTest(){

        logger.info("***************** Reading user information *****************");
        response = readUser(this.payloadData.getUsername());

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***************** User information is displayed *****************");
    }

    @Test(priority = 3)
    public void updateUserTest(){

        logger.info("***************** Updating User *****************");
        payloadData.setFirstname(faker.name().firstName());
        payloadData.setLastname(faker.name().lastName());
        payloadData.setEmail(faker.internet().safeEmailAddress());
        payloadData.setPassword(faker.internet().password(5,10));
        payloadData.setPhone(faker.phoneNumber().cellPhone());

        response = updateUser(payloadData,this.payloadData.getUsername());
        response.then().log().all();

        response.then().log().body().statusCode(200); //rest assured validation
        Assert.assertEquals(response.getStatusCode(),200); // testNG validation

        //checking if data is updated or not
        Response newResponse = readUser(this.payloadData.getUsername());

        newResponse.then().log().all();
        Assert.assertEquals(newResponse.getStatusCode(),200);

        logger.info("***************** User is Updated *****************");

    }

    @Test(priority = 4)
    public void deleteUserTest(){

        logger.info("***************** Deleting User *****************");


        response = deleteUser(this.payloadData.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("***************** User is deleted *****************");

    }
}
