package login;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UsersListPanel extends JPanel {
  private JLabel usersListLabel;

  UsersListPanel() {
    setLayout(null);
    int width = 500;
    int height = 300;
    setPreferredSize(new Dimension(width, height));
    setBackground(Color.WHITE);

    usersListLabel = new JLabel("");
    usersListLabel.setBounds(10, 0, 300, 200);
    add(usersListLabel);
  }
}
