package display;

import core.arrows.*;
import core.GameMap;
import core.tiles.Tile;

import java.util.Objects;
import java.util.Scanner;

public class Show {

    public static boolean snakeAlive = true;

    public static void display(Tile[][] map) {
        for (Tile[] rowOfTiles : map) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(" ");
                rowOfTiles[j].print();
            }
            System.out.println();
        }
    }

    private static void incorrectPos() {
        System.out.println("Incorrect input");
    }

    private static Arrow stringToArrow(String scanArrow) {
        return switch (scanArrow) {
            case "w" -> new Up();
            case "a" -> new Left();
            case "s" -> new Down();
            case "d" -> new Right();
            default -> null;
        };
    }

    private static void gameOver(Scanner newGame) {
        System.out.println();
        System.out.println("Game over");
        System.out.println("Your score: " + GameMap.getScore());
        System.out.println("Start new game? (y)");
        System.out.println();
        if (Objects.equals(newGame.next(), "y")) {
            snakeAlive = true;
            GameMap.init();
        }

    }

    public static void gameLoop() {
        Scanner scanArrow = new Scanner(System.in);
        while (Show.snakeAlive) {
            Arrow arrow = stringToArrow(scanArrow.next());
            if (arrow != null) {
                GameMap.update(arrow);
            } else Show.incorrectPos();
            if (!Show.snakeAlive) {
                Show.gameOver(scanArrow);
            }
        }
        System.out.println();
        System.out.println("Goodbye!");
    }

}
