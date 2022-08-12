package core.arrows;
public class Down implements Arrow {
    @Override
    public int getDeltaX() {
        return 1;
    }

    @Override
    public int getDeltaY() {
        return 0;
    }
}
