package api.suites.roles;


import api.suites.roles.pojo.RolePojo;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;

public class CreateRoleSteps {

 CreateRoleRequest createRoleRequest = new CreateRoleRequest();

 RolePojo rolePojo = RolePojo.builder().roleName("user").build();



 public void createRole(){

 createRoleRequest.createRoleRequest(rolePojo);


  }

}
