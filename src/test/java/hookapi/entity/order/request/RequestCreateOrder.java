package hookapi.entity.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import hookapi.entity.order.response.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCreateOrder {

@JsonProperty("place_id")
private long placeId;

@JsonProperty("user_id")
private int userId;

@JsonProperty("orderStatus")
private String orderStatus;

@JsonProperty("comment")
private Comment comment;

@JsonProperty("order_time")
private String orderTime;


}