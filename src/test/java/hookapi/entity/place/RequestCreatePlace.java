package hookapi.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import hookapi.entity.order.response.Address;
import lombok.Data;

@Data
public class RequestCreatePlace {
  @JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private Address address;

}


