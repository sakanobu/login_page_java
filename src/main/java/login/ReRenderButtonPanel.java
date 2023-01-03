package login;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ReRenderButtonPanel extends JPanel {
  public ReRenderButtonPanel(UsersListPanel usersListPanel) {
    JButton reRenderButton = new JButton("再描画");
    reRenderButton.setAlignmentX(0.5f);
    reRenderButton.setBackground(Color.RED);
    reRenderButton.addActionListener(new ReRenderButtonListener(usersListPanel));
    add(reRenderButton);
  }
}
