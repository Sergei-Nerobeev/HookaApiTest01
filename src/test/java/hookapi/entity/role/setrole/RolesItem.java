package hookapi.entity.role.setrole;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RolesItem{

	@JsonProperty("role_name")
	private String roleName;

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("id")
	private int id;

	@JsonProperty("deleted_at")
	private Object deletedAt;

	public String getRoleName(){
		return roleName;
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
}