package login;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DbButtonPanel extends JPanel {
  private final JTextField idField = new JTextField("");

  public DbButtonPanel(ResultPanel resultPanel, NamePanel namePanel,
                       PasswordPanel passwordPanel, UsersListPanel usersListPanel) {
    setLayout(new GridLayout(3, 2));
    int width = 800;
    int height = 100;
    setPreferredSize(new Dimension(width, height));

    JButton loginButton = new JButton("ログイン");
    loginButton.addActionListener(
        new DbButtonListener(resultPanel, namePanel, passwordPanel, usersListPanel));
    add(loginButton);

    JButton createButton = new JButton("新規追加");
    add(createButton);

    JLabel idLabel = new JLabel("ID入力:");
    idLabel.setHorizontalAlignment(JLabel.RIGHT);
    add(idLabel);

    add(idField);

    JButton updateButton = new JButton("更新");
    add(updateButton);

    JButton deleteButton = new JButton("削除");
    add(deleteButton);
  }
}
