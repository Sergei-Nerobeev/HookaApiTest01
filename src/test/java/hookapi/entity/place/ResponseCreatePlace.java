package hookapi.entity.place;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hookapi.entity.order.response.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseCreatePlace {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("phone")
	private Object phone;
	@JsonProperty("address")
	private Address address;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JsonProperty("created_at")
	private LocalDate createdAt;
	@JsonProperty("updated_at")
	private LocalDate updatedAt;
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
	private Object rating;





}