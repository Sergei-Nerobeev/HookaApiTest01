package scratches.steps;


import org.junit.jupiter.api.Test;

public class StepsTest {


  @Test
  public void test01(){
    AuthLoginSteps authLoginSteps = new AuthLoginSteps();
    authLoginSteps.createAuthLogin();
  }

}
