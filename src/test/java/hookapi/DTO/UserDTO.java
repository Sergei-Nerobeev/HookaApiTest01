package hookapi.DTO;


import hookapi.entity.user.pojo.ResponseCreateUser;
import hookapi.service.UserGenerator;
import lombok.*;

/**
 * DTO - Data Transfer Object
 * Object to transfer between tests
 */
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class UserDTO {

    private ResponseCreateUser user;
    private String authToken;
}
