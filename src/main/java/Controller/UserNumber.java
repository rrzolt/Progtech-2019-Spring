package Controller;

import Model.Statistics;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class UserNumber {

    @FXML
    private TextField number;
    @FXML
    private Text numberOfTimesPicked;
    @FXML
    private Text numberPercentage;
    @FXML
    private Text numberOfBeingInFiveHits;
    @FXML
    private Text lastTimePicked;

    @FXML
    private void Ok(MouseEvent event){
        int sourceNumber;
        try {
            sourceNumber=Integer.parseInt(number.getText());
            if(sourceNumber>0&&sourceNumber<91) {
                numberOfTimesPicked.setText("Ez a szám eddig " + Statistics.numberOfTimesPicked(sourceNumber).toString() + " alkalommal került kihúzásra,");
                lastTimePicked.setText("legutóbb " + Statistics.lastTimePicked(sourceNumber));
                numberOfBeingInFiveHits.setText("Eddig " + Statistics.numberOfBeingInFiveHits(sourceNumber) + " alkalommal szerepelt 5 találatos szelvényen,");
                numberPercentage.setText("és " + String.format("%.4f", Statistics.numberPercentage(sourceNumber)) + " % az esélye, hogy kihúzásra kerül.");
            }
            else{
                numberOfTimesPicked.setText("NEM MEGFELELŐ SZÁM");
                lastTimePicked.setText("");
                numberOfBeingInFiveHits.setText("");
                numberPercentage.setText("");
            }
            } catch (NumberFormatException e) {
            numberOfTimesPicked.setText("NEM SZÁM");
            lastTimePicked.setText("");
            numberOfBeingInFiveHits.setText("");
            numberPercentage.setText("");
        }
    }
}
