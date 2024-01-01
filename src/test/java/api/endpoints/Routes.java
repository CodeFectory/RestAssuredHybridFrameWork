package api.endpoints;



/*
* --->This URLs is for the User modules
Request URL:
Swagger - https://petstore.swagger.io/
Create User - https://petstore.swagger.io/v2/user
Get User - https://petstore.swagger.io/v2/user/{username}
Update User - https://petstore.swagger.io/v2/user/{username}
Delete User - https://petstore.swagger.io/v2/user/{username}
*
* */



public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";

    //user modules request urls

    public static String user_post_url = base_url+"/user";
    public static String user_get_url = base_url+"/user/{username}";
    public static String user_put_url = base_url+"/user/{username}";
    public static String user_delete_url = base_url+"/user/{username}";

    //add more module urls accordingly

}
