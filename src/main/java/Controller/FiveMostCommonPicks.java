package Controller;
import Model.Statistics;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class FiveMostCommonPicks implements Initializable {

    @FXML
    private Text number1;
    @FXML
    private Text number2;
    @FXML
    private Text number3;
    @FXML
    private Text number4;
    @FXML
    private Text number5;
    @FXML
    private Text numberOfTimesPicked1;
    @FXML
    private Text numberOfTimesPicked2;
    @FXML
    private Text numberOfTimesPicked3;
    @FXML
    private Text numberOfTimesPicked4;
    @FXML
    private Text numberOfTimesPicked5;
    @FXML
    private Text lastTimePicked1;
    @FXML
    private Text lastTimePicked2;
    @FXML
    private Text lastTimePicked3;
    @FXML
    private Text lastTimePicked4;
    @FXML
    private Text lastTimePicked5;
    @FXML
    private Text numberOfBeingInFiveHits1;
    @FXML
    private Text numberOfBeingInFiveHits2;
    @FXML
    private Text numberOfBeingInFiveHits3;
    @FXML
    private Text numberOfBeingInFiveHits4;
    @FXML
    private Text numberOfBeingInFiveHits5;
    @FXML
    private Text numberPercentage1;
    @FXML
    private Text numberPercentage2;
    @FXML
    private Text numberPercentage3;
    @FXML
    private Text numberPercentage4;
    @FXML
    private Text numberPercentage5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTexts(Statistics.fiveMostCommon().get(0),number1,numberOfTimesPicked1,lastTimePicked1,numberOfBeingInFiveHits1,numberPercentage1);
        setTexts(Statistics.fiveMostCommon().get(1),number2,numberOfTimesPicked2,lastTimePicked2,numberOfBeingInFiveHits2,numberPercentage2);
        setTexts(Statistics.fiveMostCommon().get(2),number3,numberOfTimesPicked3,lastTimePicked3,numberOfBeingInFiveHits3,numberPercentage3);
        setTexts(Statistics.fiveMostCommon().get(3),number4,numberOfTimesPicked4,lastTimePicked4,numberOfBeingInFiveHits4,numberPercentage4);
        setTexts(Statistics.fiveMostCommon().get(4),number5,numberOfTimesPicked5,lastTimePicked5,numberOfBeingInFiveHits5,numberPercentage5);
    }
    private void setTexts(int sourceNumber,Text number, Text numberOfTimesPicked, Text lastTimePicked,Text numberOfBeingInFiveHits, Text numberPercentage) {

            number.setText(sourceNumber+"");
            numberOfTimesPicked.setText("Ez a szám eddig "+Statistics.numberOfTimesPicked(sourceNumber).toString()+" alkalommal került kihúzásra,");
            lastTimePicked.setText("legutóbb "+Statistics.lastTimePicked(sourceNumber));
            numberOfBeingInFiveHits.setText("Eddig "+Statistics.numberOfBeingInFiveHits(sourceNumber)+" alkalommal szerepelt 5 találatos szelvényen,");
            numberPercentage.setText("és "+String.format("%.4f",Statistics.numberPercentage(sourceNumber))+" % az esélye, hogy kihúzásra kerül.");
    }
}
