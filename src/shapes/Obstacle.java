package shapes;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import panels.MainPanel;

public class Obstacle extends Base {

    public Obstacle(int x, int y) {
        super(x, y, "src/resources/wall.png");
    }

    @Override
    public void update(MainPanel panel) {
        handleGhostCollision(panel);
        handlePackmanCollision(panel);
    }

    private void handleGhostCollision(MainPanel panel) {
        ArrayList<Ghost> ghosts = panel.getStage().getGhosts();

        for (Ghost ghost : ghosts) {
            int direction = ghost.getDirection();
            switch (direction) {
                case 1:
                    chackUpSide(ghost);
                    break;
                case 2:
                    chackRightSide(ghost);
                    break;
                case 3:
                    chackDownSide(ghost);
                    break;
                case 4:
                    chackLeftSide(ghost);
                    break;
            }

        }

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
            setDiraction(obj);
            obj.setX(x + sizeX);
        }
    }

    private void chackRightSide(Base obj) {
        if (chackCollision(obj)) {
            setDiraction(obj);
            obj.setX(x - obj.sizeX);
        }
    }

    private void chackUpSide(Base obj) {
        if (chackCollision(obj)) {
            setDiraction(obj);
            obj.setY(y + sizeY);
        }
    }

    private void chackDownSide(Base obj) {
        if (chackCollision(obj)) {
            setDiraction(obj);
            obj.setY(y - obj.sizeY);
        }
    }

    private void setDiraction(Base obj) {
        if (obj instanceof Ghost) {
            int direction = ((int) (Math.floor(Math.random() * 4)) + 1);
            Ghost ghost = (Ghost) obj;
            ghost.setDirection(direction);
        }
    }

}
