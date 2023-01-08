package login;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersTable {
  private final String url;
  private final String dbUserName;
  private final String dbPassword;
  private Connection con = null;
  private PreparedStatement ps = null;
  private ResultSet rs = null;

  public UsersTable() {
    url = Dotenv.load().get("MYSQL_URL");
    dbUserName = Dotenv.load().get("MYSQL_USER_NAME");
    dbPassword = Dotenv.load().get("MYSQL_PASSWORD");
  }

  public ArrayList<User> findAll() {
    ArrayList<User> allUserList = new ArrayList<>();
    String sql = """
        SELECT u.id, u.name, u.password
        FROM users as u
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
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

  // DAO が boolean を返すのは微妙かもしれない
  public boolean existTargetUser(String targetName, String targetPassword) {
    boolean existTargetUser = false;
    String sql = """
        SELECT u.id, u.name, u.password
        FROM users as u
        WHERE u.name = ?
        AND u.password = ?
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
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

  public void create(String name, String password) {
    int recordNumber = 0;
    String sql = """
        INSERT INTO users(name, password)
        VALUES(?, ?)
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
      ps = con.prepareStatement(sql);
      ps.setString(1, name);
      ps.setString(2, password);
      recordNumber = ps.executeUpdate();
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

    System.out.printf("%d行追加しました。\n", recordNumber);
  }

  public void update(String id, String name, String password) {
    int recordNumber = 0;
    String sql = """
        UPDATE users
        SET name = ?, password = ?
        WHERE id = ?
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
      ps = con.prepareStatement(sql);
      ps.setString(1, name);
      ps.setString(2, password);
      ps.setString(3, id);
      recordNumber = ps.executeUpdate();
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

    System.out.printf("%d行更新しました。\n", recordNumber);
  }

  public void delete(String id) {
    int recordNumber = 0;
    String sql = """
        DELETE FROM users
        WHERE id = ?
        """;

    try {
      con = DriverManager.getConnection(url, dbUserName, dbPassword);
      ps = con.prepareStatement(sql);
      ps.setString(1, id);
      recordNumber = ps.executeUpdate();
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

    System.out.printf("%d行削除しました。\n", recordNumber);
  }
}
