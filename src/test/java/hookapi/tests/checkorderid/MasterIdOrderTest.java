package hookapi.tests.checkorderid;

import hookapi.DTO.PlaceDTO;
import hookapi.DTO.UserDTO;
import hookapi.entity.order.response.Address;
import hookapi.entity.order.response.ResponseCreateOrder;
import hookapi.entity.place.RequestCreatePlace;
import hookapi.entity.place.ResponseCreatePlace;
import hookapi.entity.role.pojo.ResponseRolePojo;
import hookapi.entity.user.pojo.ResponseCreateUser;
import hookapi.jdbc.DbConnector;
import hookapi.service.PlaceGenerator;
import hookapi.service.RoleGenerator;
import hookapi.service.UserGenerator;
import hookapi.spec.BaseSpecification;
import hookapi.token.TokenHolder;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
public class MasterIdOrderTest {


@BeforeTest
public void setUp() {
	//Подключение к локальному серверу
	given()
		.spec(BaseSpecification.baseDefautlRequestSpecification())
		.then()
		.spec(BaseSpecification.baseResponseSpecification());

PlaceGenerator placeGenerator = new PlaceGenerator();
placeGenerator.createPlace();




}


}
