package hookapi.tests.places;

import hookapi.DTO.UserDTO.UserDTO;
import hookapi.service.UserGenerator;
import hookapi.spec.BaseSpecification;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;

public class PlaceTests {
    private final UserGenerator userGenerator = UserGenerator.getInstance();
    private UserDTO user;
    private int placeId;

    @BeforeTest
    public void setUp() {
        user = userGenerator.createUser();
        RestAssured
                .given()
                .spec(BaseSpecification.baseDefautlRequestSpecification())
                .then()
                .spec(BaseSpecification.baseResponseSpecification());
    }

    @Test
    public void test01GetAllPlacesTest() {
        placeId = RestAssured
                .get("/place/get/all")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$.size()", greaterThan(0))
                .extract()
                .body().path("$.id");
    }

    @Test(dependsOnMethods = {"test01GetAllPlacesTest"})
    public void test02GetPlaceByIdTest() {
        RestAssured
                .get("/place/get/{id}", placeId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
