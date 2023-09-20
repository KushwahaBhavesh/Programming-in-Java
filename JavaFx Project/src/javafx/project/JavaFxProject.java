
package javafx.project;

import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author Bhavesh kushwaha
 */
public class JavaFxProject extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Scene sc = new Scene(grid, 500, 800);

        primaryStage.setTitle("Registration Form");
        primaryStage.setScene(sc);
        primaryStage.show();

        grid.setHgap(16);
        grid.setVgap(18);
        grid.setPadding(new Insets(1, 2, 1, 2));

        //ui Component
        //Name 
        Label fname = new Label("First Name");
        grid.add(fname, 1, 0);

        TextField first = new TextField();
        grid.add(first, 2, 0);

        Label mname = new Label("Middle Name");
        grid.add(mname, 1, 1);

        TextField Middle = new TextField();
        grid.add(Middle, 2, 1);

        Label lname = new Label("Last Name");
        grid.add(lname, 1, 2);

        TextField last = new TextField();
        grid.add(last, 2, 2);

        //email
        Label email = new Label("Email:");
        grid.add(email, 1, 3);

        TextField txtemail = new TextField();
        grid.add(txtemail, 2, 3);

        //phone Number
        Label phone = new Label("Phone Number");
        grid.add(phone, 1, 4);

        TextField txtphone = new TextField();
        grid.add(txtphone, 2, 4);

        //combo box
        Label lbl_dob = new Label("Date of Birth ");
        grid.add(lbl_dob, 1, 5);

        HBox dob = new HBox(6);
        grid.add(dob, 2, 5);

        ComboBox<Integer> day = new ComboBox<Integer>();
        day.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        day.setPromptText("Day");

        ComboBox<String> month = new ComboBox<String>();
        month.getItems().addAll("JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY", "AUG", "SEP", "OCT", "NOV", "DEC");
        month.setPromptText("Month");

        ComboBox<Integer> year = new ComboBox<Integer>();
        year.getItems().addAll(2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001, 2000);
        year.setPromptText("Year");

        dob.getChildren().addAll(day, month, year);

        //Radio button
        Label gender = new Label("Gender");
        grid.add(gender, 1, 6);

        HBox radiogender = new HBox(10);
        grid.add(radiogender, 2, 6);

        ToggleGroup group = new ToggleGroup();
        RadioButton male = new RadioButton("male");
        male.setToggleGroup(group);

        RadioButton female = new RadioButton("Female");
        female.setToggleGroup(group);

        radiogender.getChildren().addAll(male, female);

        //CheckBox
        HBox lang = new HBox(10);
        grid.add(lang, 2, 7);
        Label Languages = new Label("Languages");
        grid.add(Languages, 1, 7);

        CheckBox hindi = new CheckBox("Hindi");
        CheckBox gujarati = new CheckBox("Gujarati");
        CheckBox english = new CheckBox("English");

        lang.getChildren().addAll(hindi, gujarati, english);

        //Button
        HBox btnpane = new HBox(12);
        grid.add(btnpane, 2, 9);
        Button Submit = new Button("Submit");
        Button cancle = new Button("Cancle");

        btnpane.getChildren().addAll(Submit, cancle);

        HBox nbh = new HBox();

        //cancle Button Event
        cancle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                first.clear();
                Middle.clear();
                last.clear();
                txtemail.clear();
                txtphone.clear();
                day.setValue(1);
                month.setValue("01");
                year.setValue(2022);
                male.setSelected(false);
                female.setSelected(false);
                hindi.setSelected(false);
                gujarati.setSelected(false);
                english.setSelected(false);
            }

        });

        //submit Button Event
        Submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (first.getText().trim().isEmpty() || Middle.getText().trim().isEmpty() || last.getText().trim().isEmpty() || txtemail.getText().trim().isEmpty() || txtphone.getText().trim().isEmpty() || !(hindi.isSelected() || gujarati.isSelected() || english.isSelected()) || group.getSelectedToggle() == null) {
                    Alert a1 = new Alert(Alert.AlertType.ERROR, "Empty Fields not Allowed");
                    a1.setHeaderText("Unable to submit");
                    a1.showAndWait();
                } else if (!isValid(txtemail.getText().trim())) {
                    Alert a1 = new Alert(Alert.AlertType.ERROR, "Invalid Email");
                    a1.setHeaderText("Enter valid Email");
                    a1.showAndWait();
                } else if (txtphone.getText().length() < 9) {
                    Alert a1 = new Alert(Alert.AlertType.ERROR, "Invalid PhoneNumber");
                    a1.setHeaderText("Enter valid Phone Number");
                    a1.showAndWait();
                } else {
                    Scene newScene = new Scene(nbh, 500, 500);
                    Label welcome = new Label("Welcome to New Scene");
                    nbh.setAlignment(Pos.CENTER);
                    nbh.getChildren().addAll(welcome);
                    primaryStage.setScene(newScene);
                    primaryStage.show();
                }

            }
        });

        //validating Phone number
        txtphone.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (txtphone.getText().length() > 9 && (event.getCode() != KeyCode.BACK_SPACE)) {
                    Alert a1 = new Alert(Alert.AlertType.ERROR, "Invalid PhoneNumber");
                    a1.setHeaderText("Enter 10 digit Phone Number");
                    a1.showAndWait();
                }
            }
        });

        //validating email 
        email.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!isValid(txtemail.getText().trim())) {
                    txtemail.setStyle("-fx-text-inner-color:#ff0000");
                } else {
                    txtemail.setStyle("-fx-text-inner-color:#000000");
                }
            }

        });

    }

    //isValid Function
    public static boolean isValid(String e) {
        String emailReg = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9_+&*-]+\\.)+[a-zA-Z]{2,7}+$";
        Pattern pattern = Pattern.compile(emailReg);
        if (e == null) {
            return false;
        } else {
            return pattern.matcher(e).matches();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
