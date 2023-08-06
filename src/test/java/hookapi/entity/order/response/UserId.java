package hookapi.entity.order.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalTime;
@Data
public class UserId{
	@JsonProperty("id")
	private int id;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("enabled")
	private boolean enabled;
	@JsonProperty("email")
	private Object email;
	@JsonProperty("name")
	private Object name;
	@JsonProperty("is_enabled")
	private boolean isEnabled;
	@JsonProperty("created_at")
	private LocalTime createdAt;
	@JsonProperty("updated_at")
	private LocalTime updatedAt;
	@JsonProperty("deleted_at")
	private Object deletedAt;
	@JsonProperty("rating")
	private double rating;



}