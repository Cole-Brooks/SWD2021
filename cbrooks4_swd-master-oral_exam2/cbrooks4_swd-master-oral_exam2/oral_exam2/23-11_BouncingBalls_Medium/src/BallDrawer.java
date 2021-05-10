import javax.swing.*;

/**
 * this class contains the sleeping threads that prevent our balls from bouncing
 * a billion times in the blink of an eye
 */
public class BallDrawer implements Runnable{
    /** the component which needs to be drawn again*/
    private final JFrame comp;

    /**
     * constructor of the timer
     * @param c the component which will need to be redrawn
     */
    public BallDrawer(JFrame c){
        comp = c;
    }

    @Override
    public void run() {
        // infinite loop that just sleeps and redraws over and over
        while(true){
            try{
                Thread.sleep(30);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            comp.repaint();
        }
    }
}
