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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import static jizanscanner.controllers.ScannerGrnSetupController.allRow;

import jizanscanner.models.CustomeControls;
import jizanscanner.models.GlobalVariable;
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
     @FXML
   private ScannerGrnSetupController parentController;
               
        String currtext = "";
        String comptext = "";
        GrnModal gm = new GrnModal();
        String companyname = "";
        String grnnum = "";
        CustomeControls cc = new CustomeControls();
        DateFormat yeartwodig = new SimpleDateFormat("yy"); // Just the year, with last 2 digits
        String formatyeartwodig = yeartwodig.format(Calendar.getInstance().getTime());
        DateFormat todayformate = new SimpleDateFormat("yyyy-MM-dd"); // Just the year, with last 2 digits
        String Today = todayformate.format(Calendar.getInstance().getTime());
        GlobalVariable gv = new GlobalVariable();    
        private final String pattern = "yyyy-MM-dd";   
        String newean = "";
   
   @FXML
   public AnchorPane child; 
   
   @FXML
   public AnchorPane msgDisp;
   
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
   public Label grnlbl;
   public AnchorPane genError;
   public ComboBox doctype;
   public ComboBox compname;
   public TextField docinput;
   public Label grntxt;
   public Label btnsave;
   public Button btnSave;
   public Button btncheck;
   public String lastgrn;
           
   Alert alert = new Alert(AlertType.CONFIRMATION);
   Alert alertinfo = new Alert(AlertType.INFORMATION);
   
 
   

