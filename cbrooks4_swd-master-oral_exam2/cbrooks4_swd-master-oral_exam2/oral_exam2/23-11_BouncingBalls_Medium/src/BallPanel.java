import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BallPanel extends JPanel {
    //////////////////////////////////////////////////////////////////
    //                  FIELDS
    //////////////////////////////////////////////////////////////////
    /** the executor service */
    private final ExecutorService executor;
    /** parent window */
    private JFrame parent;

    //////////////////////////////////////////////////////////////////
    //                  CONSTRUCTORS
    //////////////////////////////////////////////////////////////////
    /**
     * Constructor for BallPanel
     * @param window    the JFrame for the panel to be drawn in
     */
    public BallPanel(JFrame window){
        setParent(window);

        executor = Executors.newCachedThreadPool();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                newBall(e);
                BallDrawer t = new BallDrawer(parent);
                executor.execute(t);
            }
        });
    }

    //////////////////////////////////////////////////////////////////
    //                  OTHER METHODS
    //////////////////////////////////////////////////////////////////

    /**
     * creates a new ball and adds it to the box with the others
     * @param e the mouse event which caused another ball to be added
     */
    private void newBall(MouseEvent e){
        // only make a new ball if there are between 0 and 20 balls
        if(Ball.getBallArray().size() < 20) {
            int x = e.getX();
            int y = e.getY();
            executor.execute(new Ball(x, y)); // get them balls rolling
        }
    }

    /**
     * Paint the balls
     * @param g Graphics
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ArrayList<Ball> balls = Ball.getBallArray();
        for (Ball ball : balls) {
            g.setColor(ball.getColor());
            g.fillOval(ball.getxPos(), ball.getyPos(), 50, 50);
        }
    }

    /**
     * getter for BallPanel's parent JFrame
     * @return  the parent of the JFrame
     */
    @Override
    public JFrame getParent() {
        return parent;
    }

    /**
     * setter for BallPanel's parent JFrame
     * @param parent    the new parent of the BallPanel
     */
    public void setParent(JFrame parent) {
        this.parent = parent;
    }

    // Handle Sizing

    /**
     * gets the minimum size that the panel can pop up as
     * @return  a dimension denoting the minimum size
     */
    @Override
    public Dimension getMinimumSize(){
        return new Dimension(800,800);
    }

    /**
     * gets the preferred size that hopefully the panel will pop up as
     * @return  a dimension denoting the preferred size
     */
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800,800);
    }
}
