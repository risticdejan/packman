package packman;

import contracts.Drawable;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import panels.MainPanel;
import shapes.Base;
import shapes.FactoryObject;
import shapes.Packman;

public class Stage implements Drawable {

    private int CEIL = 30;

    private Packman packman;

    private Base[][] mat;

    public Stage() {
        loadStageData("src/resources/data1.txt");
    }

    private void loadStageData(String path) {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(path)))) {

            String[] meta = sc.nextLine().split(" ");

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
}
