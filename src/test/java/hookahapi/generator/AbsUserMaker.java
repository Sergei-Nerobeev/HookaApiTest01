package hookahapi.generator;

import hookahapi.service.UserRandom;
import io.github.cdimascio.dotenv.Dotenv;

public abstract class AbsUserMaker {
private static UserGen generator;
private UserRandom userRandom = new UserRandom(); // тута он!
Dotenv dotenv = Dotenv.load();

}
