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

    System.out.println("\n↓ findAll()");
    for (User user : usersTable.findAll()
    ) {
      System.out.printf("%d, %s, %s\n", user.getId(), user.getName(), user.getPassword());
    }

    User user1 = usersTable.findById("1");
    System.out.println("\n↓ findUserById()");
    System.out.println("%d, %s, %s".formatted(user1.getId(), user1.getName(), user1.getPassword()));

    // System.out.println("\n↓ create()");
    // usersTable.create("a", "b");

    // System.out.println("\n\n↓ findAll()");
    // for (User user : usersTable.findAll()
    // ) {
    //   System.out.printf("%d, %s, %s\n", user.getId(), user.getName(), user.getPassword());
    // }

    // System.out.println("\n↓ update()");
    // usersTable.update("11", "aa", "bb");

    // System.out.println("\n\n↓ findAll()");
    // for (User user : usersTable.findAll()
    // ) {
    //   System.out.printf("%d, %s, %s\n", user.getId(), user.getName(), user.getPassword());
    // }

    // System.out.println("\n↓ delete()");
    // usersTable.delete("11");

    // System.out.println("\n\n↓ findAll()");
    // for (User user : usersTable.findAll()
    // ) {
    //   System.out.printf("%d, %s, %s\n", user.getId(), user.getName(), user.getPassword());
    // }
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

  public void create(String name, String password) {
    int recordNumber = 0;
    String sql = """
        INSERT INTO users(name, password)
        VALUES(?, ?)
        """;

    try {
      con = DriverManager.getConnection(URL, DB_USER_NAME, DB_PASSWORD);
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
    System.out.printf("%d行追加しました。", recordNumber);
  }

  public void update(String id, String name, String password) {
    int recordNumber = 0;
    String sql = """
        UPDATE users
        SET name = ?, password = ?
        WHERE id = ?
        """;

    try {
      con = DriverManager.getConnection(URL, DB_USER_NAME, DB_PASSWORD);
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
    System.out.printf("%d行更新しました。", recordNumber);
  }

  public void delete(String id) {
    int recordNumber = 0;
    String sql = """
        DELETE FROM users
        WHERE id = ?
        """;

    try {
      con = DriverManager.getConnection(URL, DB_USER_NAME, DB_PASSWORD);
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
    System.out.printf("%d行削除しました。", recordNumber);
  }
}
