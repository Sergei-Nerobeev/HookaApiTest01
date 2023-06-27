package hookapi.tests;

import hookapi.spec.BaseSpecification;
import hookapi.suites.auth.pojo.RequestAuthPojo;
import hookapi.suites.auth.pojo.ResponseAuthPojo;
import hookapi.suites.roles.pojo.RequestRolePojo;
import hookapi.suites.roles.pojo.ResponseRolePojo;
import hookapi.suites.user.createuser.RequestCreateUser;
import hookapi.suites.user.createuser.ResponseCreateUser;
import hookapi.suites.user.updateuser.RequestUpdateUser;
import hookapi.suites.user.updateuser.ResponseUpdateUser;
import hookapi.token.AccessToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RoleTest {
  @BeforeTest
  public void setBaseSpecification() {
    BaseSpecification.installSpecification(BaseSpecification.baseRequestSpecification("http://localhost",
        8080), BaseSpecification.baseResponseSpecification());
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

  //POST на подключение Роли, как передать Список? Хэш мапа!
  //TODO
  @Ignore
  @Test(description = "{{base_url}}/roles/set.role/", priority = 2)
  public void setRoleToUser() {
  //RequestSetRole requestSetRole = RequestSetRole.builder().userId(3).roleIds(1).build();
  }

  //POST на создание нового user
  @Test(description = " POST{{base_url}}/user/create", priority = 3)

  public void createUser() {
    AccessToken accessToken = new AccessToken();
    String token = accessToken.getToken();
    System.out.println(token);

    RequestCreateUser requestCreateUser = RequestCreateUser.builder().phone("89992222222").password("123456789").build();
    ResponseCreateUser responseCreateUser = given()
        .when()
        .header("Authorization", "Bearer" + token)
        .body(requestCreateUser)
        .post("/user/create")
        .then()
        .statusCode(200)
        .extract().response().as(ResponseCreateUser.class);
  }
  //PUT обновление user
  //TODO
  @Ignore
  @Test(description = "PUT{{base_url}}/user/update/", priority = 4)
  public void updateUser() {
    AccessToken accessToken = new AccessToken();
    String token = accessToken.getToken();

    RequestUpdateUser requestUpdateUser = RequestUpdateUser.builder().name("user").build();
    ResponseUpdateUser responseUpdateUser = given()
        .when()
        .header("Authorization", "Bearer" + token)
        .body(requestUpdateUser)
        .put("/user/update/19")
        .then()
        .statusCode(200)
        .extract().response().as(ResponseUpdateUser.class);// как работает обновление
  }
  //GET all users
  @Test(description = "GET{{base_url}}/user/get/all", priority = 5)
  public void getAllUsers(){
    AccessToken accessToken = new AccessToken();
    String token = accessToken.getToken();
    RestAssured.given()
        .when().header("Authorization", "Bearer " + token)
        .get("/user/get/all")
        .then().statusCode(200); // написать проверку
  }
  //GET user by Id
  @Test(description = "GET{{base_url}}/user/get/{id}",priority = 6)
  public void getUserById(){
    AccessToken accessToken = new AccessToken();
    String token = accessToken.getToken();
    RestAssured.given()
        .when().header("Authorization", "Bearer " + token)
        .get("/user/get/21")
        .then().statusCode(200); // написать проверку
  }
  //DELETE disable user account
 //TODO
  @Ignore
  @Test(description = "DELETE{{base_url}}/user/disable", priority = 7)
  public void disableUserAccount(){}

  //DELETE disband user account by id
  @Test(description = "DELETE{{base_url}}/user/disband",priority = 8)
  public void disbandUserAccountById(){
    AccessToken accessToken = new AccessToken();
    String token = accessToken.getToken();
    RestAssured.given()
        .when().header("Authorization", "Bearer " + token)
        .delete("/user/disband/21")
        .then().statusCode(200); // написать проверку

  }









}