@Override
public void initialize(URL url, ResourceBundle rb) {
docinput.setVisible(false);
btncheck.setVisible(false); 

     
            
         
     

         RotateTransition rotation = new RotateTransition(Duration.seconds(0.5), btncheck);
         rotation.setCycleCount(Animation.INDEFINITE);
         rotation.setByAngle(360);
         btncheck.setOnMouseEntered(e -> rotation.play());
         btncheck.setOnMouseExited(e -> rotation.pause());

         btnSave.setStyle("-fx-font: 18 Ubuntu; -fx-base: #76B40A;");
         btnSave.setVisible(false);
     
         StringConverter converter = new StringConverter<LocalDate>() {
         DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        
     
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
        
        FxIconicsLabel saveGrnIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_save)
                .size(16)
                .color(MaterialColor.BROWN_700)
                .build();
        btnSave.setGraphic(saveGrnIcon);
        btnSave.setContentDisplay(ContentDisplay.LEFT);     
        
        }
         
     
         
    
     @FXML
     public void valChange(){
     currtext =  doctype.getSelectionModel().getSelectedItem().toString().trim();
     System.out.println("this is text" + currtext);
     if(currtext.length() > 1 && comptext.length() > 1)
           {
             docinput.setVisible(true);
             checkValidDoc();
           }
     }
     
     @FXML
     public void compSelected() {
       comptext =  compname.getSelectionModel().getSelectedItem().toString().trim();
      System.out.println("this is text" + comptext); 
      if(currtext.length() > 1 && comptext.length() > 1)
      {
         docinput.setVisible(true);
         checkValidDoc();
      }
     }
    
    
     @FXML
     public void checkValidDoc(){
        
         Pattern pattern;
         Matcher matcher;
         System.out.println("Checking Document Values");
         System.out.println("Company is: " + comptext);
         System.out.println("Current DocType is: " + currtext);
         
         if(comptext.equals("JIZAN PERFUMES LLC") || comptext.equals("INTERCITY PERFUMES LLC") || comptext.equals("J P G TRADING LLC")) 
         if(currtext.equals("Current"))
            {
              pattern  = Pattern.compile("\\d{4}");
              matcher = pattern.matcher(docinput.getText().trim());
              
              if (matcher.matches()) {
                   genError.getChildren().clear();
                   btncheck.setVisible(true);
                   btncheck.setVisible(true);
                 }else
                 {
                   genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                   btncheck.setVisible(false);
                 }   
            }else if(currtext.equals("LGR"))
            {
                
             System.out.println("Currently LGR");
             System.out.println("button goes visible ******");
             pattern  = Pattern.compile("\\d{3}-\\d{2}");
             matcher = pattern.matcher(docinput.getText().trim());
                
              if (matcher.matches()) {
                genError.getChildren().clear();
                   btncheck.setVisible(true);
                    btncheck.setVisible(true);
              }else
              {

                    
               genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
               btncheck.setVisible(false);
                    
            
              }
            }
            else if(currtext.equals("ISR"))
            {
                System.out.println("Currently ISR");
                pattern  = Pattern.compile("\\d{4}-\\d{2}");
                matcher = pattern.matcher(docinput.getText().trim());
                
                if (matcher.matches()) {
                genError.getChildren().clear();
                   btncheck.setVisible(true);
                   btncheck.setVisible(true);
                }else
                {

                    genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                    btncheck.setVisible(false);
                }
            }
            else if(currtext.equals("MTN"))
            {
                pattern  = Pattern.compile("\\d{5}");
                matcher =  pattern.matcher(docinput.getText().trim());
                
                if (matcher.matches()) {
                    
                    genError.getChildren().clear();
                    btncheck.setVisible(true);
                   
                }else
                 {    
                     
                    genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                    btncheck.setVisible(false);
                        
                 }
            }
            else if(currtext.equals("AR"))
            {
                pattern  = Pattern.compile("\\d{4}-\\d{2}");
                matcher = pattern.matcher(docinput.getText().trim());
                
                if (matcher.matches()) { 
                genError.getChildren().clear();
                btncheck.setVisible(true);
                }
                else
                {
                    
                  genError.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Invalid input!"));   
                  btncheck.setVisible(false); 
                
                } 
            } 
         }
    
     
         /*  */
 @FXML
 public void checkact()
  {
   if("Check".equals(btncheck.getText()))
     {
      if(docinput.getText().trim().length() >= 4)
         {   
           currtext = doctype.getSelectionModel().getSelectedItem().toString().trim();
           grnnum = docinput.getText().trim();
       
              try{
                  int matchnum = gm.checkdoctypestatus(currtext,grnnum);
                  if(matchnum == 3)
                    {        
                     if(!currtext.equals("Current"))
                            {
                                
                               // btnclose.setVisible(true);   
                                /*  */
                            } 
                        //gm.updatedocstat("Start",currtext);
                         docinput.setEditable(false);
                    }
                  
                else if (matchnum == 2)
                     {
                      if(!currtext.equals("Current"))
                                {
                                 // btnclose.setVisible(true);      
                                }       
                         /* gm.updatedocstat("Break",currtext); */
                        /* this is test material name */
                     } 
              else if(matchnum == 0 )
                        {
                         if(!currtext.equals("Current"))
                            {
                          //btnclose.setVisible(true);      
                            } 
                            //gm.insertdocstat("Start",currtext,grnnum);
                        }
              else if (matchnum == 1)
                    {
                  DialogPane dialogPane1 = alertinfo.getDialogPane();
                  dialogPane1.getStylesheets().add(
                  getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
                          
                 alertinfo.setTitle("Info");
                 alertinfo.setHeaderText(null);
                 alertinfo.setContentText("Document type "+ currtext +" with document number " +  grnnum + " has already been used.");
                 alertinfo.showAndWait();
                 return;
                    }
                } catch (Exception ex) {
                    System.out.println("exception :" + ex);
                }
        }

                    /* Started - to check for grn proper number and auto increament */
                            try
                            {
                                                             
                               if(currtext.equals("ISR") || currtext.equals("LGR") || currtext.equals("MTN") || currtext.equals("AR"))
                               {
                                  Optional<ButtonType> result = alert.showAndWait();
                                  
                                  if(result.get() == ButtonType.OK) { 
                                       
                                   }else
                                   {
                                     return;  
                                   }
                                   
                                 int grnmatch = gm.checkGrnTypeStatus(currtext,grnnum,formatyeartwodig);
                                 
                                 System.out.println("status of doc is "+ grnmatch +"other var are"+currtext+"var"+grnnum);
                                 
                                  /*start - To update the grnnum*/ 
                                 
                                 if(grnmatch == 0 || grnmatch == 1)
                                        {
                                            
                                   grnnum = gm.grnnum;
                                   
                                        }
                                 
                                 
                                 /* start - To update the grnnum */ 
                                 
                                 /* start - To update the grnnum */
                                
                                 String getgrnnum="";
                                 
                                 if(grnmatch == 0 ) // new start
                                  {
                                   if(currtext.equals("ISR"))
                                    {
                                       String grnnew = String.valueOf(Integer.parseInt(grnnum)+1);
                                       getgrnnum = gm.grnDigit(grnnew);
                                       //gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start"); 
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
                                      //gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start");                                      
                                      grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                                    }
                            
                                 }

                                else if(grnmatch == 2) //break
                                     {
                                        if(currtext.equals("ISR"))
                                          {
                                               String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                               getgrnnum = gm.grnDigit(grnnew);
                                               //gm.updategrndocstat(currtext,grnnum,getgrnnum,"Break");
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
                                            //gm.updategrndocstat(currtext,grnnum,getgrnnum,"Break");
                                            grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);

                                          }
                                    }
                             
                                else if(grnmatch == 3) 
                                  { 
                                            docinput.setEditable(false);
                                    //if windows close then it will remain //"start" status which should consider break status
                                      
                                  if(currtext.equals("ISR"))
                                        {
                                            String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                            System.out.println("test" + grnnew);
                                            getgrnnum = gm.grnDigit(grnnew);
                                            //gm.updategrndocstat(currtext,grnnum,getgrnnum,"Start");
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
                                      //gm.updategrndocstat(currtext,grnnum,getgrnnum,"Start");
                                      grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                                      docinput.setEditable(false);
                                    }
                                }
       
                                else if(grnmatch == 1) //close
                                {
                                  if(currtext.equals("ISR"))
                                         {
                                           String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                           getgrnnum = gm.grnDigit(grnnew);
                                           //gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start");
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
                                            //gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start");
                                            grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                            
                                      }
                                    
                                    }
                         /*  */
                                 
                         btncheck.setVisible(false);
                         docinput.setDisable(true);
                         grnlbl.setVisible(true);
                         grntxt.setVisible(true);
                         grnddatetxt.setText(Today);
                         recvdatetxt.setValue(LocalDate.parse(Today));
                            
                               }
                         else
                               {
                                   
                         //docbtn.setText("Break");
//                         docbtn.setDisable(true);
                        
                         //txtean.setEnabled(true);
                         
                         doctype.setVisible(false);
                         docinput.setVisible(false);
                         btncheck.setVisible(false);
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
               //endgrnbtn.setVisible(true);
               //btnclose.setVisible(true); 
                }
           else  if (currtext.equals("ISR") || currtext.equals("LGR"))
               {     
             //endgrnbtn.setVisible(true);
             //btnclose.setVisible(true);                      
                }
           else if (currtext.equals("Current"))
                {
                //endgrnbtn.setVisible(false);  
                //btnclose.setVisible(false);
                }
           
           /* End - to check for grn proper number and auto increament */
        }
                
    }
    
    public void newGenGRN()
    {
        
       DialogPane dialogPane1 = alert.getDialogPane();
       dialogPane1.getStylesheets().add(
                        getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
       alert.setTitle("Confirmation Needed");
       alert.setContentText("Are you  sure you ");
       
        
    }
            
   

     public void genGRN() 
     {
      DialogPane dialogPane1 = alert.getDialogPane();
      dialogPane1.getStylesheets().add(
                        getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
      alert.setTitle("Confirmation Required");
//    alert.setHeaderText("A Confirmation Dialog");
      alert.setContentText("Are you sure you want to continue with " + currtext + ". " + docinput.getText() + " ?");


 //final grn no. created. so it goes final.
      if("Save".equals(btnSave.getText()))
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
                             //btnclose.setVisible(true);      
                                } 
                    gm.updatedocstat("Start",currtext);
                          docinput.setEditable(false);
                    }
                  
                else if (matchnum == 2)
                     {
                      if(!currtext.equals("Current"))
                                {
                                 // btnclose.setVisible(true);      
                                }       
                       gm.updatedocstat("Break",currtext);
                     } 
              else if(matchnum == 0 )
                        {
                         if(!currtext.equals("Current"))
                            {
                          //btnclose.setVisible(true);      
                            } 
                            gm.insertdocstat("Start",currtext,grnnum);
                        }
              else if (matchnum == 1)
                    {
                   DialogPane dialogPane2 = alertinfo.getDialogPane();
                  dialogPane2.getStylesheets().add(
                  getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());     
                 
                 alertinfo.setTitle("Info");
                 alertinfo.setHeaderText(null);
                 alertinfo.setContentText("Document type "+ currtext +" with document number " +  grnnum + " has already been used.");
                 alertinfo.showAndWait();
                 return;
                    }
                } catch (Exception ex) {
                    System.out.println("exception :" + ex);
                }
 
        }

             /*  Started - to check for grn proper number and auto increament */
           
                            try
                            {
                                                             
                               if(currtext.equals("ISR") || currtext.equals("LGR") || currtext.equals("MTN") || currtext.equals("AR"))
                               {
                                  Optional<ButtonType> result = alert.showAndWait();
                                  
                                  if(result.get() == ButtonType.OK) { 
                                       
                                   }else
                                   {
                                     return;  
                                   }
                                   
                                 int grnmatch = gm.checkGrnTypeStatus(currtext,grnnum,formatyeartwodig);
                                 
                                 System.out.println("status of doc is "+ grnmatch +"other var are"+currtext+"var"+grnnum);
                                 
                                  /*start - To update the grnnum*/ 
                                 
                                 if(grnmatch == 0 || grnmatch == 1)
                                        {
                                   lastgrn = gm.grnnum;
                                   System.out.println("Last GRN Number is " + lastgrn);
                                        }
                                 
                                 /* start - To update the grnnum */
                                      
                                 /* start - To update the grnnum */
                                
                                 String getgrnnum="";
                                 if(grnmatch == 0 ) // new start
                                  {
                                   if(currtext.equals("ISR"))
                                    {
                                       String grnnew = String.valueOf(Integer.parseInt(lastgrn)+1);
                                       getgrnnum = gm.grnDigit(grnnew);
                                       gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start","IMP"); 
                                       grntxt.setText("IMP. "+getgrnnum+" - "+ formatyeartwodig); 
                                    }
                                    else if(currtext.equals("Current"))
                                    {
                                     grntxt.setText("Non");
                                    }
                                    else
                                    {
                                        String grnnew = String.valueOf(Integer.parseInt(lastgrn)+1);
                                        getgrnnum = gm.grnDigit(grnnew);
                                        gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start","LOC");                                      
                                        grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                                    }
                            
                                 }

                                else if(grnmatch == 2) //break
                                     {
                                        if(currtext.equals("ISR"))
                                          {
                                               String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                               getgrnnum = gm.grnDigit(grnnew);
                                               gm.updategrndocstat(currtext,grnnum,getgrnnum,"Break", formatyeartwodig);
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
                                            gm.updategrndocstat(currtext,grnnum,getgrnnum,"Break",formatyeartwodig);
                                            grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);

                                          }
                                    }
                             
                                else if(grnmatch == 3) 
                                  { 
                                            docinput.setEditable(false);
                                    //if windows close then it will remain //"start" status which should consider break status
                                      
                                  if(currtext.equals("ISR"))
                                        {
                                            String grnnew = String.valueOf(Integer.parseInt(grnnum));
                                            System.out.println("test" + grnnew);
                                            getgrnnum = gm.grnDigit(grnnew);
                                            gm.updategrndocstat(currtext,grnnum,getgrnnum,"Start",formatyeartwodig);
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
                                      gm.updategrndocstat(currtext,grnnum,getgrnnum,"Start",formatyeartwodig);
                                      grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                                            docinput.setEditable(false);
                                    }
  
                                }
                        
                                else if(grnmatch == 1) //close
                                {
                                    System.out.println("Now trying to Enter!!");
                                  if(currtext.equals("ISR"))
                                        {
       
                                           String grnnew = String.valueOf(Integer.parseInt(grnnum)+1);
                                            getgrnnum = gm.grnDigit(grnnew);
                                            gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start","IMP");
                                            grntxt.setText("IMP. "+getgrnnum+" - "+formatyeartwodig); 
                                        
                                        }
                                  else if(currtext.equals("Current"))
                                    {
                                     grntxt.setText("Non");
                                    }
                                  else 
                                     {
                                            String grnnew = String.valueOf(Integer.parseInt(grnnum)+1);
                                            getgrnnum = gm.grnDigit(grnnew);
                                            gm.grnNewSerialNumber(currtext,grnnum,getgrnnum,"Start","LOC");
                                            grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
                            
                                      }
                                  
 
                                    
                                    }
                          
                         docinput.setDisable(true);
                         grnlbl.setVisible(true);
                         grntxt.setVisible(true);
                         grnddatetxt.setText(Today);
                         recvdatetxt.setValue(LocalDate.parse(Today));
                         
                         
                         
                         
                               }
                                
                         else
                               {
                                   

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
               //endgrnbtn.setVisible(true);
               //btnclose.setVisible(true); 
                }
           else  if (currtext.equals("ISR") || currtext.equals("LGR"))
               {     
             //endgrnbtn.setVisible(true);
             //btnclose.setVisible(true);                      
                }
           else if (currtext.equals("Current"))
                {
                //endgrnbtn.setVisible(false);  
                //btnclose.setVisible(false);
                }
           
           /* End - to check for grn proper number and auto increament */
        }   
     /*commented boz no break login is there*/  
