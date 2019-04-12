package contracts;

import panels.MainPanel;

public interface Moveable {

    public void moveLeft(MainPanel panel);

    public void moveRight(MainPanel panel);

    public void moveUp(MainPanel panel);

    public void moveDown(MainPanel panel);
}
