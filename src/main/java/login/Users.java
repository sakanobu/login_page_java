package login;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
  private static final String url = "jdbc:mysql://localhost:3306/login_page_study";
  private static final String userName = "login_page_study_user";
  private static final String dbPassword = Dotenv.load().get("MYSQL_PASS");

  public static void main(String[] args) {
    User user = findUser("1");
    System.out.println("%d, %s, %s".formatted(user.getId(), user.getName(), user.getPassword()));
  }

  public static User findUser(String targetID) {
    int id = -1;
    String name = "";
    String password = "";
    String sql = """
        SELECT * FROM users
        WHERE id = ?
        """;

    try (Connection con = DriverManager.getConnection(url, userName, dbPassword)) {
      try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, targetID);
        try (ResultSet rs = ps.executeQuery()) {
          while (rs.next()) {
            id = rs.getInt("id");
            name = rs.getString("name");
            password = rs.getString(3);
          }
        }
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return new User(id, name, password);
  }

}
