package scratches.suites.roles;

import scratches.steps.AuthLoginSteps;
import scratches.suites.roles.pojo.RolePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class CreateRoleRequest {
  RolePojo rolePojo = new RolePojo();

  AuthLoginSteps authLoginSteps = new AuthLoginSteps();
  String token = authLoginSteps.getAccessToken();

  private final String BASE_URI01 = "http://localhost:8080";
  private final String BASE_PATH01 = "/roles/create";

  private RequestSpecification requestSpecification01  = given()
      .baseUri(BASE_URI01)
      .basePath(BASE_PATH01)
      .contentType(ContentType.JSON)
      .log().all();

  public ValidatableResponse createRoleRequest(RolePojo rolePojo) { // проверка ответа на пост запрос

    String BASE_URL = "http://localhost:8080/roles/create";
    return given(requestSpecification01)
        .log()
        .all()
        .header("Authorization","Bearer" + token)
        .body(rolePojo)
        .when().post(BASE_URL)
        .then().log().all();


  }
  public void show(){
    System.out.println("HERE" + " " + token);
  }


  }
