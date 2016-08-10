
package jizanscanner.models;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import jizanscanner.controllers.ScannerGrnSetupController;



/**
* @author SANTOSH JAISWAL
*/
public class GlobalVariable {

   
public static int passfail = 0;
private static ObservableList<GrnDetails> allGrnRow;
public static String username = "";
public static String password = "";
public static String barcodedata = "";
public static String prevsysname = "";
public static String usertype = "";
public static String dialogname ="";
public static  Map<String, String> myMap = new HashMap<String, String>();
public static String currgrnnum ="";
public static String currImage = "";
public static Stage repoStage = null;
public static String prevprintername = "";
    
    public String curgrn = "";
    private ScannerGrnSetupController parentcontrol;

    public ScannerGrnSetupController getMyControllerobj() {
        return parentcontrol;
    }
    
    public void setCurrImage(String currimg) {
        GlobalVariable.currImage = currimg;
    }
    
    
    public String getCurrImage(){
        return currImage;
    }
    
    public String getCurgrn() {
        return curgrn;
    }

    public void setCurgrn(String curgrn) {
        this.curgrn = curgrn;
    }
    
    public  String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    
    public static Map<String, String> getMyMap() {
        return myMap;
    }

    public static void setMyMap(Map<String, String> myMap) {
        GlobalVariable.myMap = myMap;
    }
   
    public void setGrnObservTable(javafx.collections.ObservableList<GrnDetails> myobservtbl) {
        GlobalVariable.allGrnRow = myobservtbl;
    }
    
    public javafx.collections.ObservableList<GrnDetails> getGrnObservTable() {
        return allGrnRow;  
    }
    
    public String getPrevsysname() {
        return prevsysname;
    }

    public void setPrevsysname(String prevsysname) {
        GlobalVariable.prevsysname = prevsysname;
    }

    public String getPrevprintername() {
        return prevprintername;
    }

    public void setPrevprintername(String prevprintername) {
        GlobalVariable.prevprintername = prevprintername;
    }
    

    public String getBarcodedata() {
        return barcodedata;
    }

    public void setBarcodedata(String barcodedata) {
        GlobalVariable.barcodedata = barcodedata;
    }
    
    public void  setselDocnum(String currDocNum){
        GlobalVariable.currgrnnum =  currDocNum;
    }
    
    public String getselDocnum(){
        return currgrnnum;
    }

     public int getPassFail() {
        return this.passfail;
    }

    public void setPassFail(int passfail) {
        this.passfail = passfail;
    }
    
    public String getGlobusername() {
        return this.username;
    }

    public void setGlobusername(String username) {
        this.username = username;   
    }
    
    public String getGlobpassword() {
        return this.password;
    }

    public void setGlobpassword(String password) {
        this.password = password;        
    }
    
    public void setAppDefaultImg(java.awt.Frame frm)
    {
       frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/scanner.jpg"))); 
    }
    
    public String getDialogname() {
        return this.dialogname;
    }

    public void setDialogname(String dialogname) {
        this.dialogname = dialogname;   
    }
    
    public void setMyControllerobj(ScannerGrnSetupController aThis) {
        this.parentcontrol = aThis;
    }

    public void setReportStage(Stage tStatge) {
        this.repoStage = tStatge;
    }
    
    public Stage getReportStage() {
       return this.repoStage;
    }
    
}
