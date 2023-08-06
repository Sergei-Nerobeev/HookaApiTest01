package hookapi.service;

import hookapi.DTO.UserDTO.UserDTO;
import hookapi.entity.user.pojo.ResponseCreateUser;
import hookapi.jdbc.DbConnector;
import hookapi.spec.BaseSpecification;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import lombok.Data;
import static io.restassured.RestAssured.given;

@Data
public class UserGenerator {
    private static UserGenerator generator;
    private final RandomUser randomUser = new RandomUser();
    Dotenv dotenv = Dotenv.load();
    private UserGenerator() {
    }

    public static UserGenerator getInstance() {
        if (generator == null) {
            generator = new UserGenerator();
        }
        return generator;
    }

    public ResponseCreateUser createNewUser() {
        return given().spec(BaseSpecification.baseDefautlRequestSpecification())
            .when().body(randomUser).post(dotenv.get("USER_CREATE"))
            .then().statusCode(200).log().all().extract().response().as(ResponseCreateUser.class);
    }

    public UserDTO createUser() {
        var user = given().spec(BaseSpecification.baseDefautlRequestSpecification())
          .when().body(randomUser).post(dotenv.get("USER_CREATE"))
          .then().statusCode(200).log().all().extract().response().as(ResponseCreateUser.class);
        var token = requestAuthTokenForNewUser();
        return new UserDTO(user, token);
    }

    public UserDTO createAdmin() {
        var user = given().spec(BaseSpecification.baseDefautlRequestSpecification())
          .when().body(randomUser).post(dotenv.get("USER_CREATE"))
          .then().statusCode(200).log().all().extract().response().as(ResponseCreateUser.class);
        var token = requestAuthTokenForNewUser();
        var role = new RoleGenerator().createNewAdminRole(token);
        DbConnector.getDbConnector().updateUserRole(user.getId(), role.getId());
        return new UserDTO(user, token);
    }public UserDTO createOwner() {
        var user = given().spec(BaseSpecification.baseDefautlRequestSpecification())
          .when().body(randomUser).post(dotenv.get("USER_CREATE"))
          .then().statusCode(200).log().all().extract().response().as(ResponseCreateUser.class);
        var token = requestAuthTokenForNewUser();
        var role = new RoleGenerator().createNewOwnerRole(token);
        DbConnector.getDbConnector().updateUserRole(user.getId(), role.getId());
        return new UserDTO(user, token);
    }

    public String requestAuthTokenForNewUser() {
        return given().spec(BaseSpecification.baseDefautlRequestSpecification())
            .contentType(ContentType.JSON).body(randomUser).post(dotenv.get("AUTH"))
            .then().statusCode(200).log().all().extract().path("accessToken");
    }



}