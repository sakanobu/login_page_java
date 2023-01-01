package login;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
  private static final String URL = "jdbc:mysql://localhost:3306/login_page_study";
  private static final String DB_USER_NAME = "login_page_study_user";
  private static final String DB_PASSWORD = Dotenv.load().get("MYSQL_PASSWORD");

  public static void main(String[] args) {
    UsersRepository usersRepository = new UsersRepository();
    User user = usersRepository.findUserById("1");
    System.out.println("%d, %s, %s".formatted(user.getId(), user.getName(), user.getPassword()));
  }

  public User findUserById(String targetId) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    int id = -1;
    String name = "";
    String password = "";
    String sql = """
        SELECT u.id, u.name, u.password FROM users as u
        WHERE u.id = ?
        """;

    try {
      con = DriverManager.getConnection(URL, DB_USER_NAME, DB_PASSWORD);
      ps = con.prepareStatement(sql);
      ps.setString(1, targetId);
      rs = ps.executeQuery();

      if (rs.next()) {
        id = rs.getInt("id");
        name = rs.getString("name");
        password = rs.getString(3);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (ps != null) {
          ps.close();
        }
        if (con != null) {
          con.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return new User(id, name, password);
  }
}
