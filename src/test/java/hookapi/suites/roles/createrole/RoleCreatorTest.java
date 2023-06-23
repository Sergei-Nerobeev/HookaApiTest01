package hookapi.suites.roles.createrole;

import hookapi.suites.auth.pojo.RequestAuthPojo;
import hookapi.suites.roles.pojo.RequestRolePojo;
import hookapi.suites.roles.pojo.ResponseRolePojo;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RoleCreatorTest { //пост на аунт логин-пароль, получ токена и пост на создание роли с токеном
  RequestAuthPojo requestAuthPojo =
  RequestAuthPojo.builder().phone("89999999999").password("123456789").build();
  @Test
  public void createAuthLogin(){
    String token = given().log().all()
        .when().contentType(ContentType.JSON)
        .body(requestAuthPojo)
        .contentType(ContentType.JSON)
        .post("http://localhost:8080/auth/login")
        .then().statusCode(200).log().all()
        .extract().response().jsonPath().getString("accessToken");

    RequestRolePojo requestRolePojo = RequestRolePojo.builder().roleName("user").build();
    ResponseRolePojo responseRolePojo = given()
        .log().all()
        .when()
        .contentType(ContentType.JSON)
        .header("Authorization",  "Bearer " + token )
        .body(requestRolePojo)
        .contentType(ContentType.JSON)
        .post("http://localhost:8080/roles/create")
        .then()
        .statusCode(200).log().all()
        .extract().response().as(ResponseRolePojo.class);
  }
}
