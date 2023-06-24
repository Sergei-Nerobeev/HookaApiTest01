package hookapi.suites.roles.setrole;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class RequestSetRole{

	@JsonProperty("userId")
	private int userId;

	@JsonProperty("role_ids")
	private List<Integer> roleIds;


}