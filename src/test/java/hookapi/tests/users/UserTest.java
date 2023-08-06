package hookapi.tests.users;

import hookapi.DTO.UserDTO.UserDTO;
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
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class UserTest {

	private UserGenerator userGenerator = UserGenerator.getInstance();
	private TokenHolder tokenHolder = new TokenHolder();
	private RoleGenerator roleGenerator = new RoleGenerator();
	private ResponseCreateUser responseCreateUser = userGenerator.createNewUser();
	private DbConnector dbConnector = DbConnector.getDbConnector();
	private Dotenv dotenv = Dotenv.load();

	@BeforeTest
	public void setUp() {
		given()
			.spec(BaseSpecification.baseDefautlRequestSpecification())
			.then()
			.spec(BaseSpecification.baseResponseSpecification());

		// Получение и установка токена для пользователя
		int idNewUser = responseCreateUser.getId();
		String token = userGenerator.requestAuthTokenForNewUser();
		tokenHolder.setToken(token);

		// Создание администратора и привязка его к пользователю
		ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);
		int idNewAdminRole = responseRolePojo.getId();
		dbConnector.updateUserRole(idNewUser, idNewAdminRole);
	}

	@AfterTest
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

	@Test(priority = 1, description = "POST/user/create")
	public void test01createUser() {
		// Метод setUp() выполняет этот тестовый метод
	}

	@Test(priority = 2, dependsOnMethods = {"test01createUser"})
	public void test02getAllUsers() {
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

	@Test(priority = 3, dependsOnMethods = {"test01createUser"})
	public void test03getUserById() {
		String newToken = userGenerator.requestAuthTokenForNewUser();
		given()
			.header("Authorization", "Bearer " + newToken)
			.get("/user/get/{id}", dotenv.get("USER_ID"))
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_OK);
	}

	@Test(priority = 4, dependsOnMethods = {"test01createUser"})
	public void test04UpdateUser() {
		String newToken = userGenerator.requestAuthTokenForNewUser();

		RequestUpdateUser requestUpdateUser = new RequestUpdateUser();
		requestUpdateUser.setName(dotenv.get("USER_NAME"));

		given()
			.log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer " + newToken)
			.pathParam("id", dotenv.get("USER_ID"))
			.body(requestUpdateUser)
			.put(dotenv.get("USER_UPDATE"))
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK);
	}

	@Test(priority = 5, dependsOnMethods = {"test01createUser"})
	public void test05DisbandUserById() {
		String newToken = userGenerator.requestAuthTokenForNewUser();
		given()
			.log().all()
			.header("Authorization", "Bearer " + newToken)
			.delete(dotenv.get("USER_DIS_BY_ID"), dotenv.get("USER_ID_DELETE"))
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK);
	}
}


