package hookapi.token;

public class TokenHolder {
  private String token;

  public TokenHolder() {
    this.token = null; // Изначально токен неизвестен
  }

  public void setToken(String token) {
    this.token = token;
    System.out.println(token);
  }

  public String getToken() {
    return token;
  }

}
