package hookahapi.test;

import hookahapi.dto.UserDto;
import hookahapi.generator.UserGen;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.Test;

public class CreateUserTest {
Dotenv dotenv = Dotenv.load();
UserDto user = UserGen.getInstance().createUser("USER"); // <- can write here another type of user
@Test
public void test01CreateUser(){}



}
