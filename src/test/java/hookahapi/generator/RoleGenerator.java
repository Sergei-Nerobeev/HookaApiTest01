package hookahapi.generator;

import hookapi.entity.role.pojo.RequestRolePojo;
import hookapi.entity.role.pojo.ResponseRolePojo;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class RoleGenerator {

RequestRolePojo requestRolePojo = RequestRolePojo.builder().roleName("ADMIN").build();
public ResponseRolePojo createNewAdminRole(String token){
	return given()
		.when().contentType(ContentType.JSON)
		.header("Authorization", "Bearer " + token)
		.body(requestRolePojo).log().all()
		.post("/roles/create")
		.then().log().all()
		.statusCode(200)
		.extract().response().as(ResponseRolePojo.class);
}

}
