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
import jizanscanner.models.UserIntraVerification;

/**
 * FXML Controller class
 *
 * @author santosh
 */


public class LoginController implements Initializable {
   UserIntraVerification pp = new UserIntraVerification();
    
    
    @FXML
    public TextField loginuser;

    @FXML
    public PasswordField loginpass;

    @FXML
    public Button loginbtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            

            System.out.println(loginuser);
        
    }
    
    @FXML
    public void tryLogin() {
        try{
        pp.sendPost();
        
        if (gv.getPassFail() == 1){   
            EanHome eanscannerobj =  new EanHome();
            eanscannerobj.setVisible(true);
            this.setVisible(false);
            infouserType();
              } 
        else
            {
            JOptionPane.showMessageDialog(this, "Invalid UserName/Password!");  
            }
        }catch (Exception ex) {
            Logger.getLogger(WelcomeLoginScreen.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
}
