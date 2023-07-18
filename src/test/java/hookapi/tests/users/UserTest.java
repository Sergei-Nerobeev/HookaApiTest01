package hookapi.tests.users;

import hookapi.entity.role.pojo.ResponseRolePojo;
import hookapi.entity.user.pojo.ResponseCreateUser;
import hookapi.jdbc.DbConnector;
import hookapi.service.RoleGenerator;
import hookapi.service.UserGenerator;
import hookapi.spec.BaseSpecification;
import hookapi.token.TokenHolder;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static org.hamcrest.Matchers.greaterThan;

public class UserTest {

  UserGenerator userGenerator = UserGenerator.getInstance();
  TokenHolder tokenHolder = new TokenHolder();
  RoleGenerator roleGenerator = new RoleGenerator();
  ResponseCreateUser responseCreateUser = userGenerator.createNewUser();
  DbConnector dbConnector = DbConnector.getDbConnector();
  private ArrayList<?>userId;
  Dotenv dotenv = Dotenv.load();
  @BeforeTest
  public void setUp() {
    RestAssured
        .given()
        .spec(BaseSpecification.baseDefautlRequestSpecification())
        .then()
        .spec(BaseSpecification.baseResponseSpecification());
  }
//  @Ignore
  @Test(description = "POST/user/create") //,priority = 1
  public void test01createUser(){

    int idNewUser = responseCreateUser.getId();
    String token = userGenerator.requestAuthTokenForNewUser();
    tokenHolder.setToken(token);

    ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);

    int idNewAdminRole = responseRolePojo.getId();
    dbConnector.updateUserRole(idNewUser,idNewAdminRole);

//  getAllUsers.allUsersGetter(token);

//  userGenerator.requestAuthToken();
//  System.out.println(token);
  }

  @Test(description ="GET/user/get/all")
  public void test02getAllUsers(){
     userId = RestAssured
        .get("/user/get/all")
        .then()
        .log().all()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .body("$.size()", greaterThan(0))
        .extract()
        .body().path("$.id");
  }
//  @Ignore //TODO
  @Test(description = "GET/user/get/{id}")
  public void test03getUserById(){
    RestAssured
        .get("/user/get/{id}", dotenv.get("USER_ID"))
        .then()
        .log().all()
        .assertThat()
        .statusCode(HttpStatus.SC_OK);
  }

}
