package scratches.suites.roles.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleResponsePojo {

	private int id;
	private String updatedAt;
	private String createdAt;
	private Object deletedAt;
	private String roleName;
}