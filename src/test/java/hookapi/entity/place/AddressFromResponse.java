package hookapi.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressFromResponse {

	@JsonProperty("country")
	private Object country;

	@JsonProperty("address")
	private Object address;

	@JsonProperty("lng")
	private double lng;

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("id")
	private int id;

	@JsonProperty("deleted_at")
	private Object deletedAt;

	@JsonProperty("lat")
	private double lat;

	public Object getCountry(){
		return country;
	}

	public Object getAddress(){
		return address;
	}

	public double getLng(){
		return lng;
	}

	public String getUpdatedAt(){
		return updatedAt;
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

	public double getLat(){
		return lat;
	}
}