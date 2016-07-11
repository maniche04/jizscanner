/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.controllers;

import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jizanscanner.models.CustomeControls;
import jizanscanner.models.GrnModal;

/**
 * FXML Controller class
 *
 * @author santosh
 */
public class AddGRNController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   String currtext = "";
   GrnModal gm = new GrnModal();
   String companyname = "";
   String grnnum = "";
   CustomeControls cc = new CustomeControls();
   DateFormat yeartwodig = new SimpleDateFormat("yy"); // Just the year, with last 2 digits
   String formatyeartwodig = yeartwodig.format(Calendar.getInstance().getTime());
   
   DateFormat todayformate = new SimpleDateFormat("yyyy-MM-dd"); // Just the year, with last 2 digits
   String Today = todayformate.format(Calendar.getInstance().getTime());
   
   
   private final String pattern = "yyyy-MM-dd";
   
   
   String newean = "";
   @FXML
   public TextField isrtxt;
   public TextField prtxt;
   public TextField sealTX;
   public TextField shiptxt;
   public TextField counttxt;
   public TextField skidtxt;
   public TextField wghtxt;
   public TextField supinvctxt;
   public TextField ctntxt;
   public DatePicker recvdatetxt;
   public TextField suppliertxt;
   public Label grnddatetxt;
   public Button endgrnbtn;
   public Label grnlbl;
   public AnchorPane genError;
   public ComboBox doctype;
   public ComboBox compname;
   public TextField docinput;
   public Button docbtn;
   public Button btnclose;
   public Label grntxt;
   public Label btnsave;
   public Button btnSave;
           
Alert alert = new Alert(AlertType.CONFIRMATION);
Alert alertinfo = new Alert(AlertType.INFORMATION);