//else if("Break".equals(btnSave.getText()))
//        { 
//            String getgrnnum = "";
//            newean = "";
//            //docbtn.setText("Start");
//            //docbtn.setDisable(false);
//            //endgrnbtn.setVisible(false);
//            //btnclose.setVisible(false);
//            docinput.setText("");
//            doctype.setDisable(false);
//            docinput.setDisable(false);
//            
//            //txtean.setEnabled(false);
//            //error.setVisible(false);
//            //btnassign.setVisible(false);
//            //jScrollPane2.setVisible(false);
//            //toglectrorg.setVisible(false);
//            
//            if(currtext.equals("Current"))
//            {
//            
//            }else
//            {
//              //btnclose.setVisible(false);  
//            }
//            
//     try {
//          
//          gm.updatedocstat("Break",currtext,grnnum);
//          
//          if(currtext.equals("ISR") || currtext.equals("LGR") || currtext.equals("MTN") || currtext.equals("AR"))
//              {
//              if(currtext.equals("ISR"))
//                   {       
//                    //5,9
//                    String updategrnstat = grntxt.getText().trim().substring(5, 9);
//                    //String grnnew = String.valueOf(Integer.parseInt(grnnum));
//                    //getgrnnum = grnDigit(grnnew);
//                    gm.updategrndocstat(currtext,grnnum,updategrnstat,"Break");
//                    grntxt.setText("");
//                    //grntxt.setText("IMP. "+getgrnnum+" - "+formatyeartwodig);
//                    }
//                      else if(currtext.equals("LGR"))
//                        {
//                            //String grnnew = String.valueOf(Integer.parseInt(grnnum));
//                            String updategrnstat = grntxt.getText().trim().substring(5, 9);
//                            //getgrnnum = grnDigit(grnnew);
//                            gm.updategrndocstat(currtext,grnnum,updategrnstat,"Break");
//                            grntxt.setText("");
//                            //grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
//                            
//                        }
//                       else if(currtext.equals("MTN"))
//                        {
//                            //String grnnew = String.valueOf(Integer.parseInt(grnnum));
//                            String updategrnstat = grntxt.getText().trim().substring(5, 9);
//                            //getgrnnum = grnDigit(grnnew);
//                            gm.updategrndocstat(currtext,grnnum,updategrnstat,"Break");
//                            grntxt.setText("");
//                            //grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
//                            
//                        }
//                        else if(currtext.equals("AR"))
//                        {
//                            //String grnnew = String.valueOf(Integer.parseInt(grnnum));
//                            String updategrnstat = grntxt.getText().trim().substring(5, 9);
//                            //getgrnnum = grnDigit(grnnew);
//                            gm.updategrndocstat(currtext,grnnum,updategrnstat,"Break");
//                            grntxt.setText("");
//                            //grntxt.setText("LOC. "+getgrnnum+" - "+formatyeartwodig);
//                            
//                        }
//                      else
//                        {
//                           grntxt.setText("");
//                        }
//               }
//               else{
//                 grntxt.setText("");
//               }
//           } catch (Exception ex) {
//               //Logger.getLogger(EanScanned.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("exception :" + ex);
//           }
//            
//        }
      
      /*commented boz no break login is there*/
    }
    
    @FXML 
    public void headInputValidation() {
        if ( !(grntxt.getText().equals("Waiting...")) && supinvctxt.getText().length() > 0 && suppliertxt.getText().length() > 0 && recvdatetxt.getValue().lengthOfYear() > 0) {
            btnSave.setVisible(true);
        } else {
            btnSave.setVisible(false);
        }
    }
    
    @FXML
    public void saveHead() throws Exception
    {
        String compcode = "";
        
        if(comptext.equals("JIZAN PERFUMES LLC"))
        {
          
          compcode = "10";
        
        }else if (comptext.equals("INTERCITY PERFUMES LLC"))
        {
          compcode = "50";
            
        }else
        {
          compcode = "20";    
        }
              
genGRN();
int pass = gm.saveGrnHead(grntxt.getText(),compcode,comptext,suppliertxt.getText(),supinvctxt.getText(),ctntxt.getText(),grnddatetxt.getText(),recvdatetxt.getValue().toString(),isrtxt.getText(),prtxt.getText(),sealTX.getText(),shiptxt.getText(),counttxt.getText(),skidtxt.getText(),wghtxt.getText());

     //Its search and now windows should goes closed
        
         if (pass == 1)
         {
         btnSave.setDisable(true);
         btnSave.setStyle("-fx-font: 18 arial; -fx-base: #D3D3D3;");
         msgDisp.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_info,MaterialColor.GREEN_400, "Added sucessfully!"));   
         }else
         {
          msgDisp.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Sorry,Not added!"));      
         }
        
    }
    
     /*
    
     msgDisp.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning,MaterialColor.RED_900, "Sorry,Not added!"));
     btnSave.setDisable(true);
    
    
     */
    
 
    
}


