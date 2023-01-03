package login;

import java.awt.Color;
import java.awt.Dimension;
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
}
