package hookapi.entity.role.setrole;

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

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("role_ids")
	private List<Long> roleIds;



}