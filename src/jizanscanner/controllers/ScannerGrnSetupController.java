/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.controllers;

import java.io.IOException;
import java.net.URL;
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
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jizanscanner.models.GlobalVariable;
import jizanscanner.models.Grn;
import jizanscanner.models.GrnDetails;
import jizanscanner.models.GrnModal;

/**
 * FXML Controller class
 *
 * @author santosh
 */
public class ScannerGrnSetupController implements Initializable {

    /**
     * Initializes the controller class.
     */
   

    
    GrnModal gm = new GrnModal();

    GlobalVariable gv = new GlobalVariable();
    private static ObservableList<String> col;
    private static ObservableList<Grn> allRow;
    String colHeading[]={"Supplier","Docnum","Date"};
    String colGrnHeading[]={"Id","Material","Desc","Good","Box","Leak","Break","Qty","Contry","EAN","Weight",
    "Remark","LotNo","Doctype","Docnum","GRNnum","Docstatus","GRNstatus"};
    final KeyCombination kcTab = KeyCodeCombination.valueOf("TAB");
    final KeyCombination kcShiftTab = KeyCodeCombination.valueOf("Shift+TAB");
    
    private final String pattern = "yyyy-MM-dd";
    String datValue = "";
    
    private static ObservableList<String> grlcol;
    private static ObservableList<GrnDetails> allGrnRow;
    
    @FXML
    public Button addbtn;
    
    @FXML
    private TableView tbldata;
    
    @FXML
    private DatePicker checkInDatePicker;
    
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
    
    DateFormat todayformate = new SimpleDateFormat("yyyy-MM-dd"); // Just the year, with last 2 digits
   String Today = todayformate.format(Calendar.getInstance().getTime());
    
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
       
        checkInDatePicker.setConverter(converter);
        col = FXCollections.observableArrayList(colHeading);
        
   

             TableColumn<Grn,String> colSupplier = new TableColumn<Grn, String>(col.get(0));
             colSupplier.setMinWidth(120);
             colSupplier.setCellValueFactory(new PropertyValueFactory<Grn,String>("supplier"));

             TableColumn<Grn, String> colDocnum = new TableColumn<Grn, String>(col.get(1));
             colSupplier.setMaxWidth(50);
             colDocnum.setCellValueFactory(new PropertyValueFactory<Grn,String>("Docnum"));

             TableColumn<Grn, String> colDate = new TableColumn<Grn, String>(col.get(2));
             colSupplier.setMaxWidth(50);
             colDate.setCellValueFactory(new PropertyValueFactory<Grn,String>("Date"));
            
             colSupplier.prefWidthProperty().bind(tbldata.widthProperty().divide(3)); // w * 1/4
             colDocnum.prefWidthProperty().bind(tbldata.widthProperty().divide(3)); // w * 2/4
             colDate.prefWidthProperty().bind(tbldata.widthProperty().divide(3)); // w * 1/4
            
            tbldata.getColumns().addAll(colSupplier,colDocnum,colDate);
            
            tableColDefine();
            
            
            
             
               
    }    
  
    
@FXML
public void addNewGrn() throws IOException
    {
      Parent root = FXMLLoader.load(getClass().getResource("/jizanscanner/views/AddGRN.fxml"));
      Scene nscene = new Scene(root);
      Stage tStatge = new Stage();
      tStatge.initModality(Modality.APPLICATION_MODAL);
      tStatge.setScene(nscene);
      tStatge.show();
      
    }
    
    @FXML
    public void onEnter(KeyEvent evt)
     {
        if(evt.getCode().toString().equals("ENTER"))
            {
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
        }
    }
    
public void defaultGrnList() { 
    
    String dateval = "";
    checkInDatePicker.setValue(LocalDate.parse(Today));
    dateval = checkInDatePicker.getValue().toString();
    allRow = gm.searchGRN(dateval,"");
    tbldata.setItems(allRow);
    }

 
@FXML
public void grnClicked(MouseEvent evt) {
        if(evt.getClickCount() == 1) {
        if(tbldata.getSelectionModel().getSelectedItem() != null) {
        int currow = tbldata.getSelectionModel().getFocusedIndex();
        System.out.println("docnum value:"+ allRow.get(currow).getDocnum());
        allGrnRow = gm.searchGRNDetails(allRow.get(currow).getDocnum());
        grnviewtbl.setItems(allGrnRow); 
        grnviewtbl.getSelectionModel().setCellSelectionEnabled(true);
     }
  }
}   



//http://www.java2s.com/Tutorials/Java/JavaFX/0650__JavaFX_TableView.htm
@FXML
public void addKeyTab(KeyEvent k){
    if (kcTab.match(k) || kcShiftTab.match(k)) {
                    //grnviewtbl.getSelectionModel().getSelectedItem().;
                    //System.out.println("traverse forward");
                    //requestFocus(textField3);
                } else if (kcShiftTab.match(k) || kcShiftTab.match(k)) {
                    //System.out.println("traverse backward");
                    //requestFocus(textField1);
                      grnviewtbl.getSelectionModel().selectNext();
                }
}


@FXML
public void searchMat(KeyEvent KV)
{
    String strmat = "";
    String strdes = "";
    
   if(KV.getCode().toString().equals("ENTER"))
                {
                   strmat = mattxt.getText();
                   strdes = destxt.getText();
                    
                  allGrnRow = gm.searchMaterial(strmat, strdes);
                  
                } 
   else{
        strmat = mattxt.getText();
        strdes = destxt.getText();
        
       if(strmat.length() > 2)
            {
           allGrnRow = gm.searchMaterial(strmat, strdes); 
             }
        }
grnviewtbl.setItems(allGrnRow); 
}


