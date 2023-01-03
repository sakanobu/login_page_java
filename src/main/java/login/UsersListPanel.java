package login;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UsersListPanel extends JPanel {
  public UsersListPanel() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    int width = 800;
    int height = 250;
    setPreferredSize(new Dimension(width, height));
    setBackground(Color.WHITE);

    for (int i = 0; i < 4; i++) {
      JLabel recordLabel = new JLabel("test" + i);
      recordLabel.setAlignmentX(0.5f);
      add(recordLabel);
    }
  }

  public void renderUsersList() {
    this.removeAll();

    UsersRepository usersRepository = new UsersRepository();
    ArrayList<User> allUserList = usersRepository.findAll();

    for (User user : allUserList
    ) {
      JLabel userRecordLabel =
          new JLabel("%d %s %s".formatted(user.getId(), user.getName(), user.getPassword()));
      userRecordLabel.setAlignmentX(0.5f);
      this.add(userRecordLabel);
    }

    this.revalidate();
    this.repaint();
  }
}
