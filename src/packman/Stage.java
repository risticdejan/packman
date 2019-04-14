package packman;

import contracts.Drawable;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import panels.MainPanel;
import shapes.Base;
import shapes.FactoryObject;
import shapes.Ghost;
import shapes.Packman;

public class Stage implements Drawable {

    private int CEIL = 30;
    private final MainPanel panel;

    private Packman packman;
    private Base[][] mat;
    private final ArrayList<Ghost> ghosts;

    public Stage(MainPanel panel) {
        ghosts = new ArrayList<>();
        this.panel = panel;
        loadStageData("src/resources/data1.txt");
    }

    private void loadStageData(String path) {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(path)))) {

            String[] meta = sc.nextLine().split(" ");
            String[] meta2 = sc.nextLine().split(" ");
            String meta3 = sc.nextLine();

            mat = new Base[Integer.parseInt(meta[0])][Integer.parseInt(meta[1])];
            int i = 0;
            while (sc.hasNextLine()) {
                String st = sc.nextLine();
                String[] stArr = st.split(" ");
                for (int j = 0; j < stArr.length; j++) {
                    mat[j][i] = FactoryObject.create(stArr[j], (CEIL * j) + 60, (CEIL * i) + 40);
                }
                i++;
            }

            packman = new Packman(Integer.parseInt(meta[2]), Integer.parseInt(meta[3]));

            int speed = Integer.parseInt(meta3);
            for (int j = 0; j < meta2.length; j += 2) {
                ghosts.add(new Ghost(
                        Integer.parseInt(meta2[j]),
                        Integer.parseInt(meta2[j + 1]),
                        speed,
                        panel
                ));
            }

        } catch (FileNotFoundException ex) {
            System.out.println("file not found.");
        }
    }

    @Override
    public void render(MainPanel panel, Graphics g) {
        packman.render(panel, g);

        for (Base[] row : mat) {
            for (Base item : row) {
                item.render(panel, g);
            }
        }

        ghosts.forEach((ghost) -> {
            ghost.render(panel, g);
        });
    }

    @Override
    public void update(MainPanel panel) {
        packman.update(panel);

        for (Base[] row : mat) {
            for (Base item : row) {
                item.update(panel);
            }
        }
    }

    public boolean isPackmanDead() {
        return packman.isStatus() == false;
    }

    public Packman getPackman() {
        return packman;
    }

    public Base[][] getMat() {
        return mat;
    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }
}
