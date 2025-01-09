package com.mycompany.networthy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class UpdateMemberController implements Initializable {
    @FXML
    private Label display;
    @FXML
    private TextField memberID;
    @FXML
    private Button load;
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
    public void loadMemberInfo(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        try {
            Connection con = DbConnection.Connection();
            PreparedStatement ps = con.prepareStatement("Select * from members where id = ?");
            ps.setInt(1, Integer.parseInt(memberID.getText()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                memberFirstName.setText(rs.getString("first_name"));
                memberLastName.setText(rs.getString("last_name"));
                memberEmail.setText(rs.getString("email"));
                memberLinkedin.setText(rs.getString("linkedin"));
                memberPhone.setText(Integer.toString(rs.getInt("phone")));
            } else {
                display.setText("Customer Not Found");
            }
            ps.close();
            rs.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            display.setText("Please Enter The ID Correctly");
        }
    }
    public void EditMemberInfo(ActionEvent event) throws SQLException, FileNotFoundException {
        if (memberFirstName.getText().isEmpty() || memberLastName.getText().isEmpty()) {
            display.setText("Please Add Complete Name");
        } else {
            Connection con = DbConnection.Connection();
            PreparedStatement ps = con.prepareStatement("UPDATE members SET first_name = ? ,last_name = ? ,email = ? , linkedin = ?, phone = ? WHERE id = '" + Integer.parseInt(memberID.getText()) + "'");
            ps.setString(1, memberFirstName.getText());
            ps.setString(2, memberLastName.getText());
            ps.setString(3, memberEmail.getText());
            ps.setString(4, memberLinkedin.getText());
            ps.setInt(5, Integer.parseInt(memberPhone.getText()));

            int i = ps.executeUpdate();
            if (i > 0) {
                memberFirstName.setText("");
                memberLastName.setText("");
                memberEmail.setText("");
                memberLinkedin.setText("");
                memberPhone.setText("");
                display.setText("Member Info Updated Successfully");
            } else {
                display.setText("Failed To Update Customer Info");
            }
            ps.close();
            con.close();

            }
    }
    public void DeleteMember(ActionEvent event) throws SQLException {
        try {
            Connection con = DbConnection.Connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM members WHERE id = ?");
            ps.setInt(1, Integer.parseInt(memberID.getText()));
            int k = ps.executeUpdate();
            if (k > 0) {
                memberFirstName.setText("");
                memberLastName.setText("");
                memberEmail.setText("");
                memberLinkedin.setText("");
                memberPhone.setText("");
                display.setText("Successfully Removed The Customer");
            } else {
                display.setText("Failed To Find The Customer");
            }
            ps.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            display.setText("Please Enter The ID Correctly");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
