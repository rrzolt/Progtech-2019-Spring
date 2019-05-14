package Controller;
import Model.Statistics;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
public class LotteryStatistics extends Application {
    static boolean fullScreen =false;
    static Scene scene;
    static Statistics stat;

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.TRANSPARENT);
        scene = new Scene(loadFXML("HomeWindow.fxml"));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LotteryStatistics.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws Exception {
        SetUpDataForWork();
        launch();
    }
    private static void SetUpDataForWork() throws Exception {
        //Model.GetDataFromUrl.LoadSourceFromUrl();
        stat=new Statistics("Otoslotto_hetek.xml","https://bet.szerencsejatek.hu/jatekok/otoslotto/sorsolasok");
    }
}