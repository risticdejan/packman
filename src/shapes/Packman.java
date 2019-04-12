package shapes;

import contracts.Moveable;
import java.awt.event.KeyEvent;
import panels.MainPanel;

public class Packman extends Base implements Moveable {

    protected int JUMP = 5;

    public Packman(int x, int y) {
        super(x, y, "src/resources/peckmanLeft.gif", 30, 30);
    }

    @Override
    public void update(MainPanel panel) {
        if (panel.isKeyPressed(KeyEvent.VK_UP)) {
            moveUp(panel);
        } else if (panel.isKeyPressed(KeyEvent.VK_LEFT)) {
            moveLeft(panel);
        } else if (panel.isKeyPressed(KeyEvent.VK_DOWN)) {
            moveDown(panel);
        } else if (panel.isKeyPressed(KeyEvent.VK_RIGHT)) {
            moveRight(panel);
        }
    }

    @Override
    public void moveLeft(MainPanel panel) {
        setImg("src/resources/peckmanLeft.gif");
        if (x > 0) {
            x -= JUMP;
        } else {
            x = 0;
        }
    }

    @Override
    public void moveRight(MainPanel panel) {
        setImg("src/resources/peckmanRight.gif");
        int limitX = panel.getWidth() - sizeX;
        if (x < limitX) {
            x += JUMP;
        } else {
            x = limitX;
        }
    }

    @Override
    public void moveUp(MainPanel panel) {
        setImg("src/resources/peckmanUp.gif");
        if (y > 0) {
            y -= JUMP;
        } else {
            y = 0;
        }
    }

    @Override
    public void moveDown(MainPanel panel) {
        setImg("src/resources/peckmanDown.gif");
        int limitY = panel.getHeight() - sizeY;
        if (y < limitY) {
            y += JUMP;
        } else {
            y = limitY;
        }
    }
}
