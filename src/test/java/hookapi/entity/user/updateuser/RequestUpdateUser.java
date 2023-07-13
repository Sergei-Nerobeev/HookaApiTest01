package hookapi.entity.user.updateuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class RequestUpdateUser{

	@JsonProperty("name")
	private String name;

	public String getName(){
		return name;
	}
}