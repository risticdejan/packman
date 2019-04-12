package panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import packman.Stage;

public class MainPanel extends JPanel {

    private JLabel level;
    private JLabel total;

    private Stage stage;

    public MainPanel() {
        stage = new Stage();

        level = new JLabel("level 1");
        level.setFont(new java.awt.Font("Arial", 1, 22));
        level.setForeground(new java.awt.Color(204, 204, 204));

        total = new JLabel("total 0");
        total.setFont(new java.awt.Font("Arial", 1, 22));
        total.setForeground(new java.awt.Color(204, 204, 204));

        setBackground(new Color(12, 12, 12));

        add(level);
        add(total);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (stage != null) {;
            stage.render(this, g);
        }
    }
}
