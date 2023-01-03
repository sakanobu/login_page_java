package login;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasswordPanel extends JPanel {
  private final JLabel passwordLabel = new JLabel("Password");
  private final JTextField passwordField = new JTextField();

  public PasswordPanel() {
    setLayout(null);
    int width = 800;
    int height = 30;
    setPreferredSize(new Dimension(width, height));

    passwordLabel.setBounds(0, 0, 70, 30);
    passwordLabel.setHorizontalAlignment(JLabel.LEFT);
    add(passwordLabel);
    passwordField.setBounds(70, 0, 710, 30);
    passwordField.setHorizontalAlignment(JLabel.LEFT);
    add(passwordField);
  }

  public String getPasswordText() {
    return passwordField.getText();
  }
}
