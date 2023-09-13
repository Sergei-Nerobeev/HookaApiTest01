package hookapi.tests_01.order;

import hookapi.DTO.PlaceDTO;
import hookapi.DTO.UserDTO;
import hookapi.service.OrderGenerator;
import hookapi.service.PlaceGenerator;
import hookapi.service.UserGenerator;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class CreateMasterOrderTest {

Dotenv dotenv = Dotenv.load();
UserDTO newOwner = UserGenerator.getInstance().createOwner();
String token = newOwner.getAuthToken();
PlaceDTO newPlace = PlaceGenerator.getInstance().createPlace(token,dotenv);

@Ignore
@BeforeTest
public void setUp(){
}
@Test
public void test01createMasterOrder() {
	Dotenv dotenv2 = Dotenv.load();
	UserDTO newHMaster = UserGenerator.getInstance().createAdmin();
	String token2 = newHMaster.getAuthToken();
	long idPlace = newPlace.getPlace().getId();
	int idUser = newHMaster.getUser().getId();
	OrderGenerator.getInstance().createOrder(idPlace, idUser, token2, dotenv2);

}
@Ignore
@AfterTest
public void tearDown(){}

}
