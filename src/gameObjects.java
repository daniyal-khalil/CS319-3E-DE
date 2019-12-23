package sample;

public class gameObjects {
    private int x;
    private int y;

    gameObjects() {
        x = 0;
        y = 0;
    }

    /**
     * getter for X
     * @return x to be setted.
     */
    public int getX() {
        return x;
    }

    /**
     * getter for name
     * @return  y to be setted.
     */
    public int getY() {
        return y;
    }

    /**
     * setter for X
     * @param x to be setted.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setter for Y
     * @param y to be setted.
     */
    public void setY(int y) {
        this.y = y;
    }
}
