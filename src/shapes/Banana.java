package shapes;

import contracts.Eatable;
import javafx.scene.media.AudioClip;
import packman.Store;
import panels.MainPanel;

public class Banana extends Base implements Eatable {

    protected int val = 20;

    private final AudioClip sound;

    public Banana(int x, int y) {
        super(x, y, "src/resources/banana.png");
        this.sound = new AudioClip(Banana.class.getResource("../resources/woosh.wav").toString());
    }

    @Override
    public void update(MainPanel panel) {
        Packman packman = panel.getStage().getPackman();
        canItBeEaten(packman, panel);
    }

    @Override
    public void canItBeEaten(Base object, MainPanel panel) {
        if (chackCollision(object) && isStatus() == true) {
            if (object instanceof Packman) {
                Store.upTotal(val);
                panel.setTotalText(Store.getTotal());
            }
            setStatus(false);
            sound.play();
        }
    }
}
