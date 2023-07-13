package hookapi.entity.user.service;
import hookapi.jdbc.DbConnector;
import hookapi.entity.role.service.RoleGenerator;
import hookapi.entity.user.pojo.ResponseCreateUser;
import hookapi.spec.BaseSpecification;

import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;
@Data @AllArgsConstructor @NoArgsConstructor
public class UserGenerator {

  RandomUser randomUser = new RandomUser();
  public ResponseCreateUser createNewUser(){
         return given()
        .spec(BaseSpecification.baseRequestSpecification("http://localhost",8080))
        .when().body(randomUser).post("user/create").then().statusCode(200)
        .log().all().extract()
        .response().as(ResponseCreateUser.class);
  }
  public ResponseCreateUser createNewAdmin(){
         var user = given()
        .spec(BaseSpecification.baseRequestSpecification("http://localhost",8080))
        .when().body(randomUser).post("user/create").then().statusCode(200)
        .log().all().extract()
        .response().as(ResponseCreateUser.class);
         var token = requestAuthTokenForNewUser();
         var role = new RoleGenerator().createNewAdminRole(token);
         DbConnector.getDbConnector().updateUserRole(user.getId(), role.getId());
         return user;
  }
  public String requestAuthTokenForNewUser() {

    return given()
        .spec(BaseSpecification.baseRequestSpecification("http://localhost", 8080))
        .contentType(ContentType.JSON)
        .body(randomUser)
        .post("/auth/login")
        .then()
        .statusCode(200).log().all()
        .extract()
        .path("accessToken");
  }


}