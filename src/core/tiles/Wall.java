package core.tiles;

public class Wall implements Tile {
    @Override
    public void print() {
        System.out.print("x");
    }

    @Override
    public String getEntity(){
        return "x";
    }
}