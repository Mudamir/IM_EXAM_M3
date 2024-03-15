package com.example.im_exam_m3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class PaymentDetails {

    @FXML
    private Button btn_Update;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_save;

    @FXML
    private TableColumn<Payment, String> clm_Amount;

    @FXML
    private TableColumn<Payment, String> clm_ContractID;

    @FXML
    private TableColumn<Payment, String> clm_Date;

    @FXML
    private TableColumn<Payment, String> clm_ID;

    @FXML
    private TableColumn<Payment, String> clm_Mode;

    @FXML
    private TableColumn<Payment, String> clm_Type;

    @FXML
    private ComboBox<String> cmb_ContractID;

    @FXML
    private ComboBox<String> cmb_Mode;

    @FXML
    private ComboBox<String> cmb_Type;

    @FXML
    private DatePicker dp_Date;

    @FXML
    private TextField tb_Amount;

    @FXML
    private TableView<Payment> tbl_payments;

    @FXML
    void MovetoHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window
            Stage currentStage = (Stage) btn_home.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any errors loading the home page scene
        }
    }

    @FXML
    void SavetoDatabase(ActionEvent event) {

    }

    @FXML
    void UpdateTable(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        populateComboBox(cmb_ContractID, "SELECT Contract_ID FROM tbl_contract");
        populateComboBox(cmb_Mode, "SELECT Customer_Name FROM tbl_payment_mode");
        populateComboBox(cmb_Type, "SELECT Contract_type FROM tbl_contract_type");

        clm_ID.setCellValueFactory(new PropertyValueFactory<>("Contract_ID"));
        clm_ContractID.setCellValueFactory(new PropertyValueFactory<>("Room_ID"));
        clm_Type.setCellValueFactory(new PropertyValueFactory<>("Customer_name"));
        clm_Mode.setCellValueFactory(new PropertyValueFactory<>("Contract_type"));
        clm_Date.setCellValueFactory(new PropertyValueFactory<>("Date_start"));
        clm_Amount.setCellValueFactory(new PropertyValueFactory<>("Date_end"));


    }
    private void populateComboBox(ComboBox<String> comboBox, String query) {
        ObservableList<String> itemList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_im_finals", "root", "");
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                itemList.add(resultSet.getString(1));
            }
            comboBox.setItems(itemList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
