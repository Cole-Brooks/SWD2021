import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class is the implementation of the balls which will bounce for this GUI
 */
public class Ball implements Runnable{
    //////////////////////////////////////////////////////////////////
    //                  FIELDS
    //////////////////////////////////////////////////////////////////
    /** the list of all other balls */
    private static ArrayList<Ball> balls = new ArrayList<Ball>();
    /** x position of the ball */
    private int xPos;
    /** y position of the ball */
    private int yPos;
    /** x velocity of the ball */
    private int xVel;
    /** y velocity of the ball */
    private int yVel;
    /** Color of the Ball */
    private Color color;

    //////////////////////////////////////////////////////////////////
    //                  CONSTRUCTOR
    //////////////////////////////////////////////////////////////////
    public Ball(int x, int y){
        Random rn = new Random();
        setxVel(rn.nextInt(6));
        setyVel(-5);
        setxPos(x);
        setyPos(y);
        if(balls.isEmpty()){
            setColor(Color.BLUE);
        }
        else{
            int r = rn.nextInt(255);
            int g = rn.nextInt(255);
            int b = rn.nextInt(255);
            setColor(new Color(r,g,b));
        }
        balls.add(this);

    }

    //////////////////////////////////////////////////////////////////
    //                  OTHER METHODS
    //////////////////////////////////////////////////////////////////
    public void run(){
        while(true){
            try{
                Thread.sleep(30); // tried 10, too fast
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            // determine where the ball is moving to
            setyPos(getyPos() + getyVel());
            setxPos(getxPos() + getxVel());

            // Handle Wall Collisions
            /** height of the JPanel */
            int jpanelHeight = 800;
            if(getyPos() <= 0 || getyPos() + 50 >= jpanelHeight){
                setyVel(getyVel() * -1);
            }
            /** width of the JPanel */
            int jpanelWidth = 800;
            if(getxPos() <= 0 || getxPos() +50 >= jpanelWidth) {
                setxVel(getxVel() * -1);
            }
        }
    }


    //////////////////////////////////////////////////////////////////
    //                  GETTERS/SETTERS
    //////////////////////////////////////////////////////////////////

    /**
     * getter for the current x position of the ball
     * @return  xPos
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * setter for the current x position of the ball
     * @param xPos  new x position
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * getter for the current y position of the ball
     * @return  yPos
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * setter for the current y position of the ball
     * @param yPos  new y position
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * getter for the x velocity of the ball
     * @return current x velocity of the ball
     */
    public int getxVel() {
        return xVel;
    }

    /**
     * setter for the x velocity of the ball
     * @param xVel  new x velocity of the ball
     */
    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    /**
     * getter of the y velocity of the ball
     * @return current y velocity of the ball
     */
    public int getyVel() {
        return yVel;
    }

    /**
     * setter for the y velocity of the ball
     * @param yVel  the new y velocity of the ball
     */
    public void setyVel(int yVel) {
        this.yVel = yVel;
    }

    /**
     * getter of the color of the ball
     * @return  color of the ball
     */
    public Color getColor() {
        return color;
    }

    /**
     * setter of the color of the ball
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * gets the static ball array
     * @return
     */
    public static ArrayList<Ball> getBallArray(){
        return balls;
    }
}
