package hookapi.entity.order.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ResponseCreateOrder{

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("orderStatus")
	private String orderStatus;

	@JsonProperty("comment")
	private Comment comment;

	@JsonProperty("order_time")
	private String orderTime;

	@JsonProperty("place_id")
	private int placeId;

}