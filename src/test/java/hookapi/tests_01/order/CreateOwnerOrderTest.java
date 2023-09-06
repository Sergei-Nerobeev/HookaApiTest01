package hookapi.tests_01.order;

import hookapi.service.PlaceGenerator;
import hookapi.spec.BaseSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateOwnerOrderTest {

@Ignore
@BeforeTest
public void setUp() {
	//Подключение к локальному серверу
	given()
		.spec(BaseSpecification.baseDefautlRequestSpecification())
		.then()
		.spec(BaseSpecification.baseResponseSpecification());
}

@Ignore
@AfterTest
public void tearDown(){}

}
