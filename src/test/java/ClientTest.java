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

    @Test
    @DisplayName("When send a POST to add a client, Then will receive StatusCode 201")
    public void postAddClient() {
        String requestBody = "{\"id\":\"3\",\"first_name\":\"Saulo11\"}";


        given()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post(url+resourceEndpoint)
                .then()
                .statusCode(201)
                .assertThat()
                .body(new IsEqual(requestBody))
                .extract()
                .response()
                .prettyPrint()
        ;
    }

    @Test
    @DisplayName("When send a PUT to update a client, Then will receive StatusCode 200")
    public void putUpdateOneClient() {
        String requestBody = "{\"id\":8,\"email\":\"lindsay.ferguson@reqres.in\",\"first_name\":\"Saulo Contente\",\"last_name\":\"Ferguson\"}";
        String paramId = "/8";

        given()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .put(url+resourceEndpoint+paramId)
                .then()
                .statusCode(200)
                .assertThat()
                .body(new IsEqual(requestBody))
                .extract()
                .response()
                .prettyPrint()
        ;
    }

    @Test
    @DisplayName("When send a DELETE a client, Then will receive StatusCode 200 and the list without that client")
    public void deleteOneClient() {
        String paramId = "/12";

        given()
                .contentType(JSON)
                .when()
                .delete(url+resourceEndpoint+paramId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPrint()
        ;
    }
}
