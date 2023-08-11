package hookapi.tests.exp;

import hookapi.DTO.UserDTO.UserDTO;
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
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AdminAndOwnerOrderIdTest {
private UserGenerator userGenerator = UserGenerator.getInstance();
private TokenHolder tokenHolder = new TokenHolder();
private RoleGenerator roleGenerator = new RoleGenerator();
private ResponseCreateUser responseCreateUser = userGenerator.createNewUser();
private DbConnector dbConnector = DbConnector.getDbConnector();
private Dotenv dotenv = Dotenv.load();
private long idPlace;
private int idUser;

@BeforeTest
public void setUp() {
	given()
		.spec(BaseSpecification.baseDefautlRequestSpecification())
		.then()
		.spec(BaseSpecification.baseResponseSpecification());
}
@AfterTest
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



	// Получение и установка токена для пользователя
	int idNewUser = responseCreateUser.getId();
	String token = userGenerator.requestAuthTokenForNewUser();
	tokenHolder.setToken(token);

	// Создание администратора и привязка его к пользователю
	ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);
	int idNewAdminRole = responseRolePojo.getId();
	dbConnector.updateUserRole(idNewUser, idNewAdminRole);


}
@Test(priority = 2, dependsOnMethods = {"test01CreateUser"})
public void test02CreatePlace(){

	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();
	int idUser = dtoUser.getId();
	this.idUser = idUser;

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
	userId.put("id",idUser);
	Map<String, Object> comment = new HashMap<>();
	comment.put("text", "Заказ создан ADMIN");

	requestBody.put("place_id", placeId);
	requestBody.put("user_id", userId);
	requestBody.put("order_time", "2023-06-06T10:00:00");
	requestBody.put("comment", comment);
	requestBody.put("orderStatus", "NEW");

	RestAssured.given()
		.log().all()
		.header("Authorization", "Bearer " + tokenHolder.getToken())
		.contentType(ContentType.JSON)
		.body(requestBody)
		.when()
		.post(dotenv.get("ORDER_CREATE"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);

	Assert.assertEquals(idUser, (int) userId.get("id"), "idUser doesn't match");
	Assert.assertEquals(idPlace, (long) placeId.get("id"), "idPlace doesn't match");

	Assert.assertEquals(idUser, (int) userId.get("id"), "idUser doesn't match. Expected: " + idUser + ", Actual: " + userId.get("id"));
	Assert.assertEquals(idPlace, (long) placeId.get("id"), "idPlace doesn't match. Expected: " + idPlace + ", Actual: " + placeId.get("id"));
	}
@Test(priority = 4)
public void test04CreateUser() {


	// Получение и установка токена для пользователя
	int idNewUser = responseCreateUser.getId();
	String token = userGenerator.requestAuthTokenForNewUser();
	tokenHolder.setToken(token);

	// Создание owner и привязка его к пользователю
	ResponseRolePojo responseRolePojo = roleGenerator.createNewOwnerRole(token);
	int idNewOwnerRole = responseRolePojo.getId();
	dbConnector.updateUserRole(idNewUser, idNewOwnerRole);

	}
@Test(priority = 5, dependsOnMethods = {"test04CreateUser"})
public void test05CreatePlace(){

	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();
	int idUser = dtoUser.getId();
	this.idUser = idUser;

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
	place.setName("Owner signature");
	place.setAddress(address);

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
@Test(priority = 6, dependsOnMethods = {"test05CreatePlace"})
public void test06CreateOrder() {
	// Создаем Map для представления данных заказа
	Map<String, Object> requestBody = new HashMap<>();
	Map<String, Object> placeId = new HashMap<>();
	placeId.put("id", idPlace);
	Map<String, Object> userId = new HashMap<>();
	userId.put("id",idUser);
	Map<String, Object> comment = new HashMap<>();
	comment.put("text", "Заказ создан OWNER");

	requestBody.put("place_id", placeId);
	requestBody.put("user_id", userId);
	requestBody.put("order_time", "2023-06-06T10:00:00");
	requestBody.put("comment", comment);
	requestBody.put("orderStatus", "NEW");

	RestAssured.given()
		.log().all()
		.header("Authorization", "Bearer " + tokenHolder.getToken())
		.contentType(ContentType.JSON)
		.body(requestBody)
		.when()
		.post(dotenv.get("ORDER_CREATE"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);

	Assert.assertEquals(idUser, (int) userId.get("id"), "idUser doesn't match");
	Assert.assertEquals(idPlace, (long) placeId.get("id"), "idPlace doesn't match");

	Assert.assertEquals(idUser, (int) userId.get("id"), "idUser doesn't match. Expected: " + idUser + ", Actual: " + userId.get("id"));
	Assert.assertEquals(idPlace, (long) placeId.get("id"), "idPlace doesn't match. Expected: " + idPlace + ", Actual: " + placeId.get("id"));
	System.out.println();
}

}