public AddGRNController() {
    this.gm = new GrnModal();
  }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //TODO
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = 
            DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };  
       
        recvdatetxt.setConverter(converter);
         
        doctype.getItems().clear();
        compname.getItems().clear();
        
        doctype.getItems().addAll(
                "ISR", 
                "LGR",
                "MTN",
                "AR",
                "Current"
            );
        
         compname.getItems().addAll(
                "JIZAN PERFUMES LLC",
                "INTERCITY PERFUMES LLC",
                "J P G TRADING LLC"
            );
        }
    
    
     @FXML
     public void valChange(){
      currtext =  doctype.getSelectionModel().getSelectedItem().toString().trim();
      System.out.println("this is text" + currtext);
     }
    
    
     @FXML
     public void checkValidDoc(){
        Pattern pattern;
        Matcher matcher;
        
      if(compname.equals("JIZAN PERFUMES LLC") || compname.equals("INTERCITY PERFUMES LLC") || compname.equals("J P G TRADING LLC")) 
        if(currtext.equals("Current"))
            {
              pattern  = Pattern.compile("\\d{4}");
              matcher = pattern.matcher(docinput.getText().trim());
              
              if (matcher.matches()) {
                   docbtn.setText("Start");
                   docbtn.setVisible(true);
                   genError.getChildren().clear();
                 }else
                 {
                   docbtn.setText("Start");
                   docbtn.setVisible(false);  
                   genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                 }   
            }else if(currtext.equals("LGR"))
            {
             System.out.println("button goes visible ******");
             pattern  = Pattern.compile("\\d{3}-\\d{2}");
             matcher = pattern.matcher(docinput.getText().trim());
                
                if (matcher.matches()) {
                docbtn.setText("Start");
                docbtn.setVisible(true);
                genError.getChildren().clear();
                }else
                {
                  docbtn.setText("Start");
                  docbtn.setVisible(false); 
                  genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                }
            }else if(currtext.equals("ISR"))
            {
                pattern  = Pattern.compile("\\d{4}-\\d{2}");
                matcher = pattern.matcher(docinput.getText().trim());
                
                if (matcher.matches()) {
                docbtn.setText("Start");
                docbtn.setVisible(true); 
                genError.getChildren().clear();
                }else
                {
                    docbtn.setText("Start");
                    docbtn.setVisible(false);
                    genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                }
            }
            else if(currtext.equals("MTN"))
            {
                pattern  = Pattern.compile("\\d{5}");
                matcher = pattern.matcher(docinput.getText().trim());
                
                if (matcher.matches()) {
                docbtn.setText("Start");
                docbtn.setVisible(true); 
                genError.getChildren().clear();
                }else
                {
                    docbtn.setText("Start");
                    docbtn.setVisible(false); 
                    genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                }
            }
            else if(currtext.equals("AR"))
            {
                pattern  = Pattern.compile("\\d{4}-\\d{2}");
                matcher = pattern.matcher(docinput.getText().trim());
                
                if (matcher.matches()) {
                docbtn.setText("Start");
                docbtn.setVisible(true);  
                genError.getChildren().clear();
                }
                else
                {
                docbtn.setText("Start");
                docbtn.setVisible(false);
                genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                } 
            } 
    }
    
    
    @FXML
    public void compSelected()
            {    
    companyname = compname.getSelectionModel().getSelectedItem().toString().trim();
            }
    
    
    @FXML
    public void genGRN() 
    {
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("A Confirmation Dialog");
alert.setContentText("Are you sure?");


      docinput.setEditable(false); //final grn no. created. so it goes final.
      if("Start".equals(docbtn.getText()))
        {
          if(docinput.getText().trim().length() >= 4)
            {   
              currtext = doctype.getSelectionModel().getSelectedItem().toString().trim();
              grnnum = docinput.getText().trim();
              
              try {
                  int matchnum = gm.checkdoctypestatus(currtext,grnnum);
                  if(matchnum == 3)
                    {        
                        if(!currtext.equals("Current"))
                                {
                             btnclose.setVisible(true);      
                                } 
                    gm.updatedocstat("Start",currtext);
                    }
                  
                else if (matchnum == 2)
                    {
                      if(!currtext.equals("Current"))
                                {
                                  btnclose.setVisible(true);      
                                }       
                       gm.updatedocstat("Break",currtext);
                    } 
              else if(matchnum == 0 )
                        {
                         if(!currtext.equals("Current"))
                            {
                           btnclose.setVisible(true);      
                            } 
                            gm.insertdocstat("Start",currtext,grnnum);
                        }
              else if (matchnum == 1)
                    {
                       
                    alertinfo.setTitle("Info");
                    alertinfo.setHeaderText(null);
                    alertinfo.setContentText("Doctype "+ currtext +" with docnum " +  grnnum + " already used !!");
                    alertinfo.showAndWait();
                    return;
                    
                    
                    }
   
                } catch (Exception ex) {
                    System.out.println("exception :" + ex);
                            
                }
 
        }

          /*Started - to check for grn proper number and auto increament*/
           
                            try
                            {
                                                             
                               if(currtext.equals("ISR") || currtext.equals("LGR") || currtext.equals("MTN") || currtext.equals("AR"))
                               {
                                  Optional<ButtonType> result = alert.showAndWait();
                                  if(result.get() == ButtonType.OK) { 
                                       
                                   } else
                                   {
                                     return;  
                                   }
                                   
                                int grnmatch = gm.checkGrnTypeStatus(currtext,grnnum);
                                  /*start - To update the grnnum*/
                                if(grnmatch == 0 || grnmatch == 1)
                                        {
                                   grnnum = gm.grnnum;
                                        }
                                /*start - To update the grnnum*/
                                
                                String getgrnnum="";
                                if(grnmatch == 0 ) // new start
                                {
                                    if(currtext.equals("ISR"))
                                    {
                                       String grnnew = String.valueOf(Integer.parseInt(grnnum)+1);
                                       getgrnnum = gm.grnDigit(grnnew);
                                       gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start"); 
                                       grntxt.setText("IMP. "+getgrnnum+" - "+ formatyeartwodig); 
                                    }
                                    else if(currtext.equals("Current"))
                                    {
                                     grntxt.setText("Non");
                                    }
                                    else
                                    {
                                        String grnnew = String.valueOf(Integer.parseInt(grnnum)+1);
                                        getgrnnum = gm.grnDigit(grnnew);
                                        gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start");                                      
                                        grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig); 
                                    }
                                    

                                 }

                                else if(grnmatch == 2) //break
                                     {
                                        if(currtext.equals("ISR"))
                                          {
                                               String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                               getgrnnum = gm.grnDigit(grnnew);
                                               gm.updategrndocstat(currtext,grnnum,getgrnnum,"Break");
                                               grntxt.setText("IMP. "+getgrnnum+" - "+formatyeartwodig);

                                          }
                                        else if(currtext.equals("Current"))
                                            {
                                                grntxt.setText("Non");
                                            }
                                        else 
                                          {
                                            String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                            getgrnnum = gm.grnDigit(grnnew);
                                            gm.updategrndocstat(currtext,grnnum,getgrnnum,"Break");
                                            grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);

                                          }
                                    }
                             
                                else if(grnmatch == 3) 
                                  { 
                                    //if windows close then it will remain //"start" status which should consider break status
                                      
                                  if(currtext.equals("ISR"))
                                        {
                                            String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                            System.out.println("test" + grnnew);
                                            getgrnnum = gm.grnDigit(grnnew);
                                            gm.updategrndocstat(currtext,grnnum,getgrnnum,"Start");
                                            grntxt.setText("IMP. "+getgrnnum+" - "+formatyeartwodig);
                                        }
                                  else if(currtext.equals("Current"))
                                    {
                                     grntxt.setText("Non");
                                    }
                                  else 
                                    {
                                      String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                      getgrnnum = gm.grnDigit(grnnew);
                                      gm.updategrndocstat(currtext,grnnum,getgrnnum,"Start");
                                      grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                                    }
  
                                }
                        
                                else if(grnmatch == 1) //close
                                {
                                  if(currtext.equals("ISR"))
                                        {
       
                                           String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                            getgrnnum = gm.grnDigit(grnnew);
                                            gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start");
                                            grntxt.setText("IMP. "+getgrnnum+" - "+formatyeartwodig); 
                                        
                                        }
                                  else if(currtext.equals("Current"))
                                    {
                                     grntxt.setText("Non");
                                    }
                                  else 
                                     {
                                            String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                            getgrnnum = gm.grnDigit(grnnew);
                                            gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start");
                                            grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                            
                                      }
                                  
 
                                    
                                    }
                          docbtn.setText("Break");
                         docbtn.setVisible(true);
                         //txtean.setEnabled(true);
                         doctype.setDisable(true);
                         docinput.setDisable(true);
                         grnlbl.setVisible(true);
                         grntxt.setVisible(true);
                         grnddatetxt.setText(Today);
                         recvdatetxt.setValue(LocalDate.parse(Today));
                         
                         
                         
                         
                               }
                                
                         else
                               {
                                   
                         docbtn.setText("Break");
                         docbtn.setVisible(true);
                         //txtean.setEnabled(true);
                         doctype.setVisible(false);
                         docinput.setVisible(false);
                         grnlbl.setVisible(false);
                         grntxt.setVisible(false);
                         grnddatetxt.setText(Today);
                         recvdatetxt.setValue(LocalDate.parse(Today));
                         
                               }

                   }catch (Exception ex) {
                System.out.println("exception :" + ex);
                }
                            
           if (currtext.equals("MTN") || currtext.equals("AR"))
                {
               endgrnbtn.setVisible(true);
               btnclose.setVisible(true); 
                }
           else  if (currtext.equals("ISR") || currtext.equals("LGR"))
               {     
             endgrnbtn.setVisible(true);
             btnclose.setVisible(true);                      
                }
           else if (currtext.equals("Current"))
                {
                endgrnbtn.setVisible(false);  
                btnclose.setVisible(false);
                }
           
           /* End - to check for grn proper number and auto increament */
        }   
       
