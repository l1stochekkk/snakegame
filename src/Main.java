import core.GameMap;
import display.Show;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GameMap.init();
        Scanner scanArrow = new Scanner(System.in);
        Show.gameLoop(scanArrow);

    }
}