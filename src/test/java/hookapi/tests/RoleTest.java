package hookapi.tests;

import hookapi.spec.BaseSpecification;
import hookapi.suites.auth.pojo.RequestAuthPojo;
import hookapi.suites.auth.pojo.ResponseAuthPojo;
import hookapi.suites.roles.pojo.RequestRolePojo;
import hookapi.suites.roles.pojo.ResponseRolePojo;
import hookapi.suites.user.RequestCreateUser;
import hookapi.suites.user.ResponseCreateUser;
import hookapi.token.AccessToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RoleTest { //Post на аунт логин-пароль c получ токена
  @BeforeTest
  public void setBaseSpecification(){
  BaseSpecification.installSpecification(BaseSpecification.baseRequestSpecification("http://localhost",
  8080),BaseSpecification.baseResponseSpecification());
  }
  RequestAuthPojo requestAuthPojo =
  RequestAuthPojo.builder().phone("89999999999").password("123456789").build();
  @Test(description = "POST{{base_url}}/auth/login", priority = 1)
  public void createAuthLogin(){

    ResponseAuthPojo responseAuthPojo = given()
        .body(requestAuthPojo)
        .post("/auth/login")
        .then().statusCode(200)
        .extract().response().as(ResponseAuthPojo.class);

//POST на создание роли "user" с полученым токеном

    RequestRolePojo requestRolePojo = RequestRolePojo.builder().roleName("user").build();
    ResponseRolePojo responseRolePojo = given()
        .when()
        .header("Authorization",  "Bearer " + responseAuthPojo.getAccessToken())
        .body(requestRolePojo)
        .post("/roles/create")
        .then()
        .statusCode(200)
        .extract().response().as(ResponseRolePojo.class);

//GET на получение всех Ролей

    RestAssured.given()
        .when().header("Authorization",  "Bearer " + responseAuthPojo.getAccessToken())
        .get("/roles/get/all")
        .then().statusCode(200); // написамть проверку

//POST на подключение Роли, как передать Список? Хэш мапа!

//RequestSetRole requestSetRole = RequestSetRole.builder().userId(3).roleIds().build();
  }
//POST на создание нового user

  RequestCreateUser requestCreateUser = RequestCreateUser.builder().phone("89999999999").password("123456789").build();
  @Test(description = " POST{{base_url}}/user/create",priority = 2)

  public void createUser(){

    ResponseCreateUser responseCreateUser = given()
        .when()
        .header("Authorization",  "Bearer " + responseAuthPojo.getAccessToken() )
        .body(requestCreateUser)
        .post("/user/create")
        .then()
        .statusCode(200)
        .extract().response().as(ResponseCreateUser.class);



  }


}
