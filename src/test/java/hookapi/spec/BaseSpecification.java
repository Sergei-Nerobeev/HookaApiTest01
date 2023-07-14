package hookapi.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.basePath;

public class BaseSpecification {

  public static RequestSpecification baseRequestSpecification(String baseUri, int port){
    return new RequestSpecBuilder()
        .setBaseUri(baseUri)
        .setPort(port)
        .setBasePath(basePath)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();
  }

  public static RequestSpecification baseDefautlRequestSpecification(){
      //TODO: move url & port to env
    return new RequestSpecBuilder()
        .setBaseUri("http://localhost")
        .setPort(8080)
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
