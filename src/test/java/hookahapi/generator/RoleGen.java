package hookahapi.generator;

import hookahapi.model.request.CreateRoleReqModel;
import hookahapi.model.responce.RoleResModel;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class RoleGen { //TODO abs?


public RoleResModel createRole(String token,String roleNewName){

	CreateRoleReqModel role = CreateRoleReqModel.builder().roleName(roleNewName).build();

	return given()
		.when().contentType(ContentType.JSON)
		.header("Authorization", "Bearer " + token)
		.body(role).log().all()
		.post("/roles/create")
		.then().log().all()
		.statusCode(200)
		.extract().response().as(RoleResModel.class);
}

}
