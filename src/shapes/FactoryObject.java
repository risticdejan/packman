package shapes;

public class FactoryObject {

    public static Base create(String st, int x, int y) {
        Base obj = null;
        switch (st) {
            case "0":
                obj = new Space(x, y);
                break;
            case "1":
                obj = new Obstacle(x, y);
                break;
            case "2":
                obj = new Donut(x, y);
                break;
            case "3":;
                obj = new Banana(x, y);
                break;
            case "4":;
                obj = new Charry(x, y);
                break;
            default:
                obj = new Space(x, y);
                break;
        }

        return obj;
    }
}
