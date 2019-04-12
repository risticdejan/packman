package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;
import packman.Stage;

public class MainPanel extends JPanel {

    private JLabel level;
    private JLabel total;

    private Stage stage;

    private KeyListener MainPanelKeyListener;
    private Set<Integer> keys = new HashSet<>();

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

        MainPanelKeyListener = new MainPanelKeyListener(this);
        addKeyListener(MainPanelKeyListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (stage != null) {
            stage.render(this, g);
        }
    }

    public boolean isKeyPressed(int key) {
        return keys.contains(key);
    }

    public Stage getStage() {
        return stage;
    }

    private class MainPanelKeyListener extends KeyAdapter {

        private final MainPanel panel;

        public MainPanelKeyListener(MainPanel panel) {
            this.panel = panel;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            keys.add(e.getKeyCode());
            stage.update(panel);
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            keys.remove(e.getKeyCode());
            stage.update(panel);
            repaint();
        }
    }
}
