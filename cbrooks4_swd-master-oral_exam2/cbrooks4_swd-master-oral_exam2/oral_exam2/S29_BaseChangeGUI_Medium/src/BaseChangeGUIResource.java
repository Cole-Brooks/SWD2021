import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class BaseChangeGUIResource {
    /** a list of supported bases */
    private static final ObservableList<Integer> bases =
            FXCollections.observableArrayList(
                    2,3,4,5,6,7,8,9,10,11,12,13,14,15
                    ,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30
                    ,31,32
            );

    @FXML
    private ChoiceBox<Integer> newBaseSelect;

    @FXML
    private ChoiceBox<Integer> curBaseSelect;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextField outputTextField;

    // Initialization
    @FXML
    public void initialize(){
        curBaseSelect.setItems(bases);
        curBaseSelect.setValue(10);
        newBaseSelect.setItems(bases);
        newBaseSelect.setValue(10);
    }

    // Methods

    /**
     * method called by the calculate button. Calculates the input in a new base.
     * @param e action event from the button that was pressed.
     */
    public void calculate(ActionEvent e){
        try {
            String input = inputTextField.getText();
            int curBase = curBaseSelect.getValue();
            int newBase = newBaseSelect.getValue();

            outputTextField.setText(Integer.toString(Integer.parseInt(input, curBase), newBase));

        }catch (Exception exception){
            outputTextField.setText("NaN input");
        }

    }
}