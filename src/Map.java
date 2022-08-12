public class Map {

    static int size = 21;

    private final static Tile[][] map = new Tile[size][size];

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static void genWalls() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (i == 0 || i + 1 == map.length || j == 0 || j + 1 == map.length) {
                    map[i][j] = new Wall();
                } else map[i][j] = new EmptyTile();
            }
        }
    }

    public static void genMeal() {
        while (true) {
            int indexI = getRandomNumber(0, map.length);
            int indexJ = getRandomNumber(0, map.length);
            if (map[indexI][indexJ] instanceof EmptyTile) {
                map[indexI][indexJ] = new Meal();
                break;
            }
        }
    }

    public static void genSnake(){
        int i = 2;
        int center = size / 2;
        SnakeBody.setSnakeInitPos(center);
        while (i >= 0) {
            map[center - i + 2][center] = new SnakeBody(i);
            i--;
        }

    }

    public static void init() {
        genWalls();
        genSnake();
        genMeal();
        Show.display(map);
    }

    public static void update(Arrow arrow){
        SnakeBody.move(map, arrow);
        Show.display(map);
    }



    public static int getScore(){
        return SnakeBody.getSnakeLength(map);
    }

    public static int getHeadNumber() {
        return SnakeBody.getHeadNumber(map);
    }
}
