package scratches.suites.roles.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RolePojo{
	private String roleName;

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public String getRoleName(){
		return roleName;
	}

	@Override
 	public String toString(){
		return 
			"RolePojo{" + 
			"role_name = '" + roleName + '\'' + 
			"}";
		}
}
