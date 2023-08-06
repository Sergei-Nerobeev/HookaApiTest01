package hookapi.tests.ownerstaff;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class OwnerStaffTest {
private UserGenerator userGenerator = UserGenerator.getInstance();
private RoleGenerator roleGenerator = new RoleGenerator();
private DbConnector dbConnector = DbConnector.getDbConnector();
private Dotenv dotenv = Dotenv.load();
private TokenHolder tokenHolder;
private ResponseCreateUser responseCreateUser;
private RequestCreatePlace requestCreatePlace;
private long idPlace;
private long idUser;

@BeforeTest
public void setUp() {
	RestAssured
		.given()
		.spec(BaseSpecification.baseDefautlRequestSpecification())
		.then()
		.spec(BaseSpecification.baseResponseSpecification());

	long idPlace;
	// Создаем нового пользователя и получаем его токен
	responseCreateUser = userGenerator.createNewUser();
	String token = userGenerator.requestAuthTokenForNewUser();
	tokenHolder = new TokenHolder();
	tokenHolder.setToken(token);

	// Назначаем пользователю роль овнера
	ResponseRolePojo responseRolePojo = roleGenerator.createNewOwnerRole(token);
	int idNewUser = responseCreateUser.getId();
	int idNewOwnerRole = responseRolePojo.getId();
	dbConnector.updateUserRole(idNewUser, idNewOwnerRole);
}

@AfterTest
public void tearDown() {
	// Удаление пользователя после каждого теста

} //TODO

@Test(priority = 1)
public void test01CreatePlace() {

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
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.extract().response().as(ResponseCreatePlace.class);

	this.idPlace = responseCreatePlace.getId();
	this.idUser = responseCreateUser.getId();
}

@Test(priority = 2, dependsOnMethods = {"test01CreatePlace"}) //TODO как проверить данные?
public void test02AddStaffToOwnerPlace() {

	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();

	//добавляем стафф к созданному place
	Set<Long> staff = new HashSet<>();
	staff.add(idUser);
	ResponseCreatePlace responseCreatePlace = given()
		.log().all()
		.header("Authorization", "Bearer " + newToken)
		.contentType(ContentType.JSON)
		.body(staff)
		.when()
		.post(dotenv.get("ADD_STAFF") + idPlace)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.extract().response().as(ResponseCreatePlace.class);
}

@Test(priority = 3,dependsOnMethods = {"test01CreatePlace"})
public void test02CreateOrderWith() {

	// Создаем Map для представления данных заказа
	Map<String, Object> requestBody = new HashMap<>();
	Map<String, Object> placeId = new HashMap<>();
	placeId.put("id", idPlace);
	Map<String, Object> userId = new HashMap<>();
	userId.put("id", idUser);
	Map<String, Object> comment = new HashMap<>();
	comment.put("text", "Заказ создан");

	requestBody.put("place_id", placeId);
	requestBody.put("user_id", userId);
	requestBody.put("order_time", "2023-06-06T10:00:00");
	requestBody.put("comment", comment);
	requestBody.put("orderStatus", "NEW");

	// Создаем заказ
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

@Test(priority = 4)
public void test04GetAssigned(){
	given()
		.log().all()
		.header("Authorization", "Bearer " + tokenHolder.getToken())
		.contentType(ContentType.JSON)
		.when()
		.get(dotenv.get("ASSIGNED"))
		.then().log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);


}
}


