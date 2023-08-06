package hookapi.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
@Data
public class RequestCreatePlace {
  @JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private Map<String, Object> address;

}


