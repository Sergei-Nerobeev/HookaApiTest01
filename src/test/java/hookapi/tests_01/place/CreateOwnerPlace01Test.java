package hookapi.tests_01.place;

import hookapi.DTO.UserDTO;
import hookapi.service.PlaceGenerator;
import hookapi.service.UserGenerator;
import hookapi.spec.BaseSpecification;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateOwnerPlace01Test {
Dotenv dotenv = Dotenv.load();
UserDTO newOwner = UserGenerator.getInstance().createOwner();
String token = newOwner.getAuthToken();
@Ignore
@BeforeTest
public void setUp() {

}
@Test
public void test01CreateOwnerPlace(){
	PlaceGenerator placeGenerator = new PlaceGenerator();
	placeGenerator.createPlace(token,dotenv);
}
@Ignore
@AfterTest
public void tearDown(){}
}
