package hookapi.entity.role.setrole;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSetRole{

	@JsonProperty("is_enabled")
	private boolean isEnabled;

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("roles")
	private List<RolesItem> roles;

	@JsonProperty("name")
	private Object name;

	@JsonProperty("rating")
	private double rating;

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

	public List<RolesItem> getRoles(){
		return roles;
	}

	public Object getName(){
		return name;
	}

	public double getRating(){
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