package login;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NamePanel extends JPanel {
  private final JLabel nameLabel = new JLabel("Name");
  private final JTextField nameField = new JTextField();

  public NamePanel() {
    setLayout(null);
    int width = 800;
    int height = 30;
    setPreferredSize(new Dimension(width, height));

    nameLabel.setBounds(0, 0, 70, 30);
    nameLabel.setHorizontalAlignment(JLabel.LEFT);
    add(nameLabel);
    nameField.setBounds(70, 0, 710, 30);
    nameField.setHorizontalAlignment(JLabel.LEFT);
    add(nameField);
  }

  public String getNameText() {
    return nameField.getText();
  }
}
