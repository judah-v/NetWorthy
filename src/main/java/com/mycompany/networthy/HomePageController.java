package com.mycompany.networthy;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomePageController implements Initializable {
    @FXML
    private Button loadNetworkDataBtn;
     @FXML
    private TableView<MemberData> MemberTable;
    @FXML
    private TableColumn<MemberData,Integer> memberID;
    @FXML
    private TableColumn<MemberData,String> memberFirstName;
    @FXML
    private TableColumn<MemberData,String> memberLastName;
    @FXML
    private TableColumn<MemberData,String> memberEmail;
    @FXML
    private TableColumn<MemberData,String> memberLinkedin;
    @FXML
    private TableColumn<MemberData,String> memberPhone;
    private ObservableList<MemberData>data;
    @FXML
    private Label title;

    @FXML
    public void LoadNetworkData(ActionEvent event) throws SQLException{
    Connection con = DbConnection.Connection();
    data = FXCollections.observableArrayList();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM members");
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
    data.add(new MemberData(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("linkedin"),rs.getInt("phone")));
    }
            memberID.setCellValueFactory(new PropertyValueFactory<MemberData,Integer>("Id"));
            memberFirstName.setCellValueFactory(new PropertyValueFactory<MemberData,String>("FirstName"));
            memberLastName.setCellValueFactory(new PropertyValueFactory<MemberData,String>("LastName"));
            memberEmail.setCellValueFactory(new PropertyValueFactory<MemberData,String>("Email"));
            memberLinkedin.setCellValueFactory(new PropertyValueFactory<MemberData,String>("Linkedin"));
            memberPhone.setCellValueFactory(new PropertyValueFactory<MemberData,String>("Phone"));
            MemberTable.setItems(null);
            MemberTable.setItems(data);
            ps.close();
            rs.close();
            con.close();
    }
     @FXML
    public void AddMember(ActionEvent event) throws IOException{
    Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AddMember.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/AddMember.css");
        Image icon = new Image("/icons/adduser.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Add New Member");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void EditCustomerData(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/UpdateMember.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/EditMember.css");
        Image icon = new Image("/icons/edituser.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Edit Customer Page");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void DeleteCustomerData(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/DeleteMember.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/DeleteMember.css");
        Image icon = new Image("/icons/deleteuser.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Delete Customer Page");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
