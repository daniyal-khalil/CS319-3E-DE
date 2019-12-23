package sample;

public class Object
{
    // variables
    private int yPos;
    private int xPos;
    private int size;

    // methods
    /**
     * setter for X
     */
    public void setXPos(int xPos)
    {
        this.xPos = xPos;
    }

    /**
     * setter for Y
     * @param yPos y of mob character
     */
    public void setYPos(int yPos)
    {
        this.yPos = yPos;
    }

    /**
     * getter for Y
     * @return Y of mob character
     */
    public int getYPos()
    {
        return yPos;
    }

    /**
     * getter for X
     * @return X of mob character
     */
    public int getXPos()
    {
        return xPos;
    }

    /**
     * getter for Size
     * @return size of mob character
     */
    public int getSize()
    {
        return size;
    }

    /**
     * setter for size
     * @oaram size
     */
    public void setSize(int size)
    {
        this.size = size;
    }
}