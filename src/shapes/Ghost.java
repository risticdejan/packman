package shapes;

import contracts.Moveable;
import static java.lang.Thread.interrupted;
import panels.MainPanel;

public class Ghost extends Base implements Moveable, Runnable {

    protected int JUMP = 7;

    private int direction = 2;
    private final int speed;
    private final MainPanel panel;

    public Ghost(int x, int y, int speed, MainPanel panel) {
        super(x, y, "src/resources/ghost.gif");
        this.speed = speed;
        this.panel = panel;
    }

    @Override
    public void update(MainPanel panel) {
        switch (direction) {
            case 1:
                moveUp(panel);
                break;
            case 2:
                moveRight(panel);
                break;
            case 3:
                moveDown(panel);
                break;
            case 4:
                moveLeft(panel);
                break;
        }
    }

    @Override
    public void moveLeft(MainPanel panel) {
        if (x > 0) {
            x -= JUMP;
        } else {
            x = 0;
            direction = 2;
        }
    }

    @Override
    public void moveRight(MainPanel panel) {
        int limitX = panel.getWidth() - sizeX;
        if (x < limitX) {
            x += JUMP;
        } else {
            x = limitX;
            direction = 4;
        }
    }

    @Override
    public void moveUp(MainPanel panel) {
        if (y > 0) {
            y -= JUMP;
        } else {
            y = 0;
            direction = 3;
        }
    }

    @Override
    public void moveDown(MainPanel panel) {
        int limitY = panel.getHeight() - sizeY;
        if (y < limitY) {
            y += JUMP;
        } else {
            y = limitY;
            direction = 1;
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            while (!interrupted()) {
                switch (direction) {
                    case 1:
                        moveUp(panel);
                        break;
                    case 2:
                        moveRight(panel);
                        break;
                    case 3:
                        moveDown(panel);
                        break;
                    case 4:
                        moveLeft(panel);
                        break;
                }

                for (Base[] row : panel.getStage().getMat()) {
                    for (Base item : row) {
                        item.update(panel);
                    }
                }

                Packman packman = panel.getStage().getPackman();

                packman.canItBeEaten(this, panel);

                panel.repaint();

                Thread.sleep(speed);
            }
        } catch (InterruptedException ex) {
            System.out.println("threads closed");
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
