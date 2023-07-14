package hookapi.DTO.UserDTO;


import hookapi.entity.user.pojo.ResponseCreateUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO - Data Transfer Object
 * Object to transfer between tests
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO {
    private ResponseCreateUser user;
    private String authToken;
}
