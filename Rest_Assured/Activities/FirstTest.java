package Examples;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstTest
{

    @Test
    public void getRequestwithqueryParm(){
        Response Responce=given().baseUri("https://petstore.swagger.io/v2/pet").
                header("Content-Type","application/json").
                queryParam("status","alive").log().all().when().get("/findByStatus)");

        //System.out.println(Response.getHeaders());
        System.out.println(Responce.getBody().asPrettyString());
        String petstatus=Responce.then().extract().path("['status']");

        //System.out.println("pet status is:" +petstatus);
        //  Assert.assertEquals(petstatus,"alive");


    }
}
