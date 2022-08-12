import core.*;
import display.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GameMap.init();

        Frame.init();

        Scanner scanArrow = new Scanner(System.in);
        Show.gameLoop(scanArrow);

    }
}