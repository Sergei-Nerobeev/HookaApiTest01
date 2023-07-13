package hookapi.entity.user.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResponseCreateUser{

	@JsonProperty("enabled")
	private boolean enabled;

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("name")
	private Object name;

	@JsonProperty("rating")
	private Object rating;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("id")
	private int id;

	@JsonProperty("deleted_at")
	private Object deletedAt;

	@JsonProperty("email")
	private Object email;

	@JsonProperty("is_enabled")
	private boolean isEnabled;


}