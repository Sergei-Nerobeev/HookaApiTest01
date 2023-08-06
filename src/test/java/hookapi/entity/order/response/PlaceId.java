package hookapi.entity.order.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlaceId{
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonProperty("updated_at")
	private String updatedAt;
	@JsonProperty("deleted_at")
	private Object deletedAt;
	@JsonProperty("start_time")
	private Object startTime;
	@JsonProperty("end_time")
	private Object endTime;
	@JsonProperty("logo_url")
	private Object logoUrl;
	@JsonProperty("owner")
	private Object owner;
	@JsonProperty("rating")
	private double rating;

}