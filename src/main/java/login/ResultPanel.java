package login;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {
  private final JLabel resultLabel = new JLabel("結果表示欄");

  public ResultPanel() {
    int width = 800;
    int height = 30;
    setPreferredSize(new Dimension(width, height));
    setBackground(Color.WHITE);

    add(resultLabel);
  }
}
