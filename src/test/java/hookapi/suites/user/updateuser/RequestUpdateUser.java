package hookapi.suites.user.updateuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestUpdateUser{

	@JsonProperty("name")
	private String name;

	public String getName(){
		return name;
	}
}