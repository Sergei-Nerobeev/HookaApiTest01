package hookapi.tests.places;

import hookapi.DTO.UserDTO;
import hookapi.entity.order.response.Address;
import hookapi.entity.place.RequestCreatePlace;
import hookapi.entity.place.ResponseCreatePlace;
import hookapi.entity.role.pojo.ResponseRolePojo;
import hookapi.entity.user.pojo.ResponseCreateUser;
import hookapi.jdbc.DbConnector;
import hookapi.service.RoleGenerator;
import hookapi.service.UserGenerator;
import hookapi.spec.BaseSpecification;
import hookapi.token.TokenHolder;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class PlaceTest {
private UserGenerator userGenerator = UserGenerator.getInstance();
private TokenHolder tokenHolder = new TokenHolder();
private RoleGenerator roleGenerator = new RoleGenerator();
private ResponseCreateUser responseCreateUser = userGenerator.createNewUser();
private DbConnector dbConnector = DbConnector.getDbConnector();
private Dotenv dotenv = Dotenv.load();
private long idPlace;
private String newToken;

@BeforeTest
public void setUp() {
    RestAssured.given()
      .spec(BaseSpecification.baseDefautlRequestSpecification())
      .then()
      .spec(BaseSpecification.baseResponseSpecification());

    int idNewUser = responseCreateUser.getId();
    String token = userGenerator.requestAuthTokenForNewUser();
    tokenHolder.setToken(token);

    ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);
    int idNewAdminRole = responseRolePojo.getId();
    dbConnector.updateUserRole(idNewUser, idNewAdminRole);

    UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
    ResponseCreateUser dtoUser = userDTO.getUser();
    String newToken = userGenerator.requestAuthTokenForNewUser();
}
@Test(priority = 1)
public void test01CreatePlace(){

    Address address = new Address();
    address.setId("");
    address.setCountry("USA");
    address.setAddress("LA");
    address.setLat(1.0);
    address.setLng(2.0);
    address.setCreatedAt("");
    address.setUpdatedAt("");
    address.setDeletedAt("");

    RequestCreatePlace place = new RequestCreatePlace();
    place.setName("Admin signature");
    place.setAddress(address);

    ResponseCreatePlace responseCreatePlace = given()
      .log().all()
      .header("Authorization", "Bearer " + newToken)
      .contentType(ContentType.JSON)
      .body(place)
      .when()
      .post(dotenv.get("PLACE_CREATE"))
      .then()
      .assertThat()
      .statusCode(HttpStatus.SC_OK)
      .extract().response().as(ResponseCreatePlace.class);

    this.idPlace = responseCreatePlace.getId();

}
@Test(priority = 2,dependsOnMethods = {"test01CreatePlace"})
public void test01CreateOrder() {
    // Создаем Map для представления данных заказа
    Map<String, Object> requestBody = new HashMap<>();
    Map<String, Object> placeId = new HashMap<>();
    placeId.put("id", idPlace);
    Map<String, Object> userId = new HashMap<>();
    userId.put("id", responseCreateUser.getId());
    Map<String, Object> comment = new HashMap<>();
    comment.put("text", "Работает создание заказа");

    requestBody.put("place_id", placeId);
    requestBody.put("user_id", userId);
    requestBody.put("order_time", "2023-06-06T10:00:00");
    requestBody.put("comment", comment);
    requestBody.put("orderStatus", "NEW");

    // Отправляем POST-запрос
    RestAssured.given()
      .log().all()
      .header("Authorization", "Bearer " + tokenHolder.getToken())
      .contentType(ContentType.JSON)
      .body(requestBody)
      .when()
      .post(dotenv.get("ORDER_CREATE"))
      .then()
      .assertThat()
      .statusCode(HttpStatus.SC_OK);
}

@Test(priority = 3)
public void test02GetAllPlaces() {
    RestAssured.get("/place/get/all")
      .then()
      .log().all()
      .assertThat()
      .statusCode(HttpStatus.SC_OK)
      .body("$.size()", greaterThan(0));
}
@Test(priority = 4,dependsOnMethods = {"test01CreatePlace"})
public void test03GetPlaceById() {
    given()
      .log().all()
      .header("Authorization", "Bearer " + tokenHolder.getToken())
      .get("/place/get/" + idPlace)
      .then()
      .log().all()
      .assertThat()
      .statusCode(HttpStatus.SC_OK);
}
@AfterTest //TODO
public void tearDown() {
    // Удаление пользователя после каждого теста
    UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
    ResponseCreateUser dtoUser = userDTO.getUser();
    String newToken = userGenerator.requestAuthTokenForNewUser();
    String id = String.valueOf(dtoUser.getId());

    given()
      .header("Authorization", "Bearer " + newToken)
      .delete(dotenv.get("USER_DIS_BY_ID"), id)
      .then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
}
}



