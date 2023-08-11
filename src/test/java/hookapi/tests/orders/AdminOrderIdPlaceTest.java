package hookapi.tests.orders;

import hookapi.DTO.UserDTO.UserDTO;
import hookapi.entity.order.response.ResponseCreateOrder;
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
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

// получение заказа по id места admin
public class AdminOrderIdPlaceTest {
private UserGenerator userGenerator = UserGenerator.getInstance();
private RoleGenerator roleGenerator = new RoleGenerator();
private DbConnector dbConnector = DbConnector.getDbConnector();
private Dotenv dotenv = Dotenv.load();
private TokenHolder tokenHolder;
private ResponseCreateUser responseCreateUser;
private RequestCreatePlace requestCreatePlace;
private long idPlace;
private long idUser;

private long idOrder;


@BeforeTest
public void setUp() {
	RestAssured
		.given()
		.spec(BaseSpecification.baseDefautlRequestSpecification())
		.then()
		.spec(BaseSpecification.baseResponseSpecification());

	long idUser;

	// Создаем нового пользователя и получаем его токен
	responseCreateUser = userGenerator.createNewUser();
	String token = userGenerator.requestAuthTokenForNewUser();
	tokenHolder = new TokenHolder();
	tokenHolder.setToken(token);

	// Назначаем пользователю роль admin
	ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);
	int idNewUser = responseCreateUser.getId();
	int idNewAdminRole = responseRolePojo.getId();
	dbConnector.updateUserRole(idNewUser, idNewAdminRole);
}
@AfterTest
public void tearDown() {
	// Удаление пользователя после каждого теста

} //TODO

@Test(priority = 1)
public void test01CreatePlace(){

	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();

	Map<String, Object> address = new HashMap<>();
	address.put("id", "");
	address.put("country", "USA");
	address.put("address", "LA");
	address.put("lat", 1.0);
	address.put("lng", 2.0);
	address.put("created_at", "");
	address.put("updated_at", "");
	address.put("deleted_at", "");

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
	this.idUser = responseCreateUser.getId();
}

@Test(priority = 2,dependsOnMethods = {"test01CreatePlace"})
public void test02CreateOrder() {
	// Создаем Map для представления данных заказа
	Map<String, Object> requestBody = new HashMap<>();
	Map<String, Object> placeId = new HashMap<>();
	placeId.put("id", idPlace);
	Map<String, Object> userId = new HashMap<>();
	userId.put("id", responseCreateUser.getId());
	Map<String, Object> comment = new HashMap<>();
	comment.put("text", "Заказ создан");

	requestBody.put("place_id", placeId);
	requestBody.put("user_id", userId);
	requestBody.put("order_time", "2023-06-06T10:00:00");
	requestBody.put("comment", comment);
	requestBody.put("orderStatus", "NEW");

	ResponseCreateOrder responseCreateOrder =
		given()
		.log().all()
		.header("Authorization", "Bearer " + tokenHolder.getToken())
		.contentType(ContentType.JSON)
		.body(requestBody)
		.when()
		.post(dotenv.get("ORDER_CREATE"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.extract().response().as(ResponseCreateOrder.class);

	this.idOrder = responseCreateOrder.getId();

}
@Test(priority = 3, dependsOnMethods = {"test02CreateOrder"})
public void test03GetOrderById() {

	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();

	ValidatableResponse validatableResponse = RestAssured
		.given().header("Authorization", "Bearer " + newToken)
		.log().all()
		.when()
		.get(dotenv.get("ORDER_GET_BY_ID") + idOrder)
		.then()
		.log().all()
		.assertThat().statusCode(HttpStatus.SC_OK);

	validatableResponse.body("id", equalTo(idPlace))
		.body("order_time", equalTo("2023-06-06T10:00:00"))
		.body("comment.text", equalTo("Работает создание заказа"))
		.body("orderStatus", equalTo("NEW"));
}

}
