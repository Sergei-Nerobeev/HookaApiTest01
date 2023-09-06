package hookapi.entity.order.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserId{
	@JsonProperty("id")
	private int id;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("enabled")
	private boolean enabled;
	@JsonProperty("email")
	private String email;
	@JsonProperty("name")
	private String name;
	@JsonProperty("is_enabled")
	private boolean isEnabled;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("created_at")
	private LocalDate createdAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("updated_at")
	private LocalDate updatedAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("deleted_at")
	private LocalDate deletedAt;
	@JsonProperty("rating")
	private double rating;
	//TODO role



}