else if("Break".equals(docbtn.getText()))
        { 
            String getgrnnum = "";
            newean = "";
            docbtn.setText("Start");
            docbtn.setDisable(false);
            endgrnbtn.setVisible(false);
            btnclose.setVisible(false);
            docinput.setText("");
            doctype.setDisable(false);
            docinput.setDisable(false);
            
            //txtean.setEnabled(false);
            //error.setVisible(false);
            //btnassign.setVisible(false);
            //jScrollPane2.setVisible(false);
            //toglectrorg.setVisible(false);
            
            if(currtext.equals("Current"))
            {
            
            }else
            {
              btnclose.setVisible(false);  
            }
            
     try {
          
          gm.updatedocstat("Break",currtext,grnnum);
          
          if(currtext.equals("ISR") || currtext.equals("LGR") || currtext.equals("MTN") || currtext.equals("AR"))
              {
              if(currtext.equals("ISR"))
                   {       
                    //5,9
                    String updategrnstat = grntxt.getText().trim().substring(5, 9);
                    //String grnnew = String.valueOf(Integer.parseInt(grnnum));
                    //getgrnnum = grnDigit(grnnew);
                    gm.updategrndocstat(currtext,grnnum,updategrnstat,"Break");
                    grntxt.setText("");
                    //grntxt.setText("IMP. "+getgrnnum+" - "+formatyeartwodig);
                    }
                      else if(currtext.equals("LGR"))
                        {
                            //String grnnew = String.valueOf(Integer.parseInt(grnnum));
                            String updategrnstat = grntxt.getText().trim().substring(5, 9);
                            //getgrnnum = grnDigit(grnnew);
                            gm.updategrndocstat(currtext,grnnum,updategrnstat,"Break");
                            grntxt.setText("");
                            //grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                            
                        }
                       else if(currtext.equals("MTN"))
                        {
                            //String grnnew = String.valueOf(Integer.parseInt(grnnum));
                            String updategrnstat = grntxt.getText().trim().substring(5, 9);
                            //getgrnnum = grnDigit(grnnew);
                            gm.updategrndocstat(currtext,grnnum,updategrnstat,"Break");
                            grntxt.setText("");
                            //grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                            
                        }
                        else if(currtext.equals("AR"))
                        {
                            //String grnnew = String.valueOf(Integer.parseInt(grnnum));
                            String updategrnstat = grntxt.getText().trim().substring(5, 9);
                            //getgrnnum = grnDigit(grnnew);
                            gm.updategrndocstat(currtext,grnnum,updategrnstat,"Break");
                            grntxt.setText("");
                            //grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                            
                        }
                      else
                        {
                           grntxt.setText("");
                        }
               }
               else{
                 grntxt.setText("");
               }
           } catch (Exception ex) {
               //Logger.getLogger(EanScanned.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("exception :" + ex);
           }
            
        }
    }
    
    
    
    @FXML
    public void saveHead() throws Exception
    {
        String compcode = "";
        
        if(compname.equals("JIZAN PERFUMES LLC"))
        {
          
            compcode = "10";
          
        }else if (compname.equals("INTERCITY PERFUMES LLC"))
        {
            
            compcode = "50";
            
        }else
        {
            
            compcode = "20";
            
        }
        
        
        gm.saveGrnHead(grntxt.getText(),compcode,companyname,suppliertxt.getText(),supinvctxt.getText(),ctntxt.getText(),grntxt.getText(),recvdatetxt.getValue().toString(),isrtxt.getText(),prtxt.getText(),sealTX.getText(),shiptxt.getText(),counttxt.getText(),skidtxt.getText(),wghtxt.getText());
        //Its search and now windows should goes closed
        
        Stage stage = (Stage)btnSave.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/jizanscanner/views/scannerGrnSetup.fxml"));     
        Parent root = (Parent)fxmlLoader.load();          
        ScannerGrnSetupController controller = fxmlLoader.<ScannerGrnSetupController>getController();
        controller.defaultGrnList();
        Scene scene = new Scene(root); 
        stage.setScene(scene);    
        stage.show();
        
    }
    
    
}


