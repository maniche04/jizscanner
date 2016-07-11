/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial.Icons;
import java.awt.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author santosh
 */
public class CustomeControls {
    
        public AnchorPane messageBox(Icons icontype,String thiscolor, String message )
            {
                AnchorPane msgbox = new AnchorPane();
                
                msgbox.setCenterShape(true);
                
                HBox container = new HBox();
                Label messagebox = new Label("  "+message);
                                              
                FxIconicsLabel labelDefault = (FxIconicsLabel) new FxIconicsLabel.Builder(icontype)
                .size(24)
                .color(thiscolor)
                .build();
                
                container.getChildren().add(labelDefault);
                container.getChildren().add(messagebox);
                msgbox.getChildren().add(container);
                return msgbox;
            }
}
