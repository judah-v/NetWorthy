package com.mycompany.networthy;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddMemberController implements Initializable {
    @FXML
    private Label display;
    @FXML
    private TextField memberFirstName;
    @FXML
    private TextField memberLastName;

    @FXML
    private TextField memberEmail;
    @FXML
    private TextField memberLinkedin;
    @FXML
    private TextField memberPhone;
    @FXML
    public void AddMember(ActionEvent event) throws SQLException, FileNotFoundException {
        if (memberFirstName.getText().isEmpty() || memberLastName.getText().isEmpty() || memberEmail.getText().isEmpty() || memberLinkedin.getText().isEmpty()) {
            display.setText("Please Complete All Fields");
        } else {
            Connection con = DbConnection.Connection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO members VALUES (NULL,?,?,?,?,?)");
            ps.setString(1, memberFirstName.getText());
            ps.setString(2, memberLastName.getText());
            ps.setString(3, memberEmail.getText());
            ps.setString(4, memberLinkedin.getText());
            ps.setString(5, memberPhone.getText());
            int i = ps.executeUpdate();
            if (i > 0) {
                memberFirstName.setText("");
                memberLastName.setText("");
                memberEmail.setText("");
                memberLinkedin.setText("");
                memberPhone.setText("");
                display.setText("New Customer Added Successfully");
                memberEmail.getScene().getWindow().hide();

            } else {
                display.setText("Failed To Add New Customer");
            }
            ps.close();
            con.close();

        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
