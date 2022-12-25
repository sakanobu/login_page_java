package login;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class LoginPageFrame extends JFrame {
  LoginPageFrame() {
    setTitle("ログイン画面");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    int width = 800;
    int height = 1000;
    setSize(width, height);
    setLayout(new FlowLayout());

    Container contentPane = getContentPane();

    contentPane.add(new UsersListPanel());
  }

  public static void main(String[] args) {
    LoginPageFrame frame = new LoginPageFrame();
    frame.setVisible(true);
  }
}
