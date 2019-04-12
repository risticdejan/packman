package packman;

import javax.swing.SwingUtilities;

/**
 *
 * @author dejan
 */
public class Packman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            (new GameEngine()).start();
        });
    }

}
