package api.tests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class RestTest {

  private final String BASE_URI = "http://localhost:8080";
  private final String BASE_PATH = "/user/get/all";
  private String OAUTH2 = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4OTk5OTk5OTk5OSIsImV4cCI6MTY5MDAzNjIzMywicm9sZXMiOlt7ImlkIjoyLCJjcmVhdGVkX2F0IjpbMjAyMyw2LDE2XSwidXBkYXRlZF9hdCI6WzIwMjMsNiwxNl0sImRlbGV0ZWRfYXQiOm51bGwsInJvbGVfbmFtZSI6IkFETUlOIn1dLCJ1c2VySWQiOjJ9.tHlT0hSlAg3XyLCFO6GFwOG5LGBQIIlMxsbHL_J6yHJfSxI3LaeeffRrgnXEVIfvEvXWrfs0Z3KqpC-eQ9MBkg";

  @Test
  public void getUsers(){
    given()
        .baseUri(BASE_URI)
        .basePath(BASE_PATH)
        .auth().oauth2(OAUTH2)
        .when().get()
        .then().statusCode(200)
        .contentType(ContentType.JSON)
        .log().all();

  }
  @Test
  public void createRole(){

    given()
        .baseUri(BASE_URI)
        .basePath("/roles/create")
        .contentType(ContentType.JSON)
        .log().all()
        .auth().oauth2(OAUTH2)
        .when().post("")
        .then().statusCode(HttpStatus.SC_OK).log().all();


  }
}
