/*Developed by Christian Zaccaria (20092351)
 * CA 1 - Rent-A-Home system
 */

package com.example.rentahome;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RentAHomeController implements Initializable {

    @FXML
    private ComboBox county;

    @FXML
    private ComboBox town;

    @FXML
    private ComboBox category;

    @FXML
    private ComboBox price;

    @FXML
    private TextArea propertiesTextArea;

    @FXML
    private Label feedback;

    RentAHome rentAHome;

    @FXML
    void onLoginButtonClick(ActionEvent event) throws IOException {

                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(RentAHomeMain.class.getResource("login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 640, 480);

                stage.setTitle("Login Page");
                stage.setScene(scene);
                stage.show();

    }

    @FXML
    protected void onClearButtonClick(ActionEvent event) {
        propertiesTextArea.clear();
        feedback.setText("List of properties has been cleared!");
    }

    @FXML
    void onDisplayAllButtonClick(ActionEvent event) {
        propertiesTextArea.setText(rentAHome.listAllProperties());
        feedback.setText("All available properties have been displayed above!");
    }


    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(RentAHomeMain.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        stage.setTitle("Register Page");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
   protected void onSearchButtonClick(ActionEvent event) {
        if (county.getValue().equals("County") ||
                town.getValue().equals("Town") ||
                category.getValue().equals("Category") ||
                price.getValue().equals("Price"))
        {
            feedback.setText("Please select county, town, category, and price");
        }
        else {

            propertiesTextArea.setText(rentAHome.listAllPropertiesSpecific((String) county.getValue(),
                    (String) town.getValue(), (String) category.getValue(), (String) price.getValue()));
            feedback.setText("Search is done!");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rentAHome = new RentAHome();

        ObservableList<String> categories = FXCollections.observableArrayList("Category","Detached","Semi-Detached","Terraced","Apartment");
        ObservableList<String> counties = FXCollections.observableArrayList("County","Waterford","Dublin","Cork");
        ObservableList<String> towns = FXCollections.observableArrayList("Town","Waterford","Dungarvan","Tramore","Sword","Lucan","Clondalkin","Cork","Midleton","Cobh");
        ObservableList<String> prices = FXCollections.observableArrayList("Price","50k - 100k","100k to 200k","200k to 300k","300k to 500k","500k to 1M");


        category.setItems(categories);
        county.setItems(counties);
        town.setItems(towns);
        price.setItems(prices);
        category.setValue("Category");
        county.setValue("County");
        town.setValue("Town");
        price.setValue("Price");



    }
}
