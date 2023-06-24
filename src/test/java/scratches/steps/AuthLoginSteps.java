package scratches.steps;

import scratches.suites.auth.pojo.AuthLoginPojo;
import scratches.suites.auth.pojo.AuthLoginResponse;
import org.apache.http.HttpStatus;

public class AuthLoginSteps {
  AuthLoginResponse authLoginResponse = new AuthLoginResponse();
  AuthLoginPojo authLoginPojo = AuthLoginPojo.builder().phone("89999999999").password("123456789").build();
  String accessToken = authLoginResponse.createAuthRequest(authLoginPojo).statusCode(HttpStatus.SC_OK).extract()
      .response().getBody().jsonPath().getString("accessToken");

  public String getAccessToken() {
    return accessToken;
  }

  public void createAuthLogin(){
    System.out.println(accessToken);

  }

  }





