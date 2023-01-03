package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReRenderButtonListener implements ActionListener {
  UsersListPanel usersListPanel;

  public ReRenderButtonListener(UsersListPanel usersListPanel) {
    this.usersListPanel = usersListPanel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("再描画")) {
      usersListPanel.renderUsersList();
    }
  }
}
