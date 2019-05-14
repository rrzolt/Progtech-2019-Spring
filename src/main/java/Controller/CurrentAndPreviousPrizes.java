package Controller;

import Model.Statistics;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentAndPreviousPrizes implements Initializable {

    @FXML
    private LineChart chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series max=new XYChart.Series();
        max.setName("Eddigi legnagyobb nyeremény");
        XYChart.Series prizes=new XYChart.Series();
        prizes.setName("Legutóbbi 10 főnyeremény");
        XYChart.Series current=new XYChart.Series();
        current.setName("Jelenlegi főnyeremény");
        for(int i=0;i<11;i++) {
            max.getData().add(new XYChart.Data(String.valueOf(i + 1), Statistics.peviousWinningsAndCurrent().get(0)));
            prizes.getData().add(new XYChart.Data(String.valueOf(i + 1), Statistics.peviousWinningsAndCurrent().get(i+1)));
        }
        current.getData().add(new XYChart.Data(String.valueOf(11), Statistics.peviousWinningsAndCurrent().get(11)));
        chart.getData().addAll(max,prizes,current);
    }
}
