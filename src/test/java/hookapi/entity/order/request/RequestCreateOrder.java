package hookapi.entity.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import hookapi.entity.order.response.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class RequestCreateOrder {

	@JsonProperty("user_id")
	private UserId userId;

	@JsonProperty("orderStatus")
	private String orderStatus;

	@JsonProperty("comment")
	private Comment comment;

	@JsonProperty("order_time")
	private String orderTime;

	@JsonProperty("place_id")
	private PlaceId placeId;
}