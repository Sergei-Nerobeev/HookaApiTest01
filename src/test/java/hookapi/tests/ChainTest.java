package hookapi.tests;

import hookapi.entity.auth.pojo.RequestAuthPojo;
import hookapi.entity.auth.pojo.ResponseAuthPojo;
import hookapi.entity.role.pojo.RequestRolePojo;
import hookapi.entity.role.pojo.ResponseRolePojo;
import hookapi.entity.user.pojo.ResponseCreateUser;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class ChainTest {
  private ResponseCreateUser responseCreateUser;
  private String phone;
  private String password;
  @BeforeTest
  public void prepareClient(){

    phone = String.valueOf(890000000 + new Random().nextInt(999999999));
    password = "1234567890";
//    responseCreateUser = UserService.createUser(new RequestCreateUser(password,phone));
  }
  //POST  логин-пароль c получ токена
  RequestAuthPojo requestAuthPojo =
      RequestAuthPojo.builder().phone("89999999999").password("123456789").build();

  @Test(description = "POST{{base_url}}/auth/login", priority = 1)
  public void createAuthLogin() {

    ResponseAuthPojo responseAuthPojo = given()
        .body(requestAuthPojo)
        .post("/auth/login")
        .then().statusCode(200)
        .extract().response().as(ResponseAuthPojo.class);

//POST на создание роли "user" с полученым токеном
    RequestRolePojo requestRolePojo = RequestRolePojo.builder().roleName("user").build();
    ResponseRolePojo responseRolePojo = given()
        .when()
        .header("Authorization", "Bearer " + responseAuthPojo.getAccessToken())
        .body(requestRolePojo)
        .post("/roles/create")
        .then()
        .statusCode(200)
        .extract().response().as(ResponseRolePojo.class);

  //GET на получение всех Ролей
    RestAssured.given()
        .when().header("Authorization", "Bearer " + responseAuthPojo.getAccessToken())
        .get("/roles/get/all")
        .then().statusCode(200); // написамть проверку
  }

  //POST на подключение Роли, как передать Список?
//  @Test(description = "POST{{base_url}}/roles/set.role/", priority = 2)
//  public void setRoleToUser() {
//    AccessToken accessToken = new AccessToken();
//    String token = accessToken.getToken();
//    RequestSetRole requestSetRole = RequestSetRole.builder().userId(24).roleIds(new ArrayList<>(24)).build();
//    ResponseSetRole responseSetRole = given()
//        .when().header("Authorization", "Bearer " + token)
//        .body(requestSetRole)
//        .post("roles/set.role/")
//        .then().statusCode(200).extract().as(ResponseSetRole.class);
//
//  }










}
