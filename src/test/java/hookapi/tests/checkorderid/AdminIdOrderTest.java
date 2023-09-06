package hookapi.tests.checkorderid;

import hookapi.DTO.UserDTO;
import hookapi.entity.order.response.Address;
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
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AdminIdOrderTest {
	private UserGenerator userGenerator = UserGenerator.getInstance();
	private TokenHolder tokenHolder = new TokenHolder();
	private RoleGenerator roleGenerator = new RoleGenerator();
	private ResponseCreateUser responseCreateUser = userGenerator.createNewUser();
	private DbConnector dbConnector = DbConnector.getDbConnector();
	private Dotenv dotenv = Dotenv.load();
	private long idPlace;
	private int idUser;
	private long idOrder;
  private String newToken;

	@BeforeTest
	public void setUp() {

		// Подключение к локальному серверу
		given()
			.spec(BaseSpecification.baseDefautlRequestSpecification())
			.then()
			.spec(BaseSpecification.baseResponseSpecification());

		// Получение и установка токена для пользователя (регистрация и аутентификация)
		int idNewUser = responseCreateUser.getId();
		String token = userGenerator.requestAuthTokenForNewUser();
		tokenHolder.setToken(token);

		// Создание роли Админ и привязка его к пользователю
		ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);
		int idNewAdminRole = responseRolePojo.getId();
		dbConnector.updateUserRole(idNewUser, idNewAdminRole);

		// Получение и установка токена для нового пользователя с ролью Админ
		UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
		ResponseCreateUser dtoUser = userDTO.getUser();
	}

	@AfterTest //TODO
	public void tearDown() {
		// Удаление пользователя после каждого теста
//	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
//	ResponseCreateUser dtoUser = userDTO.getUser();
//	String newToken = userGenerator.requestAuthTokenForNewUser();
//	String id = String.valueOf(dtoUser.getId());
//
//	given()
//		.header("Authorization", "Bearer " + newToken)
//		.delete(dotenv.get("USER_DIS_BY_ID"), id)
//		.then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
	}

	@Test(priority = 1)
	public void test01CreateUser() {
	// Метод setUp() выполняет этот тестовый метод
	}
	@Test(priority = 2, dependsOnMethods = {"test01CreateUser"})
	public void test02CreatePlace() {

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
		place.setName("ADMIN signature");
		place.setAddress(address);

		this.newToken = userGenerator.requestAuthTokenForNewUser();

		ResponseCreatePlace responseCreatePlace = given()
			.log().all()
			.header("Authorization", "Bearer " + newToken)
			.contentType(ContentType.JSON)
			.body(place)
			.when()
			.post(dotenv.get("PLACE_CREATE"))
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
			.extract().response().as(ResponseCreatePlace.class);

		this.idPlace = responseCreatePlace.getId();
	}

	@Test(priority = 3, dependsOnMethods = {"test02CreatePlace"})
	public void test03CreateOrder() {
		// Создаем Map для представления данных заказа
		Map<String, Object> requestBody = new HashMap<>();
		Map<String, Object> placeId = new HashMap<>();
		placeId.put("id", idPlace);
		Map<String, Object> userId = new HashMap<>();
		userId.put("id", idUser);
		Map<String, Object> comment = new HashMap<>();
		comment.put("text", "Заказ создан ADMIN");

		requestBody.put("place_id", placeId);
		requestBody.put("user_id", userId);
		requestBody.put("order_time", "2023-06-06T10:00:00");
		requestBody.put("comment", comment);
		requestBody.put("orderStatus", "NEW");

		ResponseCreateOrder responseCreateOrder =
			(ResponseCreateOrder) given()
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
@Test(priority = 4, dependsOnMethods = {"test03CreateOrder"})//TODO check IDPlace and IdUser
public void test04GetOrderById() {

	ValidatableResponse validatableResponse = RestAssured
		.given().header("Authorization", "Bearer " + newToken)
		.get(dotenv.get("ORDER_GET_BY_ID") + idOrder)
		.then()
		.log().all()
		.assertThat().statusCode(HttpStatus.SC_OK);

}
@Test(priority = 5, dependsOnMethods = {"test02CreatePlace"})
public void test05GetPlaceById() {
	ValidatableResponse validatableResponse = RestAssured
		.given().header("Authorization", "Bearer " + newToken)
		.get(dotenv.get("PLACE_GET_BY_ID") + idPlace)
		.then()
		.log().all()
		.assertThat().statusCode(HttpStatus.SC_OK);

}
}

