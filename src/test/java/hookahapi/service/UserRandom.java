package hookahapi.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@NoArgsConstructor
public class UserRandom {
    private String phone = String.valueOf(89000000000L + new Random().nextLong(999999999));
    private String password = "1234567890";
}
