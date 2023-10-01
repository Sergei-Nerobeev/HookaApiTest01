package hookahapi.model.responce.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hookahapi.model.responce.AddressResModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class PlaceId {

@JsonProperty(value = "id")
private Long id;

@JsonProperty(value = "name")
private String name;

@JsonProperty(value = "phone")
private String phone;

@JsonProperty(value = "address")
private AddressResModel address;


}