@FXML
public void searchDes(KeyEvent KV)
    {
        
    String strmat = "";
    String strdes = "";
    
   if(KV.getCode().toString().equals("ENTER"))
                {
                   strmat = mattxt.getText();
                   strdes = destxt.getText();
                    
                  allGrnRow = gm.searchMaterial(strmat, strdes);
                } 
   else{
       strmat = mattxt.getText();
       strdes = destxt.getText();
       if(strdes.length() > 3)
                 {    
           allGrnRow = gm.searchMaterial(strmat, strdes); 
                 }
        }
    grnviewtbl.setItems(allGrnRow); 
 }
    

public void tableColDefine(){    
      
       
        
          /*Initialization of table with column of header grlcol*/
          
          grlcol = FXCollections.observableArrayList(colGrnHeading);
          
          TableColumn<GrnDetails,String> colId = new TableColumn<GrnDetails, String>(grlcol.get(0));
            //colId.setMaxWidth(20);
          colId.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Id"));

          TableColumn<GrnDetails, String> colMaterial = new TableColumn<GrnDetails, String>(grlcol.get(1));
          colMaterial.setMinWidth(100);
          colMaterial.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Material"));

          TableColumn<GrnDetails, String> colDes = new TableColumn<GrnDetails, String>(grlcol.get(2));
          //colDes.setMinWidth(100);
         colDes.setCellFactory(TextFieldTableCell.forTableColumn());
          colDes.setCellValueFactory(new PropertyValueFactory<GrnDetails, String>("Desc"));
            
          TableColumn<GrnDetails, String> colGood = new TableColumn<GrnDetails, String>(grlcol.get(3));
          //colCond_goodqty.setMinWidth(100);
          colGood.setCellFactory(TextFieldTableCell.forTableColumn());
          colGood.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Good"));
            
          TableColumn<GrnDetails, String> colBox = new TableColumn<GrnDetails, String>(grlcol.get(4));
          //colCond_dmgboxqty.setMinWidth(100);
          colBox.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Box"));
            
          TableColumn<GrnDetails, String> colLeak = new TableColumn<GrnDetails, String>(grlcol.get(5));
          //colCond_dmgleakqty.setMinWidth(100);
          colLeak.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Leak"));
            
          TableColumn<GrnDetails, String> colBreaken = new TableColumn<GrnDetails, String>(grlcol.get(6));
          //colCond_dmgbrkqty.setMinWidth(100);
           colBreaken.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Breaken"));
        
           TableColumn<GrnDetails, String> colQty = new TableColumn<GrnDetails, String>(grlcol.get(7));
           //colCond_dmgbrkqty.setMinWidth(100);
           colQty.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Qty"));
        
           TableColumn<GrnDetails, String> colContry = new TableColumn<GrnDetails, String>(grlcol.get(8));
           //colContrycode.setMinWidth(100);
          colContry.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Contry"));
            
           TableColumn<GrnDetails, String> colEAN = new TableColumn<GrnDetails, String>(grlcol.get(9));
           //colEancode.setMinWidth(100);
           colEAN.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("EAN"));
           
           TableColumn<GrnDetails, String> colWeight = new TableColumn<GrnDetails, String>(grlcol.get(10));
           //colWeight.setMinWidth(100);
           colWeight.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Weight"));
           
           TableColumn<GrnDetails, String> colRemark = new TableColumn<GrnDetails, String>(grlcol.get(11));
           //colRemark.setMinWidth(100);
           colRemark.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Remark"));
            
           TableColumn<GrnDetails, String> colLotno = new TableColumn<GrnDetails, String>(grlcol.get(12));
           //colLotnumber.setMinWidth(100);
           colLotno.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Lotno"));
            
           TableColumn<GrnDetails, String> colDoctype = new TableColumn<GrnDetails, String>(grlcol.get(13));
           //colDoctype.setMinWidth(100);
           colDoctype.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Doctype"));
           
           TableColumn<GrnDetails, String> colDocnum = new TableColumn<GrnDetails, String>(grlcol.get(14));
           //colDocnum.setMinWidth(100);
           colDocnum.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Docnum"));
           
           TableColumn<GrnDetails, String> colGrnnum = new TableColumn<GrnDetails, String>(grlcol.get(15));
           //colGrnnum.setMinWidth(100);
           colGrnnum.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("GRNnum"));
           
           TableColumn<GrnDetails, String> colDocstatus = new TableColumn<GrnDetails, String>(grlcol.get(16));
           //colDocstatus.setMinWidth(100);
           colDocstatus.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("Docstatus"));
            
           TableColumn<GrnDetails, String> colGRNstatus = new TableColumn<GrnDetails, String>(grlcol.get(17));
           //colGrnstatus.setMinWidth(100);
           colGRNstatus.setCellValueFactory(new PropertyValueFactory<GrnDetails,String>("GRNstatus"));
           
           grnviewtbl.getColumns().addAll(colId,colMaterial,colDes,colGood,colBox,colLeak,
           colBreaken,colQty,colContry,colEAN,colWeight,colRemark,colLotno,colDoctype,colDocnum,colGrnnum,colDocstatus,
           colGRNstatus);
           
           /*Santosh -  */
           
        }
    
    
    }

    
    
    
    
    
    
    

