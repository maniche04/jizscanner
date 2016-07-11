/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import jizanscanner.models.CustomeControls;
import jizanscanner.models.GlobalVariable;
import jizanscanner.models.UserIntraVerification;
import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import java.awt.Window;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author santosh
 */


public class LoginController implements Initializable {
    
   UserIntraVerification pp = new UserIntraVerification();
   GlobalVariable gv = new GlobalVariable();
   CustomeControls cc = new CustomeControls();
   
    
    @FXML
    public TextField loginuser;

    @FXML
    public PasswordField loginpass;

    @FXML
    public Button loginbtn;
    
    @FXML
    public AnchorPane loginerrors;
 
    
    /**
     * Initializes the controller class.
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        
        System.out.println(loginuser);
        
    }
    
    @FXML
    public void tryLogin() throws Exception {
        gv.setGlobusername(loginuser.getText());
        gv.setGlobpassword(loginpass.getText());    
        pp.sendPost(); 
        if (gv.getPassFail() == 1){ 
            
    //EanHome eanscannerobj =  new EanHome();
    //Eanscannerobj.setVisible(true);
    //This.setVisible(false);
    //InfouserType();
            
        System.out.println("login sucessfull :)");
        
        secondScreen();
        Stage stage = (Stage)loginbtn.getScene().getWindow();
        stage.close();
        
            } 
       else
           {      
        loginerrors.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid Login Credentials!"));   
           }
         }
   
    
    
        public void secondScreen() throws IOException
            {
           Parent root = FXMLLoader.load(getClass().getResource("/jizanscanner/views/scannerGrnSetup.fxml"));
           Scene nscene = new Scene(root);
           Stage tStatge = new Stage();
           tStatge.setScene(nscene);
           tStatge.setMaximized(true);
           tStatge.setTitle("Jizan Scanner 0.1");
           tStatge.show();   
           
           
            }
   
    
    
     }
    
