//package hookapi.tests;
//
//import hookapi.servises.user.pojo.RequestCreateUser;
//import hookapi.servises.user.pojo.ResponseCreateUser;
//import hookapi.servises.UserService;
//import hookapi.spec.BaseSpecification;
//import hookapi.suites.user.updateuser.RequestUpdateUser;
//import hookapi.suites.user.updateuser.ResponseUpdateUser;
//
//import io.restassured.http.ContentType;
//import org.apache.http.HttpStatus;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//public class UserTest {
//  private ResponseCreateUser responseCreateUser;
//  private UserService userService;
//  @BeforeTest
//  public void setUp(){
//
//  UserService userService = new UserService();
//  userService.prepareClient();
//  }
//  //POST на создание нового user
//
//  @Test(description = "POST{{base_url}}/user/create", priority = 1)
//  public void createUser() {
//  }
//  //GET all users
//  @Test(description = "GET{{base_url}}/user/get/all", priority = 2)
//  public void getAllUsers () {
//  given()
//      .baseUri("http://localhost:8080")
//      .basePath("/user/get/all")
//      .contentType(ContentType.JSON)
//      .when()
//      .log().body()
//      .get()
//      .then()
//      .statusCode(HttpStatus.SC_OK);
//
//
//
//    }
//    //PUT обновление user
//    @Ignore
//    @Test(description = "PUT{{base_url}}/user/update/", priority = 5)
//    public void updateUser () {
//
//      RequestUpdateUser requestUpdateUser = RequestUpdateUser.builder().name("Tom").build();
//      ResponseUpdateUser responseUpdateUser = given()
//          .when()
//          .body(requestUpdateUser)
//          .put("/user/update/67")
//          .then()
//          .statusCode(200)
//          .extract().response().as(ResponseUpdateUser.class);// как работает обновление
//    }

//  //GET user by id
//  @Test(description = "GET{{base_url}}/user/get/{id}",priority = 6)
//  public void getUserById(){
//    AccessToken accessToken = new AccessToken();
//    String token = accessToken.getToken();
//    RestAssured.given()
//        .when().header("Authorization", "Bearer " + token)
//        .get("/user/get/21")
//        .then().statusCode(200); // написать проверку
//  }
//  //DELETE disable user account
//  //TODO
//  @Ignore
//  @Test(description = "DELETE{{base_url}}/user/disable", priority = 7)
//  public void disableUserAccount(){}
//
//  //DELETE disband user by id
//  @Test(description = "DELETE{{base_url}}/user/disband",priority = 8)
//  public void disbandUserAccountById(){
//    AccessToken accessToken = new AccessToken();
//    String token = accessToken.getToken();
//    RestAssured.given()
//        .when().header("Authorization", "Bearer " + token)
//        .delete("/user/disband/21")
//        .then().statusCode(200); // написать проверку
//
//  }
 // }
