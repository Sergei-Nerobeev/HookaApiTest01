package hookapi.tests;

import hookapi.jdbc.DbConnector;
import hookapi.token.TokenHolder;
import hookapi.entity.role.service.RoleGenerator;
import hookapi.entity.role.pojo.ResponseRolePojo;
import hookapi.entity.user.service.UserGenerator;
import hookapi.entity.user.pojo.ResponseCreateUser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
public class NewTest {
  @BeforeTest
  public void setUp() {
      }
  @Test(description = "POST{{base_url}}/user/create",priority = 1)
  public void createUser(){
    UserGenerator userGenerator = new UserGenerator();
    TokenHolder tokenHolder = new TokenHolder();
    RoleGenerator roleGenerator = new RoleGenerator();
    ResponseCreateUser responseCreateUser = userGenerator.createNewUser();
    DbConnector dbConnector = DbConnector.getDbConnector();


    int idNewUser = responseCreateUser.getId();
    String token = userGenerator.requestAuthTokenForNewUser();
    tokenHolder.setToken(token);

    ResponseRolePojo responseRolePojo = roleGenerator.createNewAdminRole(token);

    int idNewAdminRole = responseRolePojo.getId();
    dbConnector.updateUserRole(idNewUser,idNewAdminRole);


//  getAllUsers.allUsersGetter(token);

//  userGenerator.requestAuthToken();
//  System.out.println(token);
  }
  @Ignore
  @Test(description = "POST {{base_url}}/roles/create",priority = 2)
  public void createRole(){}

  @Ignore
  @Test(description ="GET{{base_url}}/user/get/all", priority = 3)
  public void getAllUsers(){
  }
  @Ignore
  @Test(description = "DELETE{{base_url}}/user/disband/{id}",priority = 2)
  public void disbandUserById(){}
}
