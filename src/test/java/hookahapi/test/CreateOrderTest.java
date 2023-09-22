package hookahapi.test;

import hookahapi.dto.PlaceDto;
import hookahapi.dto.UserDto;
import hookahapi.generator.OrderGen;
import hookahapi.generator.PlaceGen;
import hookahapi.generator.UserGen;
import hookahapi.model.responce.UserResModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.Test;

public class CreateOrderTest
{
Dotenv dotenv = Dotenv.load();
UserDto user = UserGen.getInstance().createUser("ADMIN");
PlaceGen place = new PlaceGen();

OrderGen order = new OrderGen();

@Test
public void test01CreateOrder()
{

	String token = user.getAuthToken();
	UserResModel userId = user.getUser();
	place.createPlace(token, dotenv);



}
}
