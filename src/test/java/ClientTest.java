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

    @Test
    @DisplayName("When send a GET with ID, Then will receive the client of that ID")
    public void getOneClientByID() {
        String paramId = "/7";
        String expectedResponse = "{\"id\":7,\"email\":\"michael.lawson@reqres.in\",\"first_name\":\"Michael\",\"last_name\":\"Lawson\"}";

        given()
                .contentType(JSON)
                .when()
                .get(url + resourceEndpoint + paramId)
                .then()
                .statusCode(200)
                .assertThat()
                .body(new IsEqual(expectedResponse))
                .extract()
                .response()
                .prettyPrint()
        ;
    }
    @Test
    @DisplayName("When send a GET with ID unregistered, Then will receive StatusCode 204 and empty")
    public void getNoneClientByID() {
        String paramId = "/2";

        given()
                .contentType(JSON)
                .when()
                .get(url + resourceEndpoint + paramId)
                .then()
                .statusCode(204)
        ;
    }

}
