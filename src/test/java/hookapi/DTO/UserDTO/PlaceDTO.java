package hookapi.DTO.UserDTO;


import hookapi.entity.place.ResponseCreatePlace;
import lombok.*;

/**
 * DTO - Data Transfer Object
 * Object to transfer between tests
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaceDTO {

private ResponseCreatePlace place;

}
