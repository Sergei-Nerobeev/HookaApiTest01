package hookapi.token;

import hookapi.spec.BaseSpecification;
import hookapi.suites.auth.pojo.RequestAuthPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;
@Data
@AllArgsConstructor @NoArgsConstructor
public class AccessToken {

  public static String getAccessToken () {
    BaseSpecification.installSpecification(BaseSpecification.baseRequestSpecification("http://localhost",
        8080), BaseSpecification.baseResponseSpecification());

    RequestAuthPojo requestAuthPojo =
        RequestAuthPojo.builder().phone("89999999999").password("123456789").build();

    return given()
        .body(requestAuthPojo)
        .post("/auth/login")
        .then().statusCode(200)
        .extract().jsonPath().getString("Bearer");
  }
}
