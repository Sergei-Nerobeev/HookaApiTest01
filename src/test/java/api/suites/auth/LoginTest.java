package api.suites.auth;

import api.suites.auth.pojo.AuthLoginPojo;
import api.suites.auth.pojo.AuthLoginResponse;
import api.suites.roles.CreateRoleSteps;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class LoginTest {


  @Test
  public void loginTesting(){

    AuthLoginResponse authLoginResponse = new AuthLoginResponse();
    AuthLoginPojo authLoginPojo = AuthLoginPojo.builder().phone("89999999999").password("123456789").build();

    HashMap accessToken = authLoginResponse
        .createAuthRequest(authLoginPojo)
        .statusCode(HttpStatus.SC_OK)
        .extract().body().as(HashMap.class);

      System.out.println(accessToken);
  }
  @Test
  public void roleTest01(){

    CreateRoleSteps createRoleSteps = new CreateRoleSteps();
    createRoleSteps.createRole();

  }

}
