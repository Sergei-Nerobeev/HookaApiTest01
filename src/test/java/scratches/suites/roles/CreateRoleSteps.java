package scratches.suites.roles;


import scratches.suites.roles.pojo.RolePojo;

public class CreateRoleSteps {

 CreateRoleRequest createRoleRequest = new CreateRoleRequest();

 RolePojo rolePojo = RolePojo.builder().roleName("user").build();



 public void createRole(){

 createRoleRequest.createRoleRequest(rolePojo);


  }

}
