package hookapi.tests.roles;

import hookapi.entity.role.pojo.ResponseRolePojo;
import hookapi.entity.user.pojo.ResponseCreateUser;
import hookapi.jdbc.DbConnector;
import hookapi.service.RoleGenerator;
import hookapi.service.UserGenerator;
import hookapi.spec.BaseSpecification;
import hookapi.token.TokenHolder;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.greaterThan;

public class RoleTest {
  private ArrayList<?> roleId;
  @BeforeTest
  public void setUp() {
    RestAssured
        .given()
        .spec(BaseSpecification.baseDefautlRequestSpecification())
        .then()
        .spec(BaseSpecification.baseResponseSpecification());
  }
  @Test(description = "POST{{base_url}}/roles/create", priority = 1)
  public void createRole() {

    UserGenerator userGenerator = UserGenerator.getInstance();
    TokenHolder tokenHolder = new TokenHolder();
    RoleGenerator roleGenerator = new RoleGenerator();
    ResponseCreateUser responseCreateUser = userGenerator.createNewUser();
    DbConnector dbConnector = DbConnector.getDbConnector();


    int idNewUser = responseCreateUser.getId();
    String token = userGenerator.requestAuthTokenForNewUser();
    tokenHolder.setToken(token);

    ResponseRolePojo responseRolePojo = roleGenerator.createNewOwnerRole(token);

    int idNewOwnerRole = responseRolePojo.getId();
    dbConnector.updateUserRole(idNewUser, idNewOwnerRole);
  }
  @Test(description ="GET{{base_url}}roles/get/all")
  public void test01getAllRolesTest(){
    roleId = RestAssured
        .get("/place/get/all")
        .then()
        .log().all()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .body("$.size()", greaterThan(0))
        .extract()
        .body().path("$.id");
  }
}
