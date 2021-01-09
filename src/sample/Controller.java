package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private JFXTextField binaryField;

    @FXML
    private JFXTextField hexField;

    @FXML
    private JFXButton convertButton;

    @FXML
    private JFXButton clearButton;
    @FXML
    private Label errorLabel;


    private String getHexChar(int hexCharValue){
        if(hexCharValue<=9){
            return String.valueOf(hexCharValue);
        }else{
            switch (hexCharValue) {
                case 10:
                    return "A";
                case 11:
                    return "B";
                case 12:
                    return "C";
                case 13:
                    return "D";
                case 14:
                    return "E";
                default:
                    return "F";

            }
        }

    }

    private String convertToHex(int integerValue){

        StringBuilder primaryHexString= new StringBuilder();
        while(integerValue != 0){

            int hexCharValue = integerValue%16;
            primaryHexString.append(getHexChar(hexCharValue));
            integerValue = integerValue/16;

        }
        return new StringBuilder(primaryHexString.toString()).reverse().toString();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    hexField.setEditable(false);

    //Action for convert button
        convertButton.setDefaultButton(true);
    convertButton.setOnAction(event -> {
        if(!binaryField.getText().isEmpty()){ //check if input field is not empty
            String binaryString = binaryField.getText();
            errorLabel.setText("");
            try { //try to Convert binary code to Integer Value
               int intValue = Integer.parseInt(binaryString,2);
               String hexString = convertToHex(intValue);
                hexField.setText("0x" + hexString);
           }catch (Exception e){ // returns we cant convert to Integer (if not valid binary string)
                errorLabel.setText("Wrong Format of binary code");
           }
        }else{
                errorLabel.setText("please Enter a binary code");
        }
    });
    clearButton.setOnAction(event -> {
        binaryField.clear();
        hexField.clear();
        errorLabel.setText("");
    });
    }
}
