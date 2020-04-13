package sample;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class FormField {
    private String label;
    private int maxLength = 250;
    private TextField txtField = new TextField();
    private boolean onlyNumber = false;

    public FormField(String label, int maxLength) {
        this.label = label;
        this.maxLength = maxLength;
    }

    public FormField(String label, int maxLength, boolean onlyNumber) {
        this(label, maxLength);
        this.onlyNumber = onlyNumber;
    }

    public void setFieldText(String str) {
        this.txtField.setText(str);
    }

    public Node getNode() {
        HBox formContainer = new HBox();
        formContainer.setPadding(new Insets(10));
        Text label = new Text();
        label.setFill(Color.WHITE);
        label.setText(this.label);
        this.txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(this.onlyNumber && newValue.length() > 0) {
                boolean digit = Character.isDigit(newValue.charAt(newValue.length() - 1));
                if(digit){
                    this.txtField.setText(newValue);
                } else if (!digit){
                    this.txtField.setText(oldValue);
                }
            }
        });
        formContainer.getChildren().addAll(label, txtField);
        return formContainer;
    }

    public String getEnteredText() {
        return this.txtField.getText();
    }
}
