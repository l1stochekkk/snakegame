package core.tiles;

public class EmptyTile implements Tile {
    @Override
    public void print() {
        System.out.print(" ");
    }

    @Override
    public String getEntity(){
        return " ";
    }
}