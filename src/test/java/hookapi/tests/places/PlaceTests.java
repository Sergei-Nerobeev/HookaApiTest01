package hookapi.tests.places;

import hookapi.spec.BaseSpecification;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.greaterThan;

public class PlaceTests {
    private ArrayList<?> placesId;
    Dotenv dotenv = Dotenv.load();
    @BeforeTest
    public void setUp() {
        RestAssured
                .given()
                .spec(BaseSpecification.baseDefautlRequestSpecification())
                .then()
                .spec(BaseSpecification.baseResponseSpecification());
    }
    @Test
    public void test01GetAllPlacesTest() {
        placesId = RestAssured
                .get("/place/get/all")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$.size()", greaterThan(0))
                .extract()
                .body().path("$.id");
    }
    @Test
    public void test02GetPlaceByIdTest() {
        RestAssured
                .get("/place/get/{id}", dotenv.get("PLACE_ID"))
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

}
