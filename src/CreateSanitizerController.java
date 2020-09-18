import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateSanitizerController implements Initializable {

    @FXML
    private ComboBox<String> brandComboBox;

    @FXML
    private Spinner<Integer> volumeSpinner;

    @FXML
    private Slider alcoholSlider;

    @FXML
    private Label alcoholLabel;

    @FXML
    private Label objectLabel;

    @FXML
    private TextField priceTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //configure the ComboBox
        brandComboBox.getItems().addAll("Koala Care","Early Start Safety","SQL Elixer");
        brandComboBox.setPromptText("Select Brand");

        //configure the Spinner
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 30);
        volumeSpinner.setValueFactory(valueFactory);
        volumeSpinner.setEditable(true);

        //try to capture the user input prior to commit
        TextField editor = volumeSpinner.getEditor();
        editor.textProperty().addListener((observable, ov, nv)->{
            objectLabel.setText("");
            try{
                String text = editor.getText();
                if (!text.isBlank())
                    Integer.parseInt(editor.getText());
            } catch (NumberFormatException e)
            {
                objectLabel.setTextFill(Color.RED);
                objectLabel.setText("Volume must be a whole number.");
            }
        });

        //configure the slider
        alcoholSlider.setMin(30);
        alcoholSlider.setMax(100);
        alcoholSlider.setValue(80);
        alcoholLabel.setText(String.format("%.1f%%",alcoholSlider.getValue()));

        //add a listener to the slider
//        alcoholSlider.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue,
//                                Number oldValue, Number new_value) {
//                alcoholLabel.setText(String.format("%.1f%%",new_value));
//            }
//        });

        //add a listener using a lambda
        alcoholSlider.valueProperty().addListener(
                (observableValue, old_value, new_value)->
                        alcoholLabel.setText(String.format("%.1f%%", new_value)));

        objectLabel.setText("");
    }

    public void createObject()
    {
        objectLabel.setTextFill(Color.BLACK);
        objectLabel.setText("");
        if (fieldsAreFull())
        {
            try {
                HandCleaner hc = new HandCleaner(
                        brandComboBox.getValue(),
                        volumeSpinner.getValue(),
                        alcoholSlider.getValue(),
                        Double.parseDouble(priceTextField.getText()));

                objectLabel.setText(hc.toString());
            } catch (IllegalArgumentException e)
            {
                objectLabel.setText(e.getMessage());
            }

        }
    }

    public boolean fieldsAreFull()
    {
        if (brandComboBox.getValue() == null || brandComboBox.getValue().isBlank())
        {
            objectLabel.setText("You must select a Brand");
            return false;
        }


        if (priceTextField.getText().isBlank())
        {
            objectLabel.setText("You must enter a price");
            return false;
        }


        return true;
    }
}
