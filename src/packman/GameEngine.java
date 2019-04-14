package packman;

import contracts.GameOverListener;
import contracts.NextListener;
import contracts.PackmanDeadListener;
import contracts.StartListener;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import panels.GameOverPanel;
import panels.MainPanel;
import panels.NextLevelPanel;
import panels.StartPanel;

public class GameEngine extends JFrame {

    private MainPanel mainPanel;
    private StartPanel startPanel;
    private GameOverPanel gameOverPanel;
    private NextLevelPanel nextLevelPanel;

    private final CardLayout cards;

    public GameEngine() {
        super("Packman");
        mainPanel = new MainPanel();
        startPanel = new StartPanel();
        gameOverPanel = new GameOverPanel();
        nextLevelPanel = new NextLevelPanel();
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

        mainPanel.setListener((NextListener) () -> {
            cards.show(this.getContentPane(), "next");
            mainPanel.stop();
        });

        mainPanel.setListener((PackmanDeadListener) () -> {
            cards.show(this.getContentPane(), "gameOver");
            mainPanel.stop();
        });

        mainPanel.setListener((GameOverListener) () -> {
            cards.show(this.getContentPane(), "gameOver");
            mainPanel.stop();
        });

        nextLevelPanel.setListener((NextListener) () -> {
            Store.upLevel(1);
            cards.show(this.getContentPane(), "main");
            mainPanel.requestFocusInWindow();
            mainPanel.start();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.gc();
                System.exit(0);
            }
        });

        add(startPanel, "start");
        add(mainPanel, "main");
        add(gameOverPanel, "gameOver");
        add(nextLevelPanel, "next");

        pack();
    }
}
