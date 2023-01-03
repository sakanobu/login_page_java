package login;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersTable {
  private static final String URL = "jdbc:mysql://localhost:3306/login_page_study";
  private static final String DB_USER_NAME = "login_page_study_user";
  private static final String DB_PASSWORD = Dotenv.load().get("MYSQL_PASSWORD");
  private Connection con = null;
  private PreparedStatement ps = null;
  private ResultSet rs = null;


  public static void main(String[] args) {
    UsersTable usersTable = new UsersTable();

    ArrayList<User> allUserList = usersTable.findAll();

    System.out.println("↓ findAll()");
    for (User user : allUserList
    ) {
      System.out.printf("%d, %s, %s\n", user.getId(), user.getName(), user.getPassword());
    }

    User user = usersTable.findById("1");
    System.out.println("\n↓ findUserById()");
    System.out.println("%d, %s, %s".formatted(user.getId(), user.getName(), user.getPassword()));
  }

  public ArrayList<User> findAll() {
    ArrayList<User> allUserList = new ArrayList<>();

    String sql = """
        SELECT u.id, u.name, u.password
        FROM users as u
        """;

    try {
      con = DriverManager.getConnection(URL, DB_USER_NAME, DB_PASSWORD);
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String password = rs.getString("password");

        allUserList.add(new User(id, name, password));
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

    return allUserList;
  }

  public User findById(String targetId) {
    int id = -1;
    String name = "";
    String password = "";
    String sql = """
        SELECT u.id, u.name, u.password
        FROM users as u
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

  public boolean existTargetUser(String targetName, String targetPassword) {
    boolean existTargetUser = false;
    String sql = """
        SELECT u.id, u.name, u.password
        FROM users as u
        WHERE u.name = ?
        AND u.password = ?
        """;

    try {
      con = DriverManager.getConnection(URL, DB_USER_NAME, DB_PASSWORD);
      ps = con.prepareStatement(sql);
      ps.setString(1, targetName);
      ps.setString(2, targetPassword);
      rs = ps.executeQuery();

      existTargetUser = rs.next();
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
    return existTargetUser;
  }
}
