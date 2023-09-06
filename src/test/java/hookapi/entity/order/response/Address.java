package hookapi.entity.order.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address{
	@JsonProperty("id")
	private String id;
	@JsonProperty("country")
	private String country;
	@JsonProperty("address")
	private String address;
	@JsonProperty("lat")
	private double lat;
	@JsonProperty("lng")
	private double lng;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonProperty("updated_at")
	private String updatedAt;
	@JsonProperty("deleted_at")
	private Object deletedAt;


}