package hookahapi.generator;

import hookahapi.dto.UserDto;
import hookahapi.jdbc.Db;
import hookahapi.model.responce.UserResModel;
import hookahapi.service.UserRandom;
import hookahapi.spec.BaseSpec;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class UserGen {

private static UserGen generator;
private UserRandom userRandom = new UserRandom();
Dotenv dotenv = Dotenv.load();

public UserGen() {}
public static UserGen getInstance() {
	if (generator == null) {
		generator = new UserGen();
	}
	return generator;
}

public UserDto createUser(String roleNewName)
{
	var user = given().spec(BaseSpec.baseSpec())
		.when()
		.body(userRandom)
		.post(dotenv.get("USER_CREATE"))
		.then()
		.statusCode(200)
		.log().all().extract().response().as(UserResModel.class);

	var token = requestAuthTokenForNewUser();
	var role = new RoleGen().createRole(token,roleNewName);

	Db.getDbConnector().updateUserRoleSet(user.getId(), role.getId());
	return new UserDto(user, token);
}

public String requestAuthTokenForNewUser()
{
	return given()
		.spec(BaseSpec.baseSpec())
		.contentType(ContentType.JSON)
		.body(userRandom)
		.post(dotenv.get("AUTH"))
		.then()
		.statusCode(200)
		.log().all().extract().path("accessToken");
}


}
