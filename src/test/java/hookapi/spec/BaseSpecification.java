package hookapi.spec;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.basePath;

public class BaseSpecification {

  public static RequestSpecification baseDefautlRequestSpecification(){
    Dotenv dotenv = Dotenv.load();
    return new RequestSpecBuilder()
        .setBaseUri(dotenv.get("LOCAL_URL"))
        .setBasePath(basePath)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();
  }
  public static ResponseSpecification baseResponseSpecification(){
    return new ResponseSpecBuilder().log(LogDetail.ALL).build();
  }
  public static void installSpecification(RequestSpecification baseRequest,
                                          ResponseSpecification baseResponse){
    RestAssured.requestSpecification = baseRequest;
    RestAssured.responseSpecification = baseResponse;
  }


}
