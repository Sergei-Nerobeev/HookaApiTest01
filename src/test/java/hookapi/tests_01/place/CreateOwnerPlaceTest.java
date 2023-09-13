package hookapi.tests_01.place;

import hookapi.DTO.PlaceDTO;
import hookapi.DTO.UserDTO;
import hookapi.service.PlaceGenerator;
import hookapi.service.UserGenerator;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class CreateOwnerPlaceTest {
Dotenv dotenv = Dotenv.load();
UserDTO newOwner = UserGenerator.getInstance().createOwner();
String token = newOwner.getAuthToken();

@Ignore
@BeforeTest
public void setUp() {

}
@Test
public void test01CreateOwnerPlace(){

PlaceDTO newPlace = PlaceGenerator.getInstance().createPlace(token,dotenv);
}
@Ignore
@AfterTest
public void tearDown(){

}
}
