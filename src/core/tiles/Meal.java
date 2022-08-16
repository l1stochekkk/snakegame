package core.tiles;

public class Meal implements Tile {
    @Override
    public void print() {
        System.out.print("*");
    }

    @Override
    public String getEntity(){
        return "*";
    }
}