package hookapi.suites.user.updateuser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseUpdateUser{

	@JsonProperty("is_enabled")
	private boolean isEnabled;

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

	@JsonProperty("enabled")
	private boolean enabled;

	public boolean isIsEnabled(){
		return isEnabled;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getPhone(){
		return phone;
	}

	public Object getName(){
		return name;
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

	public Object getEmail(){
		return email;
	}

	public boolean isEnabled(){
		return enabled;
	}
}