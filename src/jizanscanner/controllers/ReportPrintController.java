/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.controllers;

import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JTextField;
import jizanscanner.models.CustomeControls;
import jizanscanner.models.GlobalVariable;
import jizanscanner.models.GrnDetails;
import jizanscanner.models.GrnModal;
import jizanscanner.models.JasperReport;


/**
 * FXML Controller class
 *
 * @author santosh
 */

public class ReportPrintController implements Initializable {
 /**
 * Initializes the controller class.
 */   
    
    GrnModal gm =  new GrnModal();
    GlobalVariable gv = new GlobalVariable();
    private static ObservableList<GrnDetails> currObservable;
    JasperReport JR = new JasperReport();
    CustomeControls cc = new CustomeControls();
   
    @FXML
    public Button txtcheckby;
    @FXML
    public Button savebtn;
    @FXML
    public TextArea commenttxt;
    @FXML
    public Button printbtn;
    @FXML
    public TextField sealTX;
    @FXML
    public TextField shiptxt;
    @FXML
    public TextField counttxt;
    @FXML
    public TextField skidtxt;
    @FXML
    public TextField prtxt;
    @FXML
    public TextField wghtxt;
    @FXML
    public TextField ctntxt;
    @FXML
    public AnchorPane saveMsg;        
    @FXML
    public AnchorPane childanchor;
    
    Alert alertinfo = new Alert(Alert.AlertType.INFORMATION);
    
     @Override
     public void initialize(URL url, ResourceBundle rb) {
       
      //TODO
     String currDocnum = gv.getselDocnum();
     
     // System.out.println("this is cuudocnum"+currDocnum);
     
     if(currDocnum.length() > 0)
      {
        try {
             String fullstr = gm.getHeadComment(currDocnum);
             String strArray[] = fullstr.split(",");
             
              for(int i=0; i < strArray.length; i++){
                        System.out.println();
                }
            sealTX.setText(strArray[0]);
            shiptxt.setText(strArray[1]);
            counttxt.setText(strArray[2]);
            skidtxt.setText(strArray[3]);
            prtxt.setText(strArray[4]);
            wghtxt.setText(strArray[5]);
            ctntxt.setText(strArray[6]);
            commenttxt.setText(strArray[7]);
             
          }catch (Exception ex) {
             Logger.getLogger(ReportPrintController.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
     
     FxIconicsLabel saveIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_chrome_reader_mode)
              .size(18)
              .color(MaterialColor.BROWN_700)
              .build();
          savebtn.setGraphic(saveIcon);
          savebtn.setContentDisplay(ContentDisplay.LEFT);
            
     FxIconicsLabel prevIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_visibility)
              .size(18)
              .color(MaterialColor.BROWN_700)
              .build();
     printbtn.setGraphic(prevIcon);
     printbtn.setContentDisplay(ContentDisplay.LEFT);   
        
    } 
    
    @FXML
    public void saveComment() throws Exception
    {  
      String currDocnum = gv.getselDocnum();
      if(currDocnum.length() <= 0)
             {
        alertinfo.setTitle("Info");
        alertinfo.setHeaderText(null);
        alertinfo.setContentText("GRN number was not selected!!");
        alertinfo.showAndWait();
        return;
             }
        else  
             {  
        gm.saveHeadComment(currDocnum,sealTX.getText(),shiptxt.getText(),counttxt.getText(),skidtxt.getText(),prtxt.getText(),wghtxt.getText(),ctntxt.getText(),commenttxt.getText());
        saveMsg.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_info,MaterialColor.GREEN_900, "Sucessfully saved!"));   
             
             }  
    }

@FXML
public void printReport() throws Exception
  {  
    String currDocnum = gv.getselDocnum();
    
   
    if(currDocnum.substring(0, 3).equals("LOC")) 
         {
            currObservable = gv.getGrnObservTable();   
            
              
              ArrayList<String> grninfo = new ArrayList<String>();
              grninfo = gm.getGrnHeadInfo(currDocnum);
              String compname = grninfo.get(0);
              String suppinvc = grninfo.get(1);
              String ctntxt = grninfo.get(2);
              String grnnum = grninfo.get(3);
              String grndate = grninfo.get(4);        
              String recvdate = grninfo.get(5);         
              String supplier = grninfo.get(6);         
              String comment = grninfo.get(7); 
              
            
            JR.JasperGenerator(currObservable,compname,suppinvc,ctntxt,grnnum,grndate,recvdate,supplier,comment,txtcheckby.getText());
            gv.getReportStage().close();
         }         
         else if(currDocnum.substring(0, 3).equals("IMP"))     
         {  
                currObservable = gv.getGrnObservTable(); 
                //compname, grnnumber, grndate, recvdate,  prnumber, sealnumber, shipmode, countsize, skids, weight, comment
                ArrayList<String> grnimportinfo = new ArrayList<String>();
                grnimportinfo = gm.getGrnImportHeadInfo(currDocnum);
                 String compname = grnimportinfo.get(0);
                String grnnumber = grnimportinfo.get(1);
                String grndate = grnimportinfo.get(2);
                String recvdate = grnimportinfo.get(3);
                String prnumber = grnimportinfo.get(4);    
                String sealnumber = grnimportinfo.get(5);         
                String shipmode = grnimportinfo.get(6);         
                String countsize = grnimportinfo.get(7);
                String skids = grnimportinfo.get(8);         
                String weight = grnimportinfo.get(9);         
                String comment = grnimportinfo.get(10);
                
                JR.JasperGeneratorImportGrn(currObservable,compname, grnnumber,grndate,recvdate,recvdate,prnumber,sealnumber,shipmode,countsize,skids,weight,comment,txtcheckby.getText());       
                
                gv.getReportStage().close();
         }    
}

             /* this is test material */
         


    }
