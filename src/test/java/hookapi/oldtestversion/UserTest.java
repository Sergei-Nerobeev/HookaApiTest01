package hookapi.oldtestversion;

import hookapi.DTO.UserDTO;
import hookapi.entity.role.pojo.ResponseRolePojo;
import hookapi.entity.user.pojo.ResponseCreateUser;
import hookapi.entity.user.updateuser.RequestUpdateUser;
import hookapi.jdbc.DbConnector;
import hookapi.service.RoleGenerator;
import hookapi.service.UserGenerator;
import hookapi.spec.BaseSpecification;
import hookapi.token.TokenHolder;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserTest {
UserGenerator userGenerator = UserGenerator.getInstance();
TokenHolder tokenHolder = new TokenHolder();
RoleGenerator roleGenerator = new RoleGenerator();
ResponseCreateUser responseCreateUser = userGenerator.createNewUser();
DbConnector dbConnector = DbConnector.getDbConnector();
Dotenv dotenv = Dotenv.load();

@BeforeTest
public void setUp() {
	given()
		.spec(BaseSpecification.baseDefautlRequestSpecification())
		.then()
		.spec(BaseSpecification.baseResponseSpecification());
}

@Test(description = "POST/user/create")
public void test01createUser() {

	int idNewUser = responseCreateUser.getId();
	String token = userGenerator.requestAuthTokenForNewUser();
	tokenHolder.setToken(token);

	ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);

	int idNewAdminRole = responseRolePojo.getId();
	dbConnector.updateUserRole(idNewUser, idNewAdminRole);
}

@Test(dependsOnMethods = {"test01createUser"})
public void test02getAllUsers() {
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();
	given()
		.header("Authorization", "Bearer " + newToken)
		.when()
		.get(dotenv.get("USERS_GET_ALL"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
}

@Test(dependsOnMethods = {"test01createUser"})
public void test03getUserById() {
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();
	given()
		.header("Authorization", "Bearer " + newToken)
		.get("/user/get/{id}", dotenv.get("USER_ID"))
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
}

@Test(dependsOnMethods = {"test01createUser"})
public void test04UpdateUser() {
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();

	RequestUpdateUser requestUpdateUser = new RequestUpdateUser();
	requestUpdateUser.setName(dotenv.get("USER_NAME"));
	ValidatableResponse validatableResponse = given()
		.log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer " + newToken)
		.pathParam("id", dotenv.get("USER_ID"))
		.body(requestUpdateUser)
		.put(dotenv.get("USER_UPDATE"))
		.then()
		.assertThat().statusCode(HttpStatus.SC_OK);
}

@Test(dependsOnMethods = {"test01createUser"})
public void test05DisbandUserById() {
	UserDTO userDTO = new UserDTO(responseCreateUser, tokenHolder.getToken());
	ResponseCreateUser dtoUser = userDTO.getUser();
	String newToken = userGenerator.requestAuthTokenForNewUser();
	given()
		.header("Authorization", "Bearer " + newToken)
		.delete(dotenv.get("USER_DIS_BY_ID"), dotenv.get("USER_ID_DELETE"))
		.then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
}

//@AfterTest
public void tearDown() {
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
