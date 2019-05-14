package Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeWindow implements Initializable {

    double x,y;

    @FXML
    private BorderPane home5pane =null;

    @FXML
    private void Back(MouseEvent event) throws IOException { LoadPage("StartScreen.fxml"); }

    @FXML
    private void UserNumber(MouseEvent event) {
        LoadPage("UserNumber.fxml");
    }

    @FXML
    private void MostCommonPicks(MouseEvent event) {
        LoadPage("FiveMostCommonPicks.fxml");
    }

    @FXML
    private void CurrentAndPreviousPrizes(MouseEvent event) {
        LoadPage("CurrentAndPreviousPrizes.fxml");
    }

    @FXML
    private void WinningNumbers(MouseEvent event) {
        LoadPage("FiveMostCommonInFiveHits.fxml");
    }

    @FXML
    private void Close(MouseEvent event) {
        Stage stage= (Stage) home5pane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Minimize(MouseEvent event) {
        Stage stage= (Stage) home5pane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void FullScreen(MouseEvent event) {
        Stage stage=(Stage) home5pane.getScene().getWindow();
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        }
        else stage.setMaximized(true);
    }

    @FXML
    private void Pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }

    @FXML
    private void Dragged(MouseEvent event) {
        Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadPage("StartScreen.fxml");
    }

    private void LoadPage(String UI){
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource(UI));
        } catch (IOException e) {
            e.printStackTrace();
        }
        home5pane.setCenter(root);
    }
}
