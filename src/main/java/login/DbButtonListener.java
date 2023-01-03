package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DbButtonListener implements ActionListener {
  UsersTable usersTable = new UsersTable();
  ResultPanel resultPanel;
  NamePanel namePanel;
  PasswordPanel passwordPanel;
  UsersListPanel usersListPanel;

  public DbButtonListener(ResultPanel resultPanel, NamePanel namePanel,
                          PasswordPanel passwordPanel, UsersListPanel usersListPanel) {
    this.resultPanel = resultPanel;
    this.namePanel = namePanel;
    this.passwordPanel = passwordPanel;
    this.usersListPanel = usersListPanel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("ログイン")) {
      if (usersTable.existTargetUser(namePanel.getNameText(),
          passwordPanel.getPasswordText())) {
        resultPanel.setResultLabelText("ログイン OK");
      } else {
        resultPanel.setResultLabelText("ログイン NG");
      }
    }
    usersListPanel.renderUsersList();
  }
}
