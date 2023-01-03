package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class DbButtonListener implements ActionListener {
  UsersTable usersTable = new UsersTable();
  ResultPanel resultPanel;
  NamePanel namePanel;
  PasswordPanel passwordPanel;
  UsersListPanel usersListPanel;
  JTextField idField;

  public DbButtonListener(ResultPanel resultPanel, NamePanel namePanel,
                          PasswordPanel passwordPanel, UsersListPanel usersListPanel,
                          JTextField idField) {
    this.resultPanel = resultPanel;
    this.namePanel = namePanel;
    this.passwordPanel = passwordPanel;
    this.usersListPanel = usersListPanel;
    this.idField = idField;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String name = namePanel.getNameText();
    String password = passwordPanel.getPasswordText();
    String id = idField.getText();

    if (e.getActionCommand().equals("ログイン")) {
      if (usersTable.existTargetUser(name, password)) {
        resultPanel.setResultLabelText("ログイン OK");
      } else {
        resultPanel.setResultLabelText("ログイン NG");
      }
    } else if (e.getActionCommand().equals("新規追加")) {
      usersTable.create(name, password);
    } else if (e.getActionCommand().equals("更新")) {
      usersTable.update(id, name, password);
    } else if (e.getActionCommand().equals("削除")) {
      usersTable.delete(id);
    }
    usersListPanel.renderUsersList();
  }
}
