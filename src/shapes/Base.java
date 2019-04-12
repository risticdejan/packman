package shapes;

import contracts.Drawable;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import panels.MainPanel;

public abstract class Base implements Drawable {

    protected int x;

    protected int y;

    protected int sizeX;

    protected int sizeY;

    protected boolean status = true;

    protected ImageIcon img;

    public Base(int x, int y) {
        this(x, y, "src/resources/stena.gif", 30, 30);
    }

    public Base(int x, int y, String name) {
        this(x, y, name, 30, 30);
    }

    public Base(int x, int y, String name, int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x = x;
        this.y = y;
        this.img = name.equals("src/resources/space.gif") ? null : new ImageIcon(name);
    }

    public boolean chackCollision(Base obj) {
        return ((obj.x + obj.sizeX) > x) && ((x + sizeX) > obj.x)
                && ((obj.y < y + sizeY) && (y < obj.y + obj.sizeY));
    }

    @Override
    public void render(MainPanel panel, Graphics page) {
        if (status && img != null) {
            img.paintIcon(panel, page, x, y);
        }
    }

    @Override
    public abstract void update(MainPanel panel);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
