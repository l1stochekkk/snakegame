package core.arrows;
public class Right implements Arrow {
    @Override
    public int getDeltaX() {
        return 0;
    }

    @Override
    public int getDeltaY() {
        return 1;
    }
}
