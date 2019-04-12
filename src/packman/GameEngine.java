package packman;

import contracts.StartListener;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import panels.MainPanel;
import panels.StartPanel;

public class GameEngine extends JFrame {

    private MainPanel mainPanel;

    private StartPanel startPanel;

    private final CardLayout cards;

    public GameEngine() {
        super("Packman");
        mainPanel = new MainPanel();
        startPanel = new StartPanel();
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
        });

        add(startPanel, "start");
        add(mainPanel, "main");

        pack();
    }
}
