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
    String name = namePanel.getNameText();
    String password = passwordPanel.getPasswordText();

    if (e.getActionCommand().equals("ログイン")) {
      if (usersTable.existTargetUser(name, password)) {
        resultPanel.setResultLabelText("ログイン OK");
      } else {
        resultPanel.setResultLabelText("ログイン NG");
      }
    } else if (e.getActionCommand().equals("新規追加")) {
      usersTable.create(name, password);
    }
    usersListPanel.renderUsersList();
  }
}
