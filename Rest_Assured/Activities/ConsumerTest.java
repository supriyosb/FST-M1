package liveProject;


import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(PactConsumerTestExt.class)


public class ConsumerTest {

    Map<String, String> headers = new HashMap<>();

    @Pact(consumer = "UserConsumer", provider = "UserProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        headers.put("Content-Type", "application/json");
        DslPart reqAndResponseBody = new PactDslJsonBody()
                .numberType("id", 125)
                .stringType("firstName", "Thanu")
                .stringType("lastName","Vallu")
                .stringType("email", "Thanu@gmail.com");

        return builder.given("Post request").uponReceiving("Create Post request").method("POST").path("/api/users").headers(headers).
                body(reqAndResponseBody).willRespondWith().
                status(201).body(reqAndResponseBody).toPact();


    }

    @Test
    @PactTestFor(providerName = "UserProvider",port="8282")
    public void postRequestTest(){
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("id", 125);
        reqBody.put("firstName", "Thanu");
        reqBody.put("lastName","Vallu");
        reqBody.put("email", "thanu@gmail.com");
        given().baseUri("http://localhost:8282").headers(headers).body(reqBody).log().all().when().post("/api/users").then().statusCode(201).body("firstName", equalTo("Thanu)).log().all();



    }
}