/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author santosh
 */
public class JizanScanner extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("GRN Scanner 1.0");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/appicon.png"));
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
