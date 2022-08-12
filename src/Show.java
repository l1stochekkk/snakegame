import java.util.Objects;
import java.util.Scanner;

public class Show {

    public static boolean snakeAlive = true;

    public static void display(Tile[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(" ");
                map[i][j].print();
            }
            System.out.println();
        }
    }

    public static void incorrectPos() {
        System.out.println("Incorrect input");
    }

    public static Arrow stringToArrow(String scanArrow) {
        return switch (scanArrow) {
            case "w" -> new Up();
            case "a" -> new Left();
            case "s" -> new Down();
            case "d" -> new Right();
            default -> null;
        };
    }

    public static void gameOver(Scanner newGame) {
        System.out.println();
        System.out.println("Game over");
        System.out.println("Your score: " + Map.getScore());
        System.out.println("Start new game?");
        System.out.println();
        if (Objects.equals(newGame.next(), "y")) {
            snakeAlive = true;
            Map.init();
        }

    }

    public static void gameLoop(Scanner scanArrow) {
        while (Show.snakeAlive) {
            Arrow arrow = stringToArrow(scanArrow.next());
            if (arrow != null) {
                Map.update(arrow);
            } else Show.incorrectPos();
            if (!Show.snakeAlive) {
                Show.gameOver(scanArrow);
            }
        }
        System.out.println();
        System.out.println("Goodbye!");

        SnakeBody map = new SnakeBody(10);
    }

}
