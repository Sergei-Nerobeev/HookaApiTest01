package hookahapi.test;

import hookahapi.dto.UserDto;
import hookahapi.generator.OrderGen;
import hookahapi.generator.PlaceGen;
import hookahapi.generator.UserGen;
import hookahapi.model.responce.PlaceResModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.Test;

public class CreateOrderTest
{
Dotenv dotenv = Dotenv.load();
UserDto user = UserGen.getInstance().createUser("ADMIN");
PlaceGen place = new PlaceGen();
OrderGen order = new OrderGen();
String token = user.getAuthToken();
long userId = user.getUser().getId();
PlaceResModel prm = place.createPlace(token, dotenv);
long idPlace = prm.getId();

@Test
public void test01CreateOrder()
{
	order.createOrder(idPlace,userId,token,dotenv);
}
}
