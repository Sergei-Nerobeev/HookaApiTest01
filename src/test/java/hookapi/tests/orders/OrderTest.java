package hookapi.tests.orders;

import hookapi.DTO.UserDTO.UserDTO;
import hookapi.entity.order.request.PlaceId;
import hookapi.entity.order.request.RequestCreateOrder;
import hookapi.entity.order.request.UserId;
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

public class OrderTest {
private UserGenerator userGenerator = UserGenerator.getInstance();
private RoleGenerator roleGenerator = new RoleGenerator();
private DbConnector dbConnector = DbConnector.getDbConnector();
private Dotenv dotenv = Dotenv.load();
private TokenHolder tokenHolder;
private ResponseCreateUser responseCreateUser;

@BeforeTest
public void setUp() {
	RestAssured
		.given()
		.spec(BaseSpecification.baseDefautlRequestSpecification())
		.then()
		.spec(BaseSpecification.baseResponseSpecification());

	// Создаем нового пользователя и получаем его токен
	responseCreateUser = userGenerator.createNewUser();
	String token = userGenerator.requestAuthTokenForNewUser();
	tokenHolder = new TokenHolder();
	tokenHolder.setToken(token);

	// Назначаем пользователю роль администратора
	ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);
	int idNewUser = responseCreateUser.getId();
	int idNewAdminRole = responseRolePojo.getId();
	dbConnector.updateUserRole(idNewUser, idNewAdminRole);
}
@Test
public void test01CreateOrder() {
	// Создаем Map для представления данных заказа
	Map<String, Object> requestBody = new HashMap<>();
	Map<String, Object> placeId = new HashMap<>();
	placeId.put("id", dotenv.get("PLACE_ID"));
	Map<String, Object> userId = new HashMap<>();
	userId.put("id", dotenv.get("USER_ID"));
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



@Test(dependsOnMethods = {"test01CreateOrder"})//TODO
public void test02GetOrderById() {
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();
	ValidatableResponse validatableResponse = RestAssured
		.given().header("Authorization", "Bearer " + newToken)
		.get(dotenv.get("ORDER_GET_BY_ID") + dotenv.get("ORDER_ID"))
		.then().log().all()
		.assertThat().statusCode(HttpStatus.SC_OK);
	// Проверяем ожидаемый формат ответа
	validatableResponse.body("id", equalTo(Integer.parseInt(dotenv.get("ORDER_ID"))))
		.body("order_time", equalTo("2023-06-06T10:00:00"))
		.body("comment.text", equalTo("Работает создание заказа"))
		.body("orderStatus", equalTo("NEW"));
}

@Test(dependsOnMethods = {"test01CreateOrder"})
public void test03UpdateOrder(){
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();
	RequestCreateOrder requestCreateOrder = new RequestCreateOrder();

	PlaceId placeId = new PlaceId(1);
	UserId userId = new UserId(1);
	requestCreateOrder.setPlaceId(placeId);
	requestCreateOrder.setUserId(userId);

	ValidatableResponse validatableResponse =
		given().header("Authorization", "Bearer " + newToken)
			.log().all()
			.contentType(ContentType.JSON).pathParam("id", dotenv.get("ORDER_ID"))
			.when()
			.body(requestCreateOrder)
			.post("/order/update/{id}")
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK);

}

@Test(dependsOnMethods = {"test01CreateOrder"})
public void test04CancelById() {
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();

	RestAssured.given().header("Authorization", "Bearer " + newToken)
		.log().all()
		.when()
		.post(dotenv.get("ORDER_CANCEL") + dotenv.get("ORDER_ID"))
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
}

@Test(dependsOnMethods = {"test01CreateOrder"})
public void test05GetAllOrders() {
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();
	Boolean newOnly = true;
	given()
		.header("Authorization", "Bearer " + newToken)
		.pathParam("currentPlaceId", dotenv.get("PLACE_ID"))
		.queryParam("new", newOnly)
		.when()
		.log().all()
		.get(dotenv.get("ORDERS_GET_ALL"))
		.then().assertThat().statusCode(HttpStatus.SC_OK);
}

@Test(dependsOnMethods = {"test01CreateOrder"}) // TODO
public void test06TakenOrderByID(){
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();

	given()
		.header("Authorization", "Bearer " + newToken)
		.log().all()
		.contentType(ContentType.JSON).pathParam("id", 1)
		.when()
		.post(dotenv.get("ORDER_TOKEN"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
}
@AfterTest
public void tearDown(){

}
}
