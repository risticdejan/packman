package panels;

import contracts.GameOverListener;
import contracts.Listener;
import contracts.NextListener;
import contracts.PackmanDeadListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import packman.Stage;
import packman.Store;

public class MainPanel extends JPanel {

    private JLabel level;
    private JLabel total;

    private Stage stage;

    private KeyListener MainPanelKeyListener;
    private Set<Integer> keys = new HashSet<>();

    private final Timer timer;
    private Map<String, Object> listeners = new HashMap<>();

    private Thread[] threads;

    public MainPanel() {
        MainPanelKeyListener = new MainPanelKeyListener(this);

        setBackground(new Color(12, 12, 12));

        level = new JLabel("level 1");
        level.setFont(new java.awt.Font("Arial", 1, 22));
        level.setForeground(new java.awt.Color(204, 204, 204));

        total = new JLabel("total 0");
        total.setFont(new java.awt.Font("Arial", 1, 22));
        total.setForeground(new java.awt.Color(204, 204, 204));

        timer = new Timer(200, (ActionEvent e) -> {
            if (stage.isEverythingEaten() && Store.getLevel() == 12) {
                GameOverListener listener;
                if ((listener
                        = (GameOverListener) listeners.get("gameOver")) != null) {
                    listener.gameOver();
                }
            } else if (stage.isEverythingEaten()) {
                NextListener listener;
                if ((listener
                        = (NextListener) listeners.get("nextLevel")) != null) {
                    listener.nextLevel();
                }
            } else if (stage.isPackmanDead()) {
                PackmanDeadListener listener;
                if ((listener
                        = (PackmanDeadListener) listeners.get("packmanDead")) != null) {
                    listener.dead();
                }
            }

        });

        add(level);
        add(total);
    }

    public void start() {
        stage = new Stage(this);

        threads = new Thread[stage.getGhosts().size()];
        for (int i = 0; i < stage.getGhosts().size(); i++) {
            threads[i] = new Thread(stage.getGhosts().get(i));
            threads[i].start();
        }

        setLevelText(Store.getLevel());
        setTotalText(Store.getTotal());
        addKeyListener(MainPanelKeyListener);
        timer.start();
    }

    public void stop() {
        removeKeyListener(MainPanelKeyListener);
        stage = null;
        for (Thread thread : threads) {
            thread.interrupt();
        }
        threads = null;
        timer.stop();
        System.gc();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (stage != null) {
            stage.render(this, g);
        }
    }

    public void setListener(Listener listener) {
        if (listener instanceof NextListener) {
            this.listeners.put("nextLevel", listener);
        }

        if (listener instanceof PackmanDeadListener) {
            this.listeners.put("packmanDead", listener);
        }

        if (listener instanceof GameOverListener) {
            this.listeners.put("gameOver", listener);
        }
    }

    public boolean isKeyPressed(int key) {
        return keys.contains(key);
    }

    public Stage getStage() {
        return stage;
    }

    public void setLevelText(int i) {
        String st = "Level: " + i;
        this.level.setText(st);
    }

    public void setTotalText(int i) {
        String st = "Total: " + i;
        this.total.setText(st);
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
