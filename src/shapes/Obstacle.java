package shapes;

import java.awt.event.KeyEvent;
import panels.MainPanel;

public class Obstacle extends Base {

    public Obstacle(int x, int y) {
        super(x, y, "src/resources/wall.png");
    }

    @Override
    public void update(MainPanel panel) {
        handlePackmanCollision(panel);
    }

    private void handlePackmanCollision(MainPanel panel) {
        Packman packman = panel.getStage().getPackman();

        if (panel.isKeyPressed(KeyEvent.VK_UP)) {
            chackUpSide(packman);
        } else if (panel.isKeyPressed(KeyEvent.VK_LEFT)) {
            chackLeftSide(packman);
        } else if (panel.isKeyPressed(KeyEvent.VK_DOWN)) {
            chackDownSide(packman);
        } else if (panel.isKeyPressed(KeyEvent.VK_RIGHT)) {
            chackRightSide(packman);
        }
    }

    private void chackLeftSide(Base obj) {
        if (chackCollision(obj)) {
            obj.setX(x + sizeX);
        }
    }

    private void chackRightSide(Base obj) {
        if (chackCollision(obj)) {
            obj.setX(x - obj.sizeX);
        }
    }

    private void chackUpSide(Base obj) {
        if (chackCollision(obj)) {
            obj.setY(y + sizeY);
        }
    }

    private void chackDownSide(Base obj) {
        if (chackCollision(obj)) {
            obj.setY(y - obj.sizeY);
        }
    }

}
