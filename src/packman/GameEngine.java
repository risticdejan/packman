package packman;

import contracts.PackmanDeadListener;
import contracts.StartListener;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import panels.GameOverPanel;
import panels.MainPanel;
import panels.StartPanel;

public class GameEngine extends JFrame {

    private MainPanel mainPanel;
    private StartPanel startPanel;
    private GameOverPanel gameOverPanel;

    private final CardLayout cards;

    public GameEngine() {
        super("Packman");
        mainPanel = new MainPanel();
        startPanel = new StartPanel();
        gameOverPanel = new GameOverPanel();
        cards = new CardLayout();
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(750, 770));
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        setLayout(cards);

        startPanel.setListener((StartListener) () -> {
            cards.show(this.getContentPane(), "main");
            mainPanel.requestFocusInWindow();
            mainPanel.start();
        });

        gameOverPanel.setListener((StartListener) () -> {
            Store.clear();
            cards.show(this.getContentPane(), "main");
            mainPanel.requestFocusInWindow();
            mainPanel.start();
        });

        mainPanel.setListener((PackmanDeadListener) () -> {
            cards.show(this.getContentPane(), "gameOver");
            mainPanel.stop();
        });

        add(startPanel, "start");
        add(mainPanel, "main");
        add(gameOverPanel, "gameOver");

        pack();
    }
}
