package app;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class CountryAPITest {

    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Get country by id with GET \"/api/countries/{id}\"")
    public void shouldReturnCountryById() {
        when()
                .get("/api/countries/1")
        .then()
                .statusCode(200)
                .body("countryName", equalTo("vo"));
    }

    @Test
    @DisplayName("Update country with id with PUT \"/api/countries/{id}\"")
    public void shouldUpdateCountryById() {
        given()
                .body(
                        """
                               {
                                  "id": 2,
                                  "countryName": "US"
                                }
                                """
                )
                .contentType("application/json")
        .when()
                .put("/api/countries/2")
        .then()
                .statusCode(200)
                .body("countryName", equalTo("US"))
                .body("id", equalTo(2));
    }

    @Test
    @DisplayName("Create country with POST \"/api/countries\"")
    public void shouldCreateCountry() {
        final String countryName = "NT";
        given()
                .body(
                        """
                               {
                                  "countryName": "%s"
                                }
                                """.formatted(countryName)
                )
                .contentType("application/json")
        .when()
                .post("/api/countries")
        .then()
                .statusCode(201)
                .body("countryName", equalTo(countryName));
    }

    @Test
    @DisplayName("Delete country with id with DELETE \"/api/countries/{id}\"")
    public void shouldDeleteCountryById() {
         when()
                .delete("/api/countries/6")
         .then()
                .statusCode(204);
    }
}
