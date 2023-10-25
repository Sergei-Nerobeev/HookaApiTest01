package hookahapi.fant;

import hookahapi.generator.UserGen;
import hookahapi.service.UserRandom;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;

@Data
public abstract class AbsUserMaker {

private static UserGen generator;
private UserRandom userRandom;
Dotenv dotenv = Dotenv.load();

}
