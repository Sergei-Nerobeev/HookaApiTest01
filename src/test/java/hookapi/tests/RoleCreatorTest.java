package hookapi.tests;

import hookapi.spec.BaseSpecification;
import hookapi.suites.auth.pojo.RequestAuthPojo;
import hookapi.suites.auth.pojo.ResponseAuthPojo;
import hookapi.suites.roles.pojo.RequestRolePojo;
import hookapi.suites.roles.pojo.ResponseRolePojo;
import hookapi.suites.roles.setrole.RequestSetRole;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RoleCreatorTest { //Post на аунт логин-пароль c получ токена
  RequestAuthPojo requestAuthPojo =
  RequestAuthPojo.builder().phone("89999999999").password("123456789").build();
  @Test
  public void createAuthLogin(){

    BaseSpecification.installSpecification(BaseSpecification.baseRequestSpecification("http://localhost",
        8080),BaseSpecification.baseResponseSpecification());

    ResponseAuthPojo responseAuthPojo = given()
        .body(requestAuthPojo)
        .post("/auth/login")
        .then().statusCode(200)
        .extract().response().as(ResponseAuthPojo.class);

    //Post на создание роли "user" с полученым токеном

    RequestRolePojo requestRolePojo = RequestRolePojo.builder().roleName("user").build();
    ResponseRolePojo responseRolePojo = given()
        .when()
        .header("Authorization",  "Bearer " + responseAuthPojo.getAccessToken())
        .body(requestRolePojo)
        .post("/roles/create")
        .then()
        .statusCode(200)
        .extract().response().as(ResponseRolePojo.class);

    //Get на получение всех Ролей

    RestAssured.given()
        .when().header("Authorization",  "Bearer " + responseAuthPojo.getAccessToken())
        .get("/roles/get/all")
        .then().statusCode(200); // написамть проверку

    //Post на подключение Роли, как передать Список? Хэш мапа!

    //RequestSetRole requestSetRole = RequestSetRole.builder().userId(3).roleIds().build();

    //Post на создание User, добавить нумерацию?


  }



}
