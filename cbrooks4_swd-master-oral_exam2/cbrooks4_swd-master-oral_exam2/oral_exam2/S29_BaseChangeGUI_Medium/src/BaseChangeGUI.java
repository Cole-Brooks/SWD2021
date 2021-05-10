import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseChangeGUI extends Application {

    public static void main(String[] args) {
        // create a BaseChangeGUI object and call its start method
        launch(args);
    }

    /**
     * Start: starts up the GUI
     * @param stage the stage for the application
     */
    @Override
    public void start(Stage stage) {
        // construct scene graph
        Parent root =
                null;
        try {
            root = FXMLLoader.load(getClass().getResource("BaseChangeGUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root); // attach scene graph to scene
        stage.setTitle("Base Change Calculator"); // displayed in window's title bar
        stage.setScene(scene); // attach scene to stage
        stage.show(); // display the stage
    }
}
