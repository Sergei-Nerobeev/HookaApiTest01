package hookahapi.dto;

import hookahapi.model.responce.UserResModel;
import lombok.*;
/**
 * DTO - Data Transfer Object
 * Object to transfer between tests
 */
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class UserDto {

    private UserResModel user;
    private String authToken;
}
