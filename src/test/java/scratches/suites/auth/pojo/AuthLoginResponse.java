package scratches.suites.auth.pojo;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AuthLoginResponse { //проверяет отправленный пост запрос

  private final String BASE_URI = "http://localhost:8080";
  private final String BASE_PATH = "/auth/login";
  private final String BASE_URL = "http://localhost:8080/auth/login";
  private RequestSpecification requestSpecification  = given() // специф запроса
      .baseUri(BASE_URI)
      .basePath(BASE_PATH).contentType(ContentType.JSON);

  public ValidatableResponse createAuthRequest(AuthLoginPojo authLoginPojo){ // проверка ответа на пост запрос

    return given(requestSpecification)
        .log()
        .all()
        .body(authLoginPojo)
        .when().post(BASE_URL)
        .then().log().all();

  }

}
