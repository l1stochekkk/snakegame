package core.tiles;

import core.arrows.Arrow;
import core.GameMap;
import display.Show;

import java.util.Objects;

public class SnakeBody implements Tile {

    private final int number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnakeBody snakeBody = (SnakeBody) o;
        return number == snakeBody.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    private static int headX;
    private static int headY;
    private static int tailX;
    private static int tailY;

    public SnakeBody(int number) {
        this.number = number;
    }

    @Override
    public void print() {
        if (number == GameMap.getHeadNumber()) {
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
            if (map[tailX - 1][tailY] instanceof SnakeBody && getTailNumber(map) == ((SnakeBody) map[tailX - 1][tailY]).number - 1) {
                map[tailX - 1][tailY] = new SnakeBody(getTailNumber(map) + 1);
                map[tailX][tailY] = new EmptyTile();
                tailX--;
            }
            else if (map[tailX + 1][tailY] instanceof SnakeBody && getTailNumber(map) == ((SnakeBody) map[tailX + 1][tailY]).number - 1) {
                map[tailX + 1][tailY] = new SnakeBody(getTailNumber(map) + 1);
                map[tailX][tailY] = new EmptyTile();
                tailX++;
            }
            else if (map[tailX][tailY - 1] instanceof SnakeBody && getTailNumber(map) == ((SnakeBody) map[tailX][tailY - 1]).number - 1) {
                map[tailX][tailY - 1] = new SnakeBody(getTailNumber(map) + 1);
                map[tailX][tailY] = new EmptyTile();
                tailY--;
            }
            else if (map[tailX][tailY + 1] instanceof SnakeBody && getTailNumber(map) == ((SnakeBody) map[tailX][tailY + 1]).number - 1) {
                map[tailX][tailY + 1] = new SnakeBody(getTailNumber(map) + 1);
                map[tailX][tailY] = new EmptyTile();
                tailY++;
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
            return ((SnakeBody) nextPos).number != getHeadNumber(map) - 1;
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
            GameMap.genMeal();
            return;
        }
        moveTail(map);
        moveHead(map, deltaX, deltaY);
    }
}

