package hookapi.suites.user.createuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class RequestCreateUser{

	@JsonProperty("password")
	private String password;

	@JsonProperty("phone")
	private String phone;
}