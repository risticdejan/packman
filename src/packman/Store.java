package packman;

public class Store {

    private static int level = 1;
    private static int total;

    private Store() {
    }

    public static void clear() {
        level = 1;
        total = 0;
    }

    public static void upTotal(int val) {
        total += val;
    }

    public static void upLevel(int val) {
        level += val;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int l) {
        level = l;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int t) {
        total = t;
    }
}
