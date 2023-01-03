package login;

import java.awt.FlowLayout;
import javax.swing.JFrame;

public class LoginPageFrame extends JFrame {
  @SuppressWarnings("checkstyle:VariableDeclarationUsageDistance")
  LoginPageFrame() {
    setTitle("ログイン画面");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    int width = 800;
    int height = 1000;
    setSize(width, height);
    setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

    UsersListPanel usersListPanel = new UsersListPanel();
    ResultPanel resultPanel = new ResultPanel();
    NamePanel namePanel = new NamePanel();
    PasswordPanel passwordPanel = new PasswordPanel();
    DbButtonPanel dbButtonPanel =
        new DbButtonPanel(resultPanel, namePanel, passwordPanel, usersListPanel);
    ReRenderButtonPanel reRenderButtonPanel = new ReRenderButtonPanel(usersListPanel);

    add(resultPanel);
    add(namePanel);
    add(passwordPanel);
    add(dbButtonPanel);
    add(usersListPanel);
    add(reRenderButtonPanel);
  }

  public static void main(String[] args) {
    LoginPageFrame frame = new LoginPageFrame();
    frame.setVisible(true);
  }
}
