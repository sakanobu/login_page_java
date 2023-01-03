package login;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LoginPageFrame extends JFrame {
  LoginPageFrame() {
    setTitle("ログイン画面");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    int width = 800;
    int height = 1000;
    setSize(width, height);
    setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

    ResultPanel resultPanel = new ResultPanel();
    add(resultPanel);

    NamePanel namePanel = new NamePanel();
    add(namePanel);

    PasswordPanel passwordPanel = new PasswordPanel();
    add(passwordPanel);

    DbButtonPanel dbButtonPanel = new DbButtonPanel();
    add(dbButtonPanel);

    UsersListPanel usersListPanel = new UsersListPanel();
    add(usersListPanel);

    JButton updateButton = new JButton("更新");
    updateButton.setAlignmentX(0.5f);
    updateButton.setBackground(Color.RED);
    add(updateButton);
  }

  public static void main(String[] args) {
    LoginPageFrame frame = new LoginPageFrame();
    frame.setVisible(true);
  }
}
