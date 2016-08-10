
package jizanscanner.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jizanscanner.models.GlobalVariable;

/**
 * FXML Controller class
 *
 * @author santosh
 */
public class PictureViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    GlobalVariable gv = new GlobalVariable();
    
    @FXML
    public ImageView imgview;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       String currimage = gv.getCurrImage();
       Image image = new Image(currimage);
       imgview.setFitHeight(image.getHeight());
       imgview.setFitWidth(image.getHeight());
               
       imgview.setImage(image);
       //qtytxt.requestFocus();
        
        
    }    
    
}
