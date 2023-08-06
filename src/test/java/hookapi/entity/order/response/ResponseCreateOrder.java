package hookapi.entity.order.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@AllArgsConstructor @NoArgsConstructor
public class ResponseCreateOrder {
	@JsonProperty("id")
	private int id;
	@JsonProperty("takenAt")
	private Object takenAt;
	@JsonProperty("completedAt")
	private Object completedAt;
	@JsonProperty("cancelledAt")
	private Object cancelledAt;
	@JsonProperty("orderStatus")
	private String orderStatus;
	@JsonProperty("created_at")
	private LocalTime createdAt;
	@JsonProperty("updated_at")
	private LocalTime updatedAt;
	@JsonProperty("deleted_at")
	private Object deletedAt;
	@JsonProperty("place_id")
	private PlaceId placeId;
	@JsonProperty("user_id")
	private UserId userId;
	@JsonProperty("order_time")
	private LocalTime orderTime;
	@JsonProperty("comment")
	private Comment comment;










}