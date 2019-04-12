package contracts;

import java.awt.Graphics;
import panels.MainPanel;

public interface Drawable {

    public abstract void render(MainPanel panel, Graphics g);

    public abstract void update(MainPanel panel);
}
