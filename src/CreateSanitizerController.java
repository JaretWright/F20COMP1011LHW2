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

    @FXML
    private RadioButton plasticRadioButton;

    @FXML
    private RadioButton glassRadioButton;

    private ToggleGroup materialToggleGroup;
    private ToggleGroup ratingToggleGroup;

    @FXML
    private RadioButton oneStarRadioButton;

    @FXML
    private RadioButton twoStarRadioButton;

    @FXML
    private RadioButton threeStarRadioButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //configure the material RadioButton's
        materialToggleGroup = new ToggleGroup();
        glassRadioButton.setToggleGroup(materialToggleGroup);
        plasticRadioButton.setToggleGroup(materialToggleGroup);
        plasticRadioButton.setSelected(true);

        ratingToggleGroup = new ToggleGroup();
        oneStarRadioButton.setToggleGroup(ratingToggleGroup);
        twoStarRadioButton.setToggleGroup(ratingToggleGroup);
        threeStarRadioButton.setToggleGroup(ratingToggleGroup);
        threeStarRadioButton.setSelected(true);

        //configure the ComboBox
        brandComboBox.getItems().addAll("Koala Care","Early Start Safety","SQL Elixer");
        brandComboBox.setPromptText("Select Brand");

        //configure the Spinner
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 30);
        volumeSpinner.setValueFactory(valueFactory);
        volumeSpinner.setEditable(true);

//        SpinnerChangeListener scl = new SpinnerChangeListener();
        TextField spinnerTextField = volumeSpinner.getEditor();
        spinnerTextField.textProperty().addListener((observableValue,oldValue,newValue)->
        {
            objectLabel.setText("");
            if (!newValue.isBlank())
            {
                try {
                    Integer.parseInt(newValue);
                }catch (NumberFormatException e)
                {
                    spinnerTextField.setText(oldValue);
                    objectLabel.setTextFill(Color.RED);
                    objectLabel.setText("only whole numbers allowed for volume in ml");
                }
            }
        });
//        spinnerTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue,
//                                String oldValue, String newValue) {
//                objectLabel.setText("");
//                if (!newValue.isBlank())
//                {
//                    try {
//                        Integer.parseInt(newValue);
//                    }catch (NumberFormatException e)
//                    {
//                        spinnerTextField.setText(oldValue);
//                        objectLabel.setTextFill(Color.RED);
//                        objectLabel.setText("only whole numbers allowed for volume in ml");
//                    }
//                }
//            }
//        });


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
