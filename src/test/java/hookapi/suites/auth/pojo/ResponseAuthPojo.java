package hookapi.suites.auth.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ResponseAuthPojo {
	private String type;
	private String accessToken;
	private String refreshToken;

}