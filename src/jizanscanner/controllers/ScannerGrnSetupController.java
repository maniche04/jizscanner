/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.controllers;

import com.lowagie.text.Anchor;
import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import jizanscanner.helper.AutoCompleteDBLink;
import jizanscanner.helper.AutoTextComplete;
import jizanscanner.models.AutoCompleteComboBoxListener;
import jizanscanner.models.Canias;
import jizanscanner.models.GlobalVariable;
import jizanscanner.models.Grn;
import jizanscanner.models.GrnDetails;
import jizanscanner.models.GrnModal;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.json.JSONObject;
import jizanscanner.helper.AutoCompleteDBLink;
import jizanscanner.helper.AutoTextComplete;
import jizanscanner.models.DbConnection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView.ResizeFeatures;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import jizanscanner.helper.GUIUtils;
import jizanscanner.models.CustomeControls;
import jizanscanner.models.EditingCell;
import org.json.JSONException;


/**
* @author SANTOSH JAISWAL
*/


public class ScannerGrnSetupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    /*start - current selected row variable decleration*/
    String stat = "";
    String crrmat = "";
    String crrdesc = "";
    String crrgood = "";
    String crrbox = "";
    String crrleak = "";
    String crrbreak = "";
    String crrqty = "";
    String crrwght = "";
    String crrcntry = "";
    String crrean = "";
    String crrremark = "";
    String crrlot = "";
    String crrdoctype = "";
    String crrdocnum = "";
    String crrgrnnum = "";
    String crrgid = "";
    boolean click = false;
    /*end - current selected row variable decleration*/
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    Alert alertinfo = new Alert(Alert.AlertType.INFORMATION);

    AutoCompleteDBLink autocdblink;
    JComponent matcomponent = null;
    DbConnection dbconn = new DbConnection();
    Connection connekt = null;
    Canias cs = new Canias();
    GrnModal gm = new GrnModal();
    GlobalVariable gv = new GlobalVariable();
    private static ObservableList<String> col;
    public static ObservableList<Grn> allRow;
    String colHeading[] = {"Supplier", "Docnum", "Date"};
    String colGrnHeading[] = {"Id", "Material", "Desc", "Good", "Box", "Leak", "Break", "Qty", "Contry", "EAN", "Weight",
        "Remark", "LotNo", "Doctype", "Docnum", "GRNnum", "Docstatus", "GRNstatus"};

    private static JSONObject jObject = null;
    public GrnDetails blankGrn = new GrnDetails();
    private final String pattern = "yyyy-MM-dd";
    String datValue = "";
    public static ObservableList<String> grlcol;
    public static ObservableList<GrnDetails> allGrnRow;
    CustomeControls cc = new CustomeControls();

    TableColumn colGood = new TableColumn();
    TableColumn colBox = new TableColumn();
    TableColumn colLeak = new TableColumn();
    TableColumn colBreaken = new TableColumn();
    TableColumn colRemark = new TableColumn();
    TableColumn colLotno = new TableColumn();

    JTextField jtext = new JTextField("", 25);
    
    
    
    @FXML
    public Label compmsg;
    @FXML
    public Button addgrnbtn;
    @FXML
    public Button repbtn;
    @FXML
    public CheckBox isSearchAll;
    @FXML
    public Label thisGrnNum;
    @FXML
    public ImageView jizanlogo;
    @FXML
    public Button showhide;
    @FXML
    public AnchorPane leftanchor;
    @FXML
    public VBox rightVbox;
    @FXML
    public AnchorPane showbox;
    @FXML
    public VBox leftVbox;
    @FXML
    public BorderPane showBorder;
    @FXML
    public SplitPane showSplit;
    @FXML
    public Button btnclose;
    @FXML
    public Button endgrnbtn;
    @FXML
    public Button del;
    @FXML
    public AnchorPane errDisp;
    @FXML
    public TextField scammattxt;
    @FXML
    public Button addbtn;
    @FXML
    public TableView tbldata;
    @FXML
    public DatePicker checkInDatePicker;
    @FXML
    private Button viewbtn;
    @FXML
    private TextField filterGRN;
    @FXML
    public TableView grnviewtbl;
    @FXML
    public CheckBox editflag;
    @FXML
    public Label checklabel;
    @FXML
    public TextField mattxt;
    @FXML
    public TextField destxt;
    @FXML
    public TextField desctxt;
    @FXML
    public TextField codetxt;
    @FXML
    public TextField qtytxt;
    @FXML
    public TextField contrytxt;
    @FXML
    public TextField wghttxt;
    @FXML
    public ImageView imagevw;
    @FXML
    public CustomTextField searchMatTxt;
    @FXML
    public SwingNode swingnode;
    @FXML
    public AnchorPane scanAnchor;
    @FXML
    public Button viewtbn;
    @FXML
    public ImageView appbanner;
    @FXML
    public Button synbtn;
    @FXML
    public ProgressBar progressBr;
    @FXML
    public AnchorPane mainAnchor;
    
    private int isedit;
    private String currSelGrn;
    private String currSelGrnStat;
    int currow = 0;

     DateFormat todayformate = new SimpleDateFormat("yyyy-MM-dd"); // Just the year, with last 2 digits
     String Today = todayformate.format(Calendar.getInstance().getTime());

      //Canias cs = new Canias();
    
     FxIconicsLabel rightIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_fast_forward)
                .size(18)
                .color(MaterialColor.BROWN_700)
                .build();
     
     FxIconicsLabel leftIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_fast_rewind)
                .size(18)
                .color(MaterialColor.BROWN_700)
                .build();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jizanlogo.setImage(new Image("/images/jiz3dbanner.png"));
        appbanner.setImage(new Image("/images/applogo.png"));

        progressBr.setVisible(false);
        /* Start - to set edit button disable and scan part invisible at start */
        viewtbn.setVisible(true);
        scanAnchor.setVisible(false);
        scanAnchor.setManaged(false);

        jtext.setBorder(null);
        Font segoef = new Font("Segoe UI", Font.PLAIN, 14);
        jtext.setFont(segoef);
        
        FxIconicsLabel addIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_add_circle_outline)
                .size(16)
                .color(MaterialColor.BROWN_700)
                .build();
        addbtn.setGraphic(addIcon);
        addbtn.setContentDisplay(ContentDisplay.LEFT);
        
        FxIconicsLabel editIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_create)
                .size(18)
                .color(MaterialColor.BROWN_700)
                .build();
        viewtbn.setGraphic(editIcon);
        viewtbn.setContentDisplay(ContentDisplay.LEFT);

        FxIconicsLabel reportIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_picture_as_pdf)
                .size(18)
                .color(MaterialColor.BROWN_700)
                .build();
       repbtn.setGraphic(reportIcon);
       repbtn.setContentDisplay(ContentDisplay.LEFT);
       
       FxIconicsLabel deleteIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_delete)
                .size(18)
                .color(MaterialColor.BROWN_700)
                .build();
        del.setGraphic(deleteIcon);
        del.setContentDisplay(ContentDisplay.LEFT);
        
        
            showhide.setGraphic(rightIcon);
            showhide.setContentDisplay(ContentDisplay.LEFT);
            
        FxIconicsLabel addGrnIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_queue)
                .size(16)
                .color(MaterialColor.BROWN_700)
                .build();
        addgrnbtn.setGraphic(addGrnIcon);
        addgrnbtn.setContentDisplay(ContentDisplay.LEFT);            
        
        
        FxIconicsLabel endGrnIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_do_not_disturb_alt)
                .size(18)
                .color(MaterialColor.BROWN_700)
                .build();
        btnclose.setGraphic(endGrnIcon);
        btnclose.setContentDisplay(ContentDisplay.LEFT);
        
         FxIconicsLabel syncIcon = (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_sync)
                .size(18)
                .color(MaterialColor.BROWN_700)
                .build();
        synbtn.setGraphic(syncIcon);
        synbtn.setContentDisplay(ContentDisplay.LEFT);
        
         /* End -  to set edit button disable and scan part invisible at start */
         StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter
                    = DateTimeFormatter.ofPattern(pattern);

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

        
         imagevw.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
                    try {
                        System.out.println("The screen show here");
                        Parent root = FXMLLoader.load(getClass().getResource("/jizanscanner/views/PictureView.fxml"));
                        Scene nscene = new Scene(root);

                        Stage tStatge = new Stage();
                        tStatge.getIcons().add(new Image("/images/appicon.png"));
                        tStatge.setTitle("Picture View");
        
                        tStatge.initModality(Modality.APPLICATION_MODAL);
                        tStatge.setScene(nscene);
                        tStatge.show();
        
                    } catch (IOException ex){
                        Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
         });
         
        
         
        
            
         checkInDatePicker.setConverter(converter);
         col = FXCollections.observableArrayList(colHeading);

             /* */
         
             jtext.addKeyListener(new KeyListener() {
             @Override
             public void keyTyped(java.awt.event.KeyEvent e) {
             }

             @Override
             public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    if (!jtext.getText().isEmpty()) {
                        desctxt.setText(jtext.getText());
                        /* */
                        Set set2 = gv.getMyMap().entrySet();
                        Iterator iterator2 = set2.iterator();
                        while (iterator2.hasNext()) {
                            Map.Entry mentry2 = (Map.Entry) iterator2.next();
                            if (mentry2.getValue().toString().equals(desctxt.getText())) {

                                codetxt.setText(mentry2.getKey().toString().trim());
                                desctxt.setEditable(false);
                                codetxt.setEditable(false);

                                String matcode = null;
                                try {
                                    matcode = cs.getMaterialFromCode(codetxt.getText());
                                } catch (Exception ex) {
                                    Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (!matcode.equals("-1")) {
                                    try {
                                        JSONObject matdata = new JSONObject(matcode);
                                        String wght = (String) matdata.get("NETWEIGHT");

                                        wghttxt.setText(wght);

                                        if (wght.length() > 1) {
                                            wghttxt.setEditable(false);
                                        } else {
                                            wghttxt.setEditable(true);
                                        }
                                    } catch (JSONException ex) {
                                        Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                     /* 
                                    
                                     */
                                     
                                    
                                  }

                                     //URL url = new URL(maturl.getImage(mentry2.getKey().toString().trim()));
                                     //BufferedImage image = ImageIO.read(url); 
                                     //imglbl.setText("<html><img width='200' height='200' src='" + maturl.getImage(mentry2.getKey().toString().trim()) + "'</img></html>");
                                     //imglbl.setIcon(new javax.swing.ImageIcon(maturl.getImage(mentry2.getKey().toString().trim())));
                                     //System.out.println("url of pic...."+maturl.getImage(mentry2.getKey().toString().trim()));
                                
                            }
                        }
                    }
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (jtext.getText().isEmpty()) {
                    jtext.setText("");

                }
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE || evt.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT || evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT || evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN || evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP || evt.getKeyCode() == java.awt.event.KeyEvent.VK_TAB) {

                } else {
                    System.out.println("testtttt.........." + jtext.getText());
                    try {
                        getMatName(jtext.getText());
                    } catch (Exception ex) {
                        Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        TableColumn<Grn, String> colSupplier = new TableColumn<Grn, String>(col.get(0));
//colSupplier.setMinWidth(120);
        colSupplier.setCellValueFactory(new PropertyValueFactory<Grn, String>("supplier"));

        TableColumn<Grn, String> colDocnum = new TableColumn<Grn, String>(col.get(1));
//colSupplier.setMaxWidth(50);
        colDocnum.setCellValueFactory(new PropertyValueFactory<Grn, String>("Docnum"));

        TableColumn<Grn, String> colDate = new TableColumn<Grn, String>(col.get(2));
//colSupplier.setMaxWidth(50);
        colDate.setCellValueFactory(new PropertyValueFactory<Grn, String>("Date"));

        colSupplier.prefWidthProperty().bind(tbldata.widthProperty().divide(3)); // w * 1/4
        colDocnum.prefWidthProperty().bind(tbldata.widthProperty().divide(3)); // w * 2/4
        colDate.prefWidthProperty().bind(tbldata.widthProperty().divide(3)); // w * 1/4

        tbldata.getColumns().addAll(colSupplier, colDocnum, colDate);

        tableColDefine();

        swingnode.setContent(jtext);
        swingnode.setVisible(true);
        checkInDatePicker.setValue(LocalDate.parse(Today));

        refreshGrnList();

        Platform.runLater(() -> filterGRN.requestFocus());
        
            
         
    }

    
    @FXML
    public void delAct() throws Exception {
        
        if(grnviewtbl.getItems().size() < 1)
        {
          DialogPane dialogPanerepoempty = alertinfo.getDialogPane();
             dialogPanerepoempty.getStylesheets().add(getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
                alertinfo.setTitle("Info");
                alertinfo.setHeaderText(null);
                alertinfo.setContentText("Table is Empty!!");
                alertinfo.showAndWait();
        }
        else
        {      
            try   
            {
            GrnDetails gd = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex());
          
            
          if (gd.getId().length() > 0)
          {
            
        DialogPane dialogPanedel = alert.getDialogPane();
        dialogPanedel.getStylesheets().add(
                    getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
          

                //dialogPane.getStyleClass().add("myDialog");
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("A Confirmation Dialog");
                alert.setContentText("Are you sure you want to delete?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    gm.deleteRow(allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getId());
                    refreshItemTable();
                }
          }
        }catch(Exception ex)
        {
            System.out.println("System got exception "+ ex);
             DialogPane dialogPanerepoempty = alertinfo.getDialogPane();
             dialogPanerepoempty.getStylesheets().add(getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
                alertinfo.setTitle("Info");
                alertinfo.setHeaderText(null);
                alertinfo.setContentText("Table row is not selected!!");
                alertinfo.showAndWait();
            
            
        }
            
     }
 }
    
  
    
@FXML
public void syncbtn()
 {  
     
  //progressBr.setProgress(0.25F);  
  synbtn.setDisable(true);
  Platform.runLater(() -> progressBr.setVisible(true));
  Platform.runLater(() ->compmsg.setVisible(true));
  Platform.runLater(() -> compmsg.setText(""));
  progressBr.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
    
  Thread t = new Thread(new Runnable(){
  public void run() {
  try{
    //Thread.sleep(8000);     
    String valstr = gm.syncReq("api/canias/sync");
    if(valstr.equals("1"))
          {       
     Platform.runLater(() -> progressBr.setProgress(10));
     Platform.runLater(() ->compmsg.setText("Completed!"));
     synbtn.setDisable(false);
     Thread.sleep(3000);
     Platform.runLater(() -> progressBr.setVisible(false));
     Platform.runLater(() ->compmsg.setVisible(false));
          }
     else
          {
     Platform.runLater(() ->  progressBr.setProgress(0));
     Platform.runLater(() ->compmsg.setText("Not Sync!"));
     synbtn.setDisable(true);
     Thread.sleep(3000);
     Platform.runLater(() -> progressBr.setVisible(false));
     Platform.runLater(() ->compmsg.setVisible(false));
         }
     }catch (Exception ex) {
       Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
      } 
     }
   });

    t.start();
 }

/*


*/

    public void printmsg(String msg)
        {
       
       
           
        }
 
@FXML
public void addNewITem() throws Exception {
        String matcheckresponse;
        String message;
        errDisp.getChildren().clear();
        
        if (codetxt.getText().length() < 1 || scammattxt.getText().length() < 1 || desctxt.getText().length() < 1 || contrytxt.getText().length() < 1 || qtytxt.getText().length() < 1 || wghttxt.getText().length() < 1) {
            errDisp.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning, MaterialColor.RED_900, "Incomplete Details!"));           
        } else {
            matcheckresponse = gm.insertneweancode(tbldata, allRow, Today, scammattxt.getText(), desctxt.getText(), codetxt.getText(), qtytxt.getText(), contrytxt.getText(), wghttxt.getText());
            if (matcheckresponse.equals("-1")) {
                errDisp.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning, MaterialColor.RED_900, "Invalid Item Details!"));
            } else if (matcheckresponse.equals("-2")) {
                errDisp.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning, MaterialColor.RED_900, "Weight data does not match system data!"));
            } else if (matcheckresponse.equals("-3")) {
                errDisp.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning, MaterialColor.RED_900, "The EAN Code is already linked with a Different Item!"));
            } else if (matcheckresponse.equals("1")) {
                
            //insert into the table  
             String returnstr =  gm.isMatScanned(scammattxt.getText(),codetxt.getText(),desctxt.getText(),gv.getselDocnum());
            if(!returnstr.equals("1 record found"))
               {
             String grntxt = gv.getselDocnum();
             //Check if material already exist 
             gm.insertIntogrnITem(scammattxt.getText().toUpperCase(), codetxt.getText() , desctxt.getText(), "", "", qtytxt.getText(), "", "", "", qtytxt.getText(), wghttxt.getText(), contrytxt.getText(), "", gv.getGlobusername(), "", grntxt);
             refreshItemTable();
             scammattxt.setText("");
             desctxt.setText("");
             codetxt.setText("");
             contrytxt.setText("");
             wghttxt.setText("");
             qtytxt.setText("");
             codetxt.requestFocus();
             codetxt.setEditable(true);
             desctxt.setEditable(true);
             contrytxt.setEditable(true);
             wghttxt.setEditable(true);               
               }  
            else   
               {
             errDisp.getChildren().add(cc.messageBox(FxFontGoogleMaterial.Icons.gmd_warning, MaterialColor.RED_900, "Material already entered!"));        
               }  
             }
         }    
    }

    
 
     /* 
     */
 
         @FXML
         public void addNewGrn(ActionEvent event) throws IOException {

         Parent root = FXMLLoader.load(getClass().getResource("/jizanscanner/views/AddGRN.fxml"));
         Scene nscene = new Scene(root);

         Stage tStatge = new Stage();
         tStatge.getIcons().add(new Image("/images/appicon.png"));
         tStatge.setTitle("Add New GRN");
        
         tStatge.initModality(Modality.APPLICATION_MODAL);
         tStatge.setScene(nscene);
         tStatge.show();

         tStatge.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                refreshGrnList();
               System.out.println("Data updated!!"); 
            }
          });         
         }
         
         

    @FXML
    public void getReport() throws IOException {
       
        refreshItemTable();
         
        /* this is test material */
       
        System.out.print("current table... "+grnviewtbl.getItems().size());
        
         if(grnviewtbl.getItems().size() > 0)
         {
             
            gv.setGrnObservTable(allGrnRow);
            Parent root = FXMLLoader.load(getClass().getResource("/jizanscanner/views/ReportPrint.fxml"));
            Scene nscene = new Scene(root);
            Stage tStatge = new Stage();
            tStatge.initModality(Modality.APPLICATION_MODAL);
            tStatge.getIcons().add(new Image("/images/appicon.png"));
            tStatge.setTitle("Print GRN");
            tStatge.setScene(nscene);
            tStatge.show();
            gv.setReportStage(tStatge);
            
        }else
        {
            DialogPane dialogPanerepo = alertinfo.getDialogPane();
            dialogPanerepo.getStylesheets().add(getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
            alertinfo.setTitle("Info");
            alertinfo.setHeaderText(null);
            alertinfo.setContentText("Table is Empty!!");
            alertinfo.showAndWait();
            
        }
    }

     /*
    tu h
     */
    
    @FXML
    public void onEnter(KeyEvent evt) {
        if (evt.getCode().toString().equals("ENTER")) {
            refreshGrnList();
        }
    }

    @FXML
    public void refreshGrnList() {
        String dateval = "";
        if (checkInDatePicker.getValue() != null) {
            dateval = checkInDatePicker.getValue().toString();
        } else {
            dateval = "";
        }
        
        if (dateval.length() > 2 || filterGRN.getText().length() > 2) {
            allRow = gm.searchGRN(dateval, filterGRN.getText());
            tbldata.setItems(allRow);
        //GUIUtils.fitTable(tbldata);            
        }
        
    }


     
     public void defaultGrnList() {
        
        String dateval = "";
        checkInDatePicker.setValue(LocalDate.parse(Today));
        dateval = checkInDatePicker.getValue().toString();
        allRow = gm.searchGRN(dateval, "");
        tbldata.setItems(allRow);
        
     }

    public void refreshItemTable() {
        allGrnRow = gm.searchGRNDetails(gv.getselDocnum());
        grnviewtbl.setItems(allGrnRow);
            
        GUIUtils.fitTable(grnviewtbl);
        GUIUtils.autoFitTable(grnviewtbl);
   
    }


     /*
     
     */
    
    @FXML
    public void grnClicked(MouseEvent evt) {

        if (evt.getClickCount() == 1) {
            if (tbldata.getSelectionModel().getSelectedItem() != null) {
                
                 currow = tbldata.getSelectionModel().getFocusedIndex();
                 System.out.println("docnum value:" + allRow.get(currow).getDocnum());
                 /*this is test material*/
                 //Start-To set Docnum number in GV class
                 gv.setselDocnum(allRow.get(currow).getDocnum());
                 //End-To set Docnum number in GV class
                 thisGrnNum.setText(allRow.get(currow).getDocnum());
                 refreshItemTable();
                 lockColumns(0);

                try {

                    /*To track grnnumber and grnstatus of current selected row*/
                    stat = gm.getGRNStatus(allRow.get(currow).getDocnum().trim().substring(0, 3), allRow.get(currow).getDocnum().trim().substring(5, 9), allRow.get(currow).getDocnum().trim().substring(12, 14));

                } catch (Exception ex) {
                    System.out.println("Exception" + ex);
                }

                if (stat.equals("Close")) {
                    
                    isedit = 0;
                    
                } else {
                    
                    isedit = 1;
                    
                }

                System.out.println("the status is:" + stat + "isedit" + isedit);
                
                if (isedit == 1 && !stat.equals("Close")) {
                    
                    System.out.println();
                    viewtbn.setVisible(true);
                    scanAnchor.setVisible(false);
                    scanAnchor.setManaged(false);
                    isedit = 1;
                    
                } else {
                    
                    scanAnchor.setVisible(false);
                    scanAnchor.setManaged(false);
                    isedit = 0;
                    
                }

                //Start - to set grnTable's Observable in GV class 
                gv.setGrnObservTable(allGrnRow);

                //End - to set grnTable's Observable in GV class  
                grnviewtbl.getSelectionModel().setCellSelectionEnabled(true);
        }
    }
}

    @FXML
    public void editNew() {
        
        if (1 == 1) {
            //Show a message asking if the user has finished editing
            //lockColumns(1);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
            System.out.println("Document Status" + stat);
            if (isedit == 1 && !stat.equals("Close")) {

                //dialogPane.getStyleClass().add("myDialog");
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("A Confirmation Dialog");
                alert.setContentText("Are you sure want to start scan for this GRN number?");
                Optional<ButtonType> result = alert.showAndWait();
                
             if (result.get() == ButtonType.OK) {

                    scanAnchor.setVisible(true);
                    scanAnchor.setManaged(true);
                    viewtbn.setVisible(true);
                   if(isSearchAll.isSelected() == true) 
                    {
                        
                      lockColumns(0); 
                      
                    }else
                    {
                     lockColumns(1);
                    }                      
                }
             else
                {  
                     lockColumns(0);
                }
                
             }else {
                        
                 viewtbn.setVisible(false);
                 DialogPane dialogPane1 = alertinfo.getDialogPane();
                 dialogPane1.getStylesheets().add(
                       getClass().getResource("/jizanscanner/style/scannergrnsetup.css").toExternalForm());
                 alertinfo.setTitle("Info");
                 alertinfo.setHeaderText(null);
                 alertinfo.setContentText("The GRN you selected is already Closed!!");
                 alertinfo.showAndWait();
                 viewtbn.setVisible(true);
                 return;
                
            }
        }

    }

    
    /*
        
    */
    
    
    @FXML
    public void showHideAct() {
        if (click == false) {
            
                showSplit.setDividerPosition(0, 0.0);
                rightVbox.setVgrow(showhide, Priority.ALWAYS);
                showhide.setGraphic(rightIcon);
                showhide.setContentDisplay(ContentDisplay.LEFT);
                click = true;
           
          } else {
            
                showSplit.setDividerPosition(0, 0.20);
                rightVbox.setMaxWidth(USE_COMPUTED_SIZE);
                click = false;
                showhide.setGraphic(leftIcon);
                showhide.setContentDisplay(ContentDisplay.LEFT);          
        }
    }

     @FXML
     public void searchMat(KeyEvent KV) {
        String strmat = "";
        String strdes = "";

        if (KV.getCode().toString().equals("ENTER")) {
            strmat = mattxt.getText();
            strdes = destxt.getText();
            allGrnRow = gm.searchMaterial(strmat, strdes, thisGrnNum.getText(), isSearchAll.isSelected());
        } else {
            strmat = mattxt.getText();
            strdes = destxt.getText();
            System.out.println("the input text is:" + strmat + "" + strdes);
            if (strmat.length() > 2) {
                allGrnRow = gm.searchMaterial(strmat, strdes, thisGrnNum.getText(), isSearchAll.isSelected());
            }
        }
        grnviewtbl.setItems(allGrnRow);
    }

         /* this is test ma */
    
         @FXML
         public void searchDes(KeyEvent KV) {
          String strmat = "";
          String strdes = "";

          if (KV.getCode().toString().equals("ENTER")) {
            strmat = mattxt.getText();
            strdes = destxt.getText();

            allGrnRow = gm.searchMaterial(strmat, strdes, thisGrnNum.getText(), isSearchAll.isSelected());
         }else {
            strmat = mattxt.getText();
            strdes = destxt.getText();
            if (strdes.length() > 3) {
                allGrnRow = gm.searchMaterial(strmat, strdes, thisGrnNum.getText(), isSearchAll.isSelected());
            }
         }
         grnviewtbl.setItems(allGrnRow);
      }

         public void lockColumns(Integer lock) {

         if (lock == 1) {
            colGood.setEditable(true);
            colBox.setEditable(true);
            colLeak.setEditable(true);
            colBreaken.setEditable(true);
            colRemark.setEditable(true);
            colLotno.setEditable(true);
           } else {
            colGood.setEditable(false);
            colBox.setEditable(false);
            colLeak.setEditable(false);
            colBreaken.setEditable(false);
            colRemark.setEditable(false);
            colLotno.setEditable(false);
          }
         }

    @FXML
    public void globalSearch() {
        if (isSearchAll.isSelected() == true) {
             lockColumns(0);
        }
    }

       
     @FXML
     public void matEntered(KeyEvent evt) throws Exception {
         if (evt.getCode().toString().equals("ENTER")) {
            String matcode = cs.getMaterialData(scammattxt.getText());
             if (!matcode.equals("-1")) {
                jObject = new JSONObject(matcode);
                String Desc = (String) jObject.get("ITEMTEXT");
                String code = (String) jObject.get("ITEM");
                String contry = (String) jObject.get("COUNTRY");
                String wght = (String) jObject.get("NETWEIGHT");
                String imageSource = (String) jObject.get("IMG");
                desctxt.setText(Desc);
                codetxt.setText(code);

                desctxt.setEditable(false);
                codetxt.setEditable(false);

             
                contrytxt.setText(contry);
                wghttxt.setText(wght);
           
 
                if (contrytxt.getText().length() > 1) {
                     contrytxt.setEditable(false);
                }

                if (Integer.parseInt(wghttxt.getText()) > 0) {
                     wghttxt.setEditable(false);
                } else {
                     wghttxt.setEditable(true);
                }
                
                    
                //Integer.parseInt(wghttxt.getText()) > 0) 
                //{ 
                //Integer.parseInt(wghttxt.getText()) > 0)
                //{}
                //}  
                
                
                //String imageSource = "http://imshopping.rediff.com/imgshop/800-1280/shopping/pixs/17683/p/prf101070_53d9ebaecb0f1._davidoff-cool-water-l-100ml-edt-for-women-100-ml.jpg";
                gv.setCurrImage(imageSource);                
                Image image = new Image(imageSource);
                imagevw.setImage(image);
                qtytxt.requestFocus();
                    
            } else {
                 
                desctxt.setEditable(true);
                codetxt.setEditable(true);
                contrytxt.setEditable(true);
                wghttxt.setEditable(true);
                
            }
        }
    }

    @FXML
    public void mathinttxt(KeyEvent evt) {

//if(searchMatTxt.getText().length() > 3)
//{
//TextFields.bindAutoCompletion(
//searchMatTxt, 
//"");
//
//          
// TextFields.createClearableTextField();
//    
//    String currtext = gm.matlist(searchMatTxt.getText().trim());
//    if(evt.getCode().toString().equals("BACKSPACE"))
//                {
//          currtext = gm.matlist(searchMatTxt.getText().trim());  
//          currtext = gm.matlist("");
//          TextFields.bindAutoCompletion(searchMatTxt,FXCollections.observableArrayList(Arrays.asList(currtext.split("\\s*,\\s*"))));
//          System.out.println("this is text:"+gm.matlist(searchMatTxt.getText()));
//                }else
//                  {
//                      
//          
//          TextFields.bindAutoCompletion(searchMatTxt,FXCollections.observableArrayList(Arrays.asList(currtext.split("\\s*,\\s*")))); 
//          System.out.println("this is text:"+gm.matlist(searchMatTxt.getText()));
//                  }
//            }
    }

    public void getMatName(String inputchar) throws Exception {

        connekt = dbconn.conn();
            //epoconnekt =  dbconn.epormisconn();

        //autocdblink = new AutoCompleteDBLink(atcDes, condstr, epoconnekt,0);  
        matcomponent = jtext;
        AutoTextComplete atcomp = new AutoTextComplete(matcomponent);
        autocdblink = new AutoCompleteDBLink(atcomp, connekt, inputchar); /*, users, connekt*/


    }

    public void refreshRow(int CurrentRow) {
        //loop through observable list
        ObservableList<GrnDetails> currList = ScannerGrnSetupController.allGrnRow;
        GrnDetails thisGrn = ScannerGrnSetupController.allGrnRow.get(CurrentRow);

        int goodqty = thisGrn.getGood().equals("") ? 0 : Integer.parseInt(thisGrn.getGood());
        int boxqty = thisGrn.getBox().equals("") ? 0 : Integer.parseInt(thisGrn.getBox());
        int leakqty = thisGrn.getLeak().equals("") ? 0 : Integer.parseInt(thisGrn.getLeak());
        int brkqty = thisGrn.getBreaken().equals("") ? 0 : Integer.parseInt(thisGrn.getBreaken());

        System.out.println("check G" + goodqty + "B " + boxqty + "L " + leakqty + "B " + brkqty);
        int total = goodqty + boxqty + leakqty + brkqty;
        thisGrn.setQty(Integer.toString(total));
        thisGrn.setQty(Integer.toString(total));

        /*
           
            
         */
    }

    @FXML
    public void endDocument() throws Exception {
        // To be Customized for Document
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("A Confirmation Dialog");
        alert.setContentText("Are you sure to End?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            gm.updateGRNStatus("Close", allRow.get(currow).getDocnum().trim().substring(0, 3), allRow.get(currow).getDocnum().trim().substring(5, 9), allRow.get(currow).getDocnum().trim().substring(12, 14));
        }
    }
    /*this is test materail name    */

    @FXML
    public void endGrn() throws Exception {
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("A Confirmation Dialog");
        alert.setContentText("Are you sure to End GRN?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String currdoctype = allRow.get(currow).getDocnum().trim().substring(0, 3);

            if (currdoctype.equals("ISR") || currdoctype.equals("LGR") || currdoctype.equals("MTN") || currdoctype.equals("AR")) {
                String docnum = allRow.get(currow).getDocnum().trim().substring(5, 9);
                gm.updateGRNStatus("Close", allRow.get(currow).getDocnum().trim().substring(0, 3), allRow.get(currow).getDocnum().trim().substring(5, 9), allRow.get(currow).getDocnum().trim().substring(12, 14));

                scanAnchor.setVisible(false);
                scanAnchor.setManaged(false);
                viewtbn.setVisible(true);

            } else {
                gm.updateGRNStatus("Close", allRow.get(currow).getDocnum().trim().substring(0, 3), allRow.get(currow).getDocnum().trim().substring(5, 9), allRow.get(currow).getDocnum().trim().substring(12, 14));
                scanAnchor.setVisible(false);
                scanAnchor.setManaged(false);
                viewtbn.setVisible(true);

            }
        }
    }

//String matcode,String desc,String good,String box,String leak,String breaken,String qty,String wght,String cntry,String ean,String remark,String lot,String doctype,String docnum,String grnnum,String id
    public void selectedRowVals() {
        crrmat = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getMaterial();
        crrdesc = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getDesc();
        crrgood = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getGood();
        crrbox = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getBox();
        crrleak = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getLeak();
        crrbreak = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getBreaken();
        crrqty = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getQty();
        crrwght = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getWeight();
        crrcntry = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getContry();
        crrean = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getEAN();
        crrremark = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getRemark();
        crrlot = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getLotno();
        crrdoctype = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getDoctype();
        crrdocnum = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getDocnum();
        crrgrnnum = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getGRNnum();
        crrgid = allGrnRow.get(grnviewtbl.getSelectionModel().getSelectedIndex()).getId();

    }

    public void tableColDefine() {

        /*Initialization of table with column of header grlcol*/
        grlcol = FXCollections.observableArrayList(colGrnHeading);

        Callback<TableColumn, TableCell> cellFactory
                = new Callback<TableColumn, TableCell>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        //TableColumn<GrnDetails,String> colId = new TableColumn<GrnDetails, String>(grlcol.get(0));
        TableColumn colId = new TableColumn("ID");

        //colId.setMaxWidth(20);
        colId.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Id"));
        colId.setCellFactory(cellFactory);
        colId.setEditable(false);

        grnviewtbl.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 1) {
                    @SuppressWarnings("rawtypes")
                    TablePosition pos = (TablePosition) grnviewtbl.getSelectionModel().getSelectedCells().get(0);
                    int row = pos.getRow();
                    int col = pos.getColumn();
                    @SuppressWarnings("rawtypes")
                    TableColumn column = pos.getTableColumn();
                    String val = column.getCellData(row).toString();
                    System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);
                    if (col == 0) {

                        grnviewtbl.getSelectionModel().setCellSelectionEnabled(false);

                    } else {
                        grnviewtbl.getSelectionModel().setCellSelectionEnabled(true);
                    }

                }
            }
        });

        //TableColumn<GrnDetails, String> colMaterial = new TableColumn<GrnDetails, String>(grlcol.get(1));
        TableColumn colMaterial = new TableColumn("material");

        //colMaterial.setMinWidth(100);
        colMaterial.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("material"));
        colMaterial.setCellFactory(cellFactory);
        colMaterial.setEditable(false);

        //TableColumn<GrnDetails, String> colDes = new TableColumn<GrnDetails, String>(grlcol.get(2));
        TableColumn colDes = new TableColumn("Desc");
        //colDes.setMinWidth(100);
        //colDes.setCellFactory(TextFieldTableCell.forTableColumn());
        colDes.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Desc"));
        colDes.setCellFactory(cellFactory);
        colDes.setEditable(false);

        //TableColumn<GrnDetails, String> colGood = new TableColumn<GrnDetails, String>(grlcol.get(3));
        colGood = new TableColumn("Good");
             //colCond_goodqty.setMinWidth(100);
        //colGood.setCellFactory(TextFieldTableCell.forTableColumn());
        colGood.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Good"));
        colGood.setCellFactory(cellFactory);
        colGood.setOnEditCommit(
                new EventHandler<CellEditEvent<GrnDetails, String>>() {
                    @Override
                    public void handle(CellEditEvent<GrnDetails, String> event) {
                        ((GrnDetails) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())).setGood(event.getNewValue());
                        System.out.println("Value Updated!");
                        System.out.println("The Row is : " + Integer.toString(event.getTablePosition().getRow()));
                        refreshRow(event.getTablePosition().getRow());

                        try {
                            selectedRowVals();
                            gm.updateRow(crrmat, crrdesc, crrgood, crrbox, crrleak, crrbreak, crrqty, crrwght, crrcntry, crrean, crrremark, crrlot, crrdoctype, crrdocnum, crrgrnnum, crrgid);
                            //event.getTableView().refresh();
                        } catch (Exception ex) {
                            Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        );

        //TableColumn<GrnDetails, String> colBox = new TableColumn<GrnDetails, String>(grlcol.get(4));
        colBox = new TableColumn("Box");
        //colCond_dmgboxqty.setMinWidth(100);
        colBox.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Box"));
        colBox.setCellFactory(cellFactory);
        colBox.setOnEditCommit(
                new EventHandler<CellEditEvent<GrnDetails, String>>() {
                    @Override
                    public void handle(CellEditEvent<GrnDetails, String> event) {
                        ((GrnDetails) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())).setBox(event.getNewValue());
                        refreshRow(event.getTablePosition().getRow());
                        try {
                            selectedRowVals();
                            gm.updateRow(crrmat, crrdesc, crrgood, crrbox, crrleak, crrbreak, crrqty, crrwght, crrcntry, crrean, crrremark, crrlot, crrdoctype, crrdocnum, crrgrnnum, crrgid);
                            //event.getTableView().refresh();
                        } catch (Exception ex) {
                            Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        );

        //TableColumn<GrnDetails, String> colLeak = new TableColumn<GrnDetails, String>(grlcol.get(5));
        colLeak = new TableColumn("Leak");
        //colCond_dmgleakqty.setMinWidth(100);
        colLeak.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Leak"));
        colLeak.setCellFactory(cellFactory);
        colLeak.setOnEditCommit(
                new EventHandler<CellEditEvent<GrnDetails, String>>() {
                    @Override
                    public void handle(CellEditEvent<GrnDetails, String> event) {
                        ((GrnDetails) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())).setLeak(event.getNewValue());
                        refreshRow(event.getTablePosition().getRow());

                        try {
                            selectedRowVals();
                            gm.updateRow(crrmat, crrdesc, crrgood, crrbox, crrleak, crrbreak, crrqty, crrwght, crrcntry, crrean, crrremark, crrlot, crrdoctype, crrdocnum, crrgrnnum, crrgid);
                            //event.getTableView().refresh();
                        } catch (Exception ex) {
                            Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
        );

        //TableColumn<GrnDetails, String> colBreaken = new TableColumn<GrnDetails, String>(grlcol.get(6));
        colBreaken = new TableColumn("Break");
        //colCond_dmgbrkqty.setMinWidth(100);
        colBreaken.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Breaken"));
        colBreaken.setCellFactory(cellFactory);
        colBreaken.setOnEditCommit(
                new EventHandler<CellEditEvent<GrnDetails, String>>() {
                    @Override
                    public void handle(CellEditEvent<GrnDetails, String> event) {
                        ((GrnDetails) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())).setBreaken(event.getNewValue());
                        refreshRow(event.getTablePosition().getRow());

                        try {
                            selectedRowVals();
                            gm.updateRow(crrmat, crrdesc, crrgood, crrbox, crrleak, crrbreak, crrqty, crrwght, crrcntry, crrean, crrremark, crrlot, crrdoctype, crrdocnum, crrgrnnum, crrgid);
                            //event.getTableView().refresh();
                        } catch (Exception ex) {
                            Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
        );

        //TableColumn<GrnDetails, String> colQty = new TableColumn<GrnDetails, String>(grlcol.get(7));
        TableColumn colQty = new TableColumn("Qty");
        //colCond_dmgbrkqty.setMinWidth(100);
        colQty.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Qty"));
        //colQty.setCellFactory(cellFactory);
        colQty.setEditable(false);

        //TableColumn<GrnDetails, String> colContry = new TableColumn<GrnDetails, String>(grlcol.get(8));
        TableColumn colContry = new TableColumn("Contry");
        //colContrycode.setMinWidth(100);
        colContry.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Contry"));
        //colContry.setCellFactory(cellFactory);
        colContry.setEditable(false);

        //TableColumn<GrnDetails, String> colEAN = new TableColumn<GrnDetails, String>(grlcol.get(9));
        TableColumn colEAN = new TableColumn("EAN");
        //colEancode.setMinWidth(100);
        colEAN.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("EAN"));
        //colEAN.setCellFactory(cellFactory);
        colEAN.setEditable(false);

        //TableColumn<GrnDetails, String> colWeight = new TableColumn<GrnDetails, String>(grlcol.get(10));
        TableColumn colWeight = new TableColumn("Weight");
        //colWeight.setMinWidth(100);
        colWeight.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Weight"));
        //colWeight.setCellFactory(cellFactory);
        colWeight.setEditable(false);

        //TableColumn<GrnDetails, String> colRemark = new TableColumn<GrnDetails, String>(grlcol.get(11));
        colRemark = new TableColumn("Remark");
        //colRemark.setMinWidth(100);
        colRemark.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Remark"));
        colRemark.setCellFactory(cellFactory);
        colRemark.setOnEditCommit(
                new EventHandler<CellEditEvent<GrnDetails, String>>() {
                    @Override
                    public void handle(CellEditEvent<GrnDetails, String> event) {
                        ((GrnDetails) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())).setRemark(event.getNewValue());

                        try {
                            selectedRowVals();
                            gm.updateRow(crrmat, crrdesc, crrgood, crrbox, crrleak, crrbreak, crrqty, crrwght, crrcntry, crrean, crrremark, crrlot, crrdoctype, crrdocnum, crrgrnnum, crrgid);
                            //event.getTableView().refresh();
                        } catch (Exception ex) {
                            Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
        );

        //TableColumn<GrnDetails, String> colLotno = new TableColumn<GrnDetails, String>(grlcol.get(12));
        colLotno = new TableColumn("LotNo");
        //colLotnumber.setMinWidth(100);
        colLotno.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Lotno"));
        colLotno.setCellFactory(cellFactory);
        colLotno.setOnEditCommit(
                new EventHandler<CellEditEvent<GrnDetails, String>>() {
                    @Override
                    public void handle(CellEditEvent<GrnDetails, String> event) {
                        ((GrnDetails) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())).setLotno(event.getNewValue());

                        try {
                            selectedRowVals();
                            gm.updateRow(crrmat, crrdesc, crrgood, crrbox, crrleak, crrbreak, crrqty, crrwght, crrcntry, crrean, crrremark, crrlot, crrdoctype, crrdocnum, crrgrnnum, crrgid);
                            //event.getTableView().refresh();
                        } catch (Exception ex) {
                            Logger.getLogger(ScannerGrnSetupController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
        );

        //TableColumn<GrnDetails, String> colDoctype = new TableColumn<GrnDetails, String>(grlcol.get(13));
        TableColumn colDoctype = new TableColumn("Doctype");
        //colDoctype.setMinWidth(100);
        colDoctype.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Doctype"));
        //colDoctype.setCellFactory(cellFactory);
        colDoctype.setEditable(false);

        //TableColumn<GrnDetails, String> colDocnum = new TableColumn<GrnDetails, String>(grlcol.get(14));
        TableColumn colDocnum = new TableColumn("Docnum");
        //colDocnum.setMinWidth(100);
        colDocnum.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Docnum"));
        //colDocnum.setCellFactory(cellFactory);
        colDocnum.setEditable(false);

        //TableColumn<GrnDetails, String> colGrnnum = new TableColumn<GrnDetails, String>(grlcol.get(15));
        TableColumn colGrnnum = new TableColumn("GRNnum");
        //colGrnnum.setMinWidth(100);
        colGrnnum.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("GRNnum"));
        //colGrnnum.setCellFactory(cellFactory);
        colGrnnum.setEditable(false);

        //TableColumn<GrnDetails, String> colDocstatus = new TableColumn<GrnDetails, String>(grlcol.get(16));
        TableColumn colDocstatus = new TableColumn("Docstatus");
        //colDocstatus.setMinWidth(100);
        colDocstatus.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Docstatus"));
        //colDocstatus.setCellFactory(cellFactory);
        colDocstatus.setEditable(false);

        //TableColumn<GrnDetails, String> colGRNstatus = new TableColumn<GrnDetails, String>(grlcol.get(17));
        TableColumn colGRNstatus = new TableColumn("GRNstatus");
        //colGrnstatus.setMinWidth(100);
        colGRNstatus.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("GRNstatus"));
        //colGRNstatus.setCellFactory(cellFactory);
        colGRNstatus.setEditable(false);

        grnviewtbl.setEditable(true);
        grnviewtbl.getColumns().addAll(colId, colMaterial, colDes, colGood, colBox, colLeak,
                colBreaken, colQty, colContry, colEAN, colWeight, colRemark, colLotno, colDoctype, colDocnum, colGrnnum, colDocstatus,
                colGRNstatus);

    }

}
