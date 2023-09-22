package hookahapi.test;

import hookahapi.dto.UserDto;
import hookahapi.generator.PlaceGen;
import hookahapi.generator.UserGen;
import hookahapi.model.responce.UserResModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.Test;

public class CreatePlaceTest
{
Dotenv dotenv = Dotenv.load();
UserDto user = UserGen.getInstance().createUser("ADMIN");
PlaceGen placeGen = new PlaceGen();
@Test
public void test01CreatePlaceByUser(){

	String token = user.getAuthToken();
  UserResModel userId = user.getUser();
	placeGen.createPlace(token,dotenv);



}


}
