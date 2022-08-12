import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Map.init();
        Scanner scanArrow = new Scanner(System.in);
        Show.gameLoop(scanArrow);

    }
}