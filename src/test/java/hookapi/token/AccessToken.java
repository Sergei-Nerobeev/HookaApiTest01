package hookapi.token;


import hookapi.spec.BaseSpecification;
import hookapi.suites.auth.pojo.RequestAuthPojo;
import hookapi.suites.auth.pojo.ResponseAuthPojo;
import io.restassured.RestAssured;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AccessToken {

  RequestAuthPojo requestAuthPojo =
  RequestAuthPojo.builder().phone("89999999999").password("123456789").build();
    String token = given()
        .body(requestAuthPojo)
        .post("/auth/login")
        .then().statusCode(200)
        .extract().jsonPath().getString("accessToken");

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}



