package Controller;
import Model.Statistics;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StartScreen implements Initializable {
    @FXML
    private Text Jackpot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Jackpot.setText(Statistics.weekPrize());
    }
}
