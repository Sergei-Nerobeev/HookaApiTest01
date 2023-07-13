package hookapi.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCreatePlace{

	@JsonProperty("address")
	private AddressFromResponse address;

	@JsonProperty("name")
	private String name;

	public AddressFromResponse getAddress(){
		return address;
	}

	public String getName(){
		return name;
	}
}