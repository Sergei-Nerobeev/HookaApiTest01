package hookapi.suites.place;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCreatePlace{

	@JsonProperty("address")
	private Address address;

	@JsonProperty("name")
	private String name;

	public Address getAddress(){
		return address;
	}

	public String getName(){
		return name;
	}
}