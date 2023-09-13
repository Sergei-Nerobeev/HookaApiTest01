package hookapi.service;

import hookapi.entity.role.pojo.RequestRolePojo;
import hookapi.entity.role.pojo.ResponseRolePojo;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;
@Data @NoArgsConstructor
public class RoleGenerator {
  RequestRolePojo requestRolePojo = RequestRolePojo.builder().roleName("ADMIN").build();
  public ResponseRolePojo createNewAdminRole(String token){
    return given()
        .when().contentType(ContentType.JSON)
        .header("Authorization", "Bearer " + token)
        .body(requestRolePojo).log().all()
        .post("/roles/create")
        .then().log().all()
        .statusCode(200)
        .extract().response().as(ResponseRolePojo.class);
  }
  RequestRolePojo requestOwnerRolePojo = RequestRolePojo.builder().roleName("OWNER").build();
  public ResponseRolePojo createNewOwnerRole(String token){
    return given()
        .when().contentType(ContentType.JSON)
        .header("Authorization", "Bearer " + token)
        .body(requestOwnerRolePojo).log().all()
        .post("/roles/create")
        .then().log().all()
        .statusCode(200)
        .extract().response().as(ResponseRolePojo.class);
  }
  RequestRolePojo requestMasterRolePojo = RequestRolePojo.builder().roleName("HOOKAH_MASTER").build();
  public ResponseRolePojo createHMasterRole(String token){
    return given()
        .when().contentType(ContentType.JSON)
        .header("Authorization", "Bearer " + token)
        .body(requestMasterRolePojo).log().all()
        .post("/roles/create")
        .then().log().all()
        .statusCode(200)
        .extract().response().as(ResponseRolePojo.class);
}
  RequestRolePojo requestUserRolePojo = RequestRolePojo.builder().roleName("USER").build();
  public ResponseRolePojo createUserRole(String token){
    return given()
        .when().contentType(ContentType.JSON)
        .header("Authorization", "Bearer " + token)
        .body(requestMasterRolePojo).log().all()
        .post("/roles/create")
        .then().log().all()
        .statusCode(200)
        .extract().response().as(ResponseRolePojo.class);
}
}
