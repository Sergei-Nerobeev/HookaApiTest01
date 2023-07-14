package hookapi.service;

import hookapi.spec.BaseSpecification;
import io.restassured.http.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@Data
@NoArgsConstructor
public class GetAllUsers {
    public void allUsersGetter(String token) {
        given()
                .spec(BaseSpecification.baseRequestSpecification("http://localhost", 8080))
                .when()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .get("/user/get/all")
                .then().statusCode(200).log().all();

    }

}
