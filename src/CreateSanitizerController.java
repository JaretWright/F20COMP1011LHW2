import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateSanitizerController implements Initializable {

    @FXML
    private ComboBox<String> brandComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brandComboBox.getItems().addAll("Koala Care","Early Start Safety","SQL Elixer");
    }
}
