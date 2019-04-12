package packman;

import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import panels.MainPanel;

public class GameEngine extends JFrame {

    MainPanel mainPanel;

    public GameEngine() {
        super("Packman");
        mainPanel = new MainPanel();

    }

    public void start() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(750, 770));
        setResizable(false);
        setVisible(true);
        setFocusable(true);

        add(mainPanel);

        pack();
    }
}
