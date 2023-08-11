package hookapi.entity.order.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hookapi.utils.DataDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
;
import java.time.LocalTime;

@Data
@AllArgsConstructor @NoArgsConstructor

public class ResponseCreateOrder {

	@JsonProperty("id")
	private long id;
	@JsonProperty("takenAt")
	private Object takenAt;
	@JsonProperty("completedAt")
	private Object completedAt;
	@JsonProperty("cancelledAt")
	private Object cancelledAt;
	@JsonProperty("orderStatus")
	private String orderStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = DataDeserializer.class)
	@JsonProperty("created_at")
	private Date createdAt;
	@JsonProperty("updated_at")
	private LocalDate updatedAt;
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