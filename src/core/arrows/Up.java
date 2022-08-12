package core.arrows;
public class Up implements Arrow {

    public int deltaX;
    public int deltaY;


    @Override
    public int getDeltaX() {
        return -1;
    }

    @Override
    public int getDeltaY() {
        return 0;
    }
}
