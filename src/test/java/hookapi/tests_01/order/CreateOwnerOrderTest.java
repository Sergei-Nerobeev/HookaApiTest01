package hookapi.tests_01.order;

import hookapi.DTO.OrderDTO;
import hookapi.DTO.PlaceDTO;
import hookapi.DTO.UserDTO;
import hookapi.service.OrderGenerator;
import hookapi.service.PlaceGenerator;
import hookapi.service.UserGenerator;
import hookapi.spec.BaseSpecification;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateOwnerOrderTest {
Dotenv dotenv = Dotenv.load();
UserDTO newOwner = UserGenerator.getInstance().createOwner();
String token = newOwner.getAuthToken();
PlaceDTO newPlace = PlaceGenerator.getInstance().createPlace(token,dotenv);


@Ignore
@BeforeTest
public void setUp(){
}
@Test
public void test01createOwnerOrder(){
	int idUser = newOwner.getUser().getId();

	long idPlace = newPlace.getPlace().getId();

	OrderDTO newOrder = OrderGenerator.getInstance().createOrder(idPlace,idUser,token,dotenv);


}

@Ignore
@AfterTest
public void tearDown(){}

}
