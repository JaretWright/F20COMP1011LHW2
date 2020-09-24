import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BarChartController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis monthAxis;

    @FXML
    private NumberAxis temperatureAxis;

    private XYChart.Series<String, Integer> temperatureSeries;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temperatureSeries = new XYChart.Series<>();
        barChart.setTitle("High Temperatures");
        monthAxis.setLabel("Months");
        temperatureAxis.setLabel("Temperature");

//        "January" -> -3
//        "February" -> -5
        temperatureSeries.getData().add(new XYChart.Data<>("January", -4));
        temperatureSeries.getData().add(new XYChart.Data<>("February", -5));
        temperatureSeries.getData().add(new XYChart.Data<>("March", 8));
        temperatureSeries.getData().add(new XYChart.Data<>("April", 20));
        temperatureSeries.getData().add(new XYChart.Data<>("May", 28));
        temperatureSeries.getData().add(new XYChart.Data<>("June", 32));
        temperatureSeries.getData().add(new XYChart.Data<>("July", 36));
        temperatureSeries.getData().add(new XYChart.Data<>("August", 34));
        temperatureSeries.getData().add(new XYChart.Data<>("September", 24));
        temperatureSeries.getData().add(new XYChart.Data<>("October", 18));
        temperatureSeries.getData().add(new XYChart.Data<>("November", 2));
        temperatureSeries.getData().add(new XYChart.Data<>("December", -3));

        barChart.getData().addAll(temperatureSeries);
    }

    public void changeToSanitizerScene(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("createSanitizerView.fxml"));
////        Scene scene = new Scene(root);
////        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
////        stage.setScene(scene);
////        stage.setTitle("Create Hand Sanitizer");
////        stage.show();
        SceneChanger.changeScene(event,"createSanitizerView.fxml","Create Hand Sanitizer");
    }
}
