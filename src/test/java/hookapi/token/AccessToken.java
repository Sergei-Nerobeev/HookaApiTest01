//package hookapi.token;
//
//
//import hookapi.servises.user.pojo.RequestCreateUser;
//import hookapi.servises.user.pojo.ResponseCreateUser;

//import hookapi.servises.user.UserGenerator;
//import hookapi.spec.BaseSpecification;
//import hookapi.suites.auth.pojo.RequestAuthPojo;
//import hookapi.suites.auth.pojo.ResponseAuthPojo;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//
//public class AccessToken {
//    UserGenerator userGenerator;
//
//    public String getToken() {
//
//    return given()
//        .log().all()
//        .contentType(ContentType.JSON)
////        .body()
//        .post("/auth/login")
//        .then().statusCode(200)
//        .extract().jsonPath().getString("accessToken");
 // }
//    AccessToken accessToken = new AccessToken();
//    String token = accessToken.getToken();
//
//     given().when().header("Authorization", "Bearer " + token)
//        .body(responseCreateUser)
//        .then().statusCode(200);


//    ResponseCreateUser responseCreateUser = given()
//        .when()
//        .header("Authorization", "Bearer" + token)
//        .body(responseCreateUser)
//        .post("/user/create")
//        .then()
//        .statusCode(200)
//        .extract().response().as(ResponseCreateUser.class);

//}



