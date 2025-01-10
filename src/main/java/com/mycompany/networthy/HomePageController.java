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
    private TableView<Member> MemberTable;
    @FXML
    private TableColumn<Member,Integer> memberID;
    @FXML
    private TableColumn<Member,String> memberFirstName;
    @FXML
    private TableColumn<Member,String> memberLastName;
    @FXML
    private TableColumn<Member,String> memberEmail;
    @FXML
    private TableColumn<Member,String> memberLinkedin;
    @FXML
    private TableColumn<Member,String> memberPhone;
    private ObservableList<Member>data;
    @FXML
    private Label title;

    @FXML
    public void LoadNetworkData(ActionEvent event) throws SQLException{
    Connection con = DbConnection.Connection();
    data = FXCollections.observableArrayList();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM members");
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
    data.add(new Member(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("linkedin"),rs.getString("phone")));
    }
            memberID.setCellValueFactory(new PropertyValueFactory<Member,Integer>("Id"));
            memberFirstName.setCellValueFactory(new PropertyValueFactory<Member,String>("FirstName"));
            memberLastName.setCellValueFactory(new PropertyValueFactory<Member,String>("LastName"));
            memberEmail.setCellValueFactory(new PropertyValueFactory<Member,String>("Email"));
            memberLinkedin.setCellValueFactory(new PropertyValueFactory<Member,String>("Linkedin"));
            memberPhone.setCellValueFactory(new PropertyValueFactory<Member,String>("Phone"));
            MemberTable.setItems(null);
            MemberTable.setItems(data);
            ps.close();
            rs.close();
            con.close();
            loadNetworkDataBtn.setText("REFRESH NETWORK");
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
    public void UpdateMemberInfo(ActionEvent event) throws IOException{
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
        stage.setTitle("Update Member Information");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void DeleteMember(ActionEvent event) throws IOException{
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
        stage.setTitle("Remove Member");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
