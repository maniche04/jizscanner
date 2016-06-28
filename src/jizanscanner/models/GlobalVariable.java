/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;




/**
 *
 * @author USER
 */ 
public class GlobalVariable {

   
    public static int passfail = 0;
//    public static SearchEanPanel Searchobj = null;
//    public static SearchEanPanelNormal SearchNormalobj = null;
    public static String username = "";
    public static String password = "";
//    public static EanHome obj = null;
//    public static PrintSetup printobj = null;
//    public static PrintSetup printmanagerobj = null;
    public static String barcodedata = "";
    public static String prevsysname = "";
    public static String usertype = "";
    public static String dialogname ="";
    public static  Map<String, String> myMap = new HashMap<String, String>();
    
    
   
    
    

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
   
//    public static PrintSetup getPrintmanagerobj() {
//        return printmanagerobj;
//    }
//
//    public void setPrintmanagerobj(PrintSetup printmanagerobj) {
//        this.printmanagerobj = printmanagerobj;
//    }
//    
//
//    public  PrintSetup getPrintobj() {
//        return printobj;
//    }
//
//    public  void setPrintobj(PrintSetup printobj) {
//        this.printobj = printobj;
//    }
    

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
    public static String prevprintername = "";

    public String getBarcodedata() {
        return barcodedata;
    }

    public void setBarcodedata(String barcodedata) {
        GlobalVariable.barcodedata = barcodedata;
    }

//    public EanHome getObj() {
//        return obj;
//    }
//
//    public void setObj(EanHome obj) {
//        this.obj = obj;
//    }

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
       frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/eancode/scanimg.png"))); 
    }
     
   public String getDialogname() {
        return this.dialogname;
    }

    public void setDialogname(String dialogname) {
        this.dialogname = dialogname;
        
    }
   
    
   
    
     
}
