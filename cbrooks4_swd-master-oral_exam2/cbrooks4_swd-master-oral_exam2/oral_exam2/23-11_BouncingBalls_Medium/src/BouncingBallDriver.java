import javax.swing.*;

public class BouncingBallDriver extends JFrame {
    /** the panel where we will draw the balls */
    private BallPanel panel;

    /**
     * Constructor for the driver class.
     */
    public BouncingBallDriver(){
        panel = new BallPanel(this);
        add(panel);
        pack();
        setVisible(true);
    }

    /**
     * main function
     * @param args command line args
     */
    public static void main(String[] args){
        BouncingBallDriver app = new BouncingBallDriver();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
