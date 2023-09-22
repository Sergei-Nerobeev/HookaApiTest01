package hookahapi.dto;

import hookahapi.model.responce.PlaceResModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO - Data Transfer Object
 * Object to transfer between tests
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaceDto
{

private PlaceResModel placeResModel;



}
