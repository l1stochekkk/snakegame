public class SnakeBody implements Tile {

    private final int number;
    private static int headX;
    private static int headY;
    private static int tailX;
    private static int tailY;

    public SnakeBody(int number) {
        this.number = number;
    }

    @Override
    public void print() {
        if (number == Map.getHeadNumber()) {
            System.out.print("#");
        } else System.out.print("O");
    }

    public static void setSnakeInitPos(int headPos) {
        headX = headPos;
        headY = headPos;
        tailX = headPos + 2;
        tailY = headPos;
    }

    public static int getHeadNumber(Tile[][] map) {
        SnakeBody head = (SnakeBody) map[headX][headY];
        return head.number;
    }

    private static int getTailNumber(Tile[][] map) {
        SnakeBody tail = (SnakeBody) map[tailX][tailY];
        return tail.number;
    }

    public static int getSnakeLength(Tile[][] map) {
        return getHeadNumber(map) - getTailNumber(map) + 1;
    }

    private static void moveTail(Tile[][] map) {
        for (int i = -1; i < 2; i += 2) {
            if (map[tailX + i][tailY] instanceof SnakeBody && getTailNumber(map) == ((SnakeBody) map[tailX + i][tailY]).number - 1) {
                map[tailX + i][tailY] = new SnakeBody(getTailNumber(map) + 1);
                map[tailX][tailY] = new EmptyTile();
                tailX = tailX + i;
            }
            else if (map[tailX][tailY + i] instanceof SnakeBody && getTailNumber(map) == ((SnakeBody) map[tailX][tailY + i]).number - 1) {
                map[tailX][tailY + i] = new SnakeBody(getTailNumber(map) + 1);
                map[tailX][tailY] = new EmptyTile();
                tailY = tailY + i;
            }
        }
    }


    private static void moveHead(Tile[][] map, int deltaX, int deltaY) {
        map[headX + deltaX][headY + deltaY] = new SnakeBody(getHeadNumber(map) + 1);
        headX += deltaX;
        headY += deltaY;
    }

    private static boolean checkNextPosIsLegal(Tile[][] map, int deltaX, int deltaY) {
        Tile nextPos = map[headX + deltaX][headY + deltaY];
        if (nextPos instanceof SnakeBody) {
            if (((SnakeBody) nextPos).number == getHeadNumber(map) - 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkNextPosIsDefeat(Tile[][] map, int deltaX, int deltaY) {
        Tile nextPos = map[headX + deltaX][headY + deltaY];
        return nextPos instanceof SnakeBody && ((SnakeBody) nextPos).number != getTailNumber(map) || nextPos instanceof Wall;
    }

    private static boolean checkNextPosIsMeal(Tile[][] map, int deltaX, int deltaY) {
        Tile nextPos = map[headX + deltaX][headY + deltaY];
        return nextPos instanceof Meal;
    }

    public static void move(Tile[][] map, Arrow arrow) {
        int deltaX = arrow.getDeltaX();
        int deltaY = arrow.getDeltaY();
        if (!checkNextPosIsLegal(map, deltaX, deltaY)) return;
        if (checkNextPosIsDefeat(map, deltaX, deltaY)) {
            Show.snakeAlive = false;
            return;
        }
        if (checkNextPosIsMeal(map, deltaX, deltaY)) {
            moveHead(map, deltaX, deltaY);
            Map.genMeal();
            return;
        }
        moveTail(map);
        moveHead(map, deltaX, deltaY);
    }
}

