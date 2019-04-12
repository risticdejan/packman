package packman;

import contracts.Drawable;
import java.awt.Graphics;
import panels.MainPanel;
import shapes.Packman;

public class Stage implements Drawable {

    private Packman packman;

    public Stage() {
        packman = new Packman(100, 100);
    }

    @Override
    public void render(MainPanel panel, Graphics g) {
        packman.render(panel, g);
    }

    @Override
    public void update(MainPanel panel) {
        packman.update(panel);
    }
}
