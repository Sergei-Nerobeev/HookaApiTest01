package hookapi.entity.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import hookapi.entity.order.response.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderPOJO {
@JsonProperty("place_id")
private PlaceId placeId;
@JsonProperty("user_id")
private UserId userId;
@JsonProperty("order_time")
private String orderTime;
@JsonProperty("comment")
private Comment comment;
@JsonProperty("taken_at")
private LocalDate takenAt;
@JsonProperty("completed_at")
private LocalDate completedAt;
@JsonProperty("cancelled_at")
private LocalDate cancelledAt;
@JsonProperty("order_status")
private OrderStatus orderStatus;
}
