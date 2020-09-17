import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
        volumeSpinner.setEditable(false);

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
        if (fieldsAreFull())
        {
            HandCleaner hc = new HandCleaner(
                        brandComboBox.getValue(),
                        volumeSpinner.getValue(),
                        alcoholSlider.getValue(),
                        Double.parseDouble(priceTextField.getText()));

            objectLabel.setText(hc.toString());
        }
    }

    public boolean fieldsAreFull()
    {
        if (brandComboBox.getValue().isBlank())
            return false;

        if (priceTextField.getText().isBlank())
            return false;

        return true;
    }
}
