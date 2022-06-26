package app;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CountryAPITest extends CountryAPIDBTestData {


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
    public void shouldReturnCountryById() throws SQLException {
        final String countryName = "AU";
        Long id = sqlRequest("SELECT max(id) from country").getLong("max") + 1;
        addCountry(id, countryName);

        when()
                .get("/api/countries/%s".formatted(id.toString()))
                .then()
                .statusCode(200)
                .body("countryName", equalTo(countryName));

        deleteCountry(id);
    }

    @Test
    @DisplayName("Update country with id with PUT \"/api/countries/{id}\"")
    @Disabled("error.http.500 Failure during data access")
    public void shouldUpdateCountryById() throws SQLException {
        final String countryName = "ZF";
        final String newCountryName = "TE";
        int id = sqlRequest("SELECT max(id) from country").getInt("max") + 1;
        addCountry((long) id, countryName);

        given()
                .body(String.format(
                        """
                                {
                                   "id": %s,
                                   "countryName": "%s"
                                 }
                                 """, id, newCountryName)
                )
                .contentType("application/json")
                .pathParam("countryId", id)
                .when()
                .put("/api/countries/{countryId}")
                .then()
                .statusCode(200)
                .body("countryName", equalTo(getCountryById((long) id).getString("country_name")))
                .body("id", equalTo(id));

        deleteCountry((long) id);
    }

    @Test
    @DisplayName("Create country with POST \"/api/countries\"")
    public void shouldCreateCountry() throws SQLException {
        final String countryName = "AB";
        Response response = given()
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
                .body("countryName", equalTo(countryName))
                .extract().response();

        Long id = response.jsonPath().getLong("id");
        deleteCountry(id);
    }

    @Test
    @DisplayName("Delete country with id with DELETE \"/api/countries/{id}\"")
    public void shouldDeleteCountryById() throws SQLException {
        final String countryName = "AU";
        Long id = sqlRequest("SELECT max(id) from country").getLong("max") + 1;
        addCountry(id, countryName);

        given()
                .pathParam("countryId", id)
                .when()
                .delete("/api/countries/{countryId}")
                .then()
                .statusCode(204);
        assertNull(getCountryById(id));
    }
}
