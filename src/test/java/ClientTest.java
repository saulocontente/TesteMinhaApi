import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class ClientTest {

    private String url = "http://localhost:3000";
    private String resourceEndpoint = "/client";

    @Test
    @DisplayName("When send a GET, Then will receive a list with all registered clients")
    public void getAllClients(){
        List<Integer> expectedIds;

        given()
                .contentType(JSON)
                .when()
                .get(url + resourceEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .print()
        ;
    }

}
