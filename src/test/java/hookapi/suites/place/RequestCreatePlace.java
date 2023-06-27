package hookapi.suites.place;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCreatePlace{

	@JsonProperty("owner")
	private Object owner;

	@JsonProperty("start_time")
	private Object startTime;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("logo_url")
	private Object logoUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("end_time")
	private Object endTime;

	@JsonProperty("rating")
	private Object rating;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("id")
	private int id;

	@JsonProperty("deleted_at")
	private Object deletedAt;

	public Object getOwner(){
		return owner;
	}

	public Object getStartTime(){
		return startTime;
	}

	public Address getAddress(){
		return address;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public Object getLogoUrl(){
		return logoUrl;
	}

	public String getName(){
		return name;
	}

	public Object getEndTime(){
		return endTime;
	}

	public Object getRating(){
		return rating;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}
}