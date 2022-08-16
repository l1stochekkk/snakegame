package core.tiles;

public interface Tile {
    default void print(){
    }
    default String getEntity(){
        return "";
    }
}