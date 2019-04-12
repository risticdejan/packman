package contracts;

import panels.MainPanel;
import shapes.Base;

public interface Eatable {

    public void canItBeEaten(Base object, MainPanel panel);
}
