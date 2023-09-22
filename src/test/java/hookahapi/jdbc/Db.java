package hookahapi.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Db {
  private static Db dbConnector;
  public static Db getDbConnector(){
    if(dbConnector == null){
      dbConnector = new Db();
    }
    return dbConnector;
  }
  private Db(){}

  public void updateUserRoleSet(long idNewUser, long idNewRole) {
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String username = "admin";
    String password = "123456";
    String sql = "INSERT INTO public.users_roles_set (user_entity_id, roles_set_id) VALUES (?, ?)";

    try (Connection connection = DriverManager.getConnection(url,username,password);
      PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setLong(1, idNewUser);
      statement.setLong(2, idNewRole);

      int rowsUpdated = statement.executeUpdate();
      System.out.println("Rows updated: " + rowsUpdated);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
