/*
MIT License
Copyright (c) 2018 Guru
Only for Educational purposes and for reference.
*/
package com.mycompany.networthy;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getStylesheets().add("/styles/HomePage.css");
        Image icon = new Image("/icons/HomePage.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("NetWorthy");
        stage.setScene(scene);
        stage.show();
    }


}
