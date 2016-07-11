/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import jizanscanner.controllers.ScannerGrnSetupController;


/**
 * @author santosh
 */

public class GrnModal {
    
    DbConnection dbconn = new DbConnection(); 
    GlobalVariable gv = new GlobalVariable();
    Connection connekt = null;
    Statement stmt = null;
    PreparedStatement pst =null;
    ResultSet rs = null;
    private static ObservableList<Grn> row;
    private static ObservableList<GrnDetails> Grnrow;
    Alert alertinfo = new Alert(Alert.AlertType.INFORMATION);
    public String grnnum = "";
    
//ScannerGrnSetupController SC = new ScannerGrnSetupController();
    
            
  
     public ObservableList<Grn> searchGRN(String date, String filtertext)
     {         
          try {
         
             connekt =  dbconn.conn();
             row = FXCollections.observableArrayList();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT supplier, grnnumber, grndate from grnHead where 1 = 1 ";
            
            if (date.length() > 2) {
                SQL = SQL + " AND recvdate = '" + date + "'";
            } 
            
            if (filtertext.length() > 2) {
                SQL = SQL + " AND (supplier LIKE '%" + filtertext + "%'" + " OR grnnumber LIKE '%" + filtertext + "%'" + " OR grndate LIKE '%" + filtertext + "%')";
            }
            
            //ResultSet
            System.out.println("qry" + SQL);
            ResultSet rs = connekt.createStatement().executeQuery(SQL);

            while(rs.next()){
                //Iterate Row
                
                //ObservableList<Grn> data = FXCollections.observableArrayList();
                
                Grn cm = new Grn();
                cm.supplier.set(rs.getString("supplier"));
                cm.docnum.set(rs.getString("grnnumber"));
                cm.date.set(rs.getString("grndate"));
                
                row.add(cm);
            }
            
            //FINALLY ADDED TO TableView
            //tbldata.setItems(row);
           return row;
        
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
        return null;
     }   
     
        
     
     public ObservableList<GrnDetails> searchGRNDetails(String date)
     {         
          try {
         
            connekt =  dbconn.conn();
            Grnrow = FXCollections.observableArrayList();
             
            String GRNSQL = "select eantbl.id as id, material as material,des,cond_goodqty as good, cond_dmgboxqty as box,cond_dmgleakqty as leak, cond_dmgbrkqty as breaken, qty,countrycode as contry, eancode as ean, weight, remark, eantbl.lotnumber as Lotno, eantbl.doctype as doctype, eantbl.docnum as docnum, grnnum as GRNnum, doctbl.docstatus as docstatus, grntbl.grnstatus as grnstatus from grnItem eantbl left join grnSource doctbl on doctbl.docnum = eantbl.docnum and eantbl.doctype = doctbl.doctype left join grnSeries grntbl on grntbl.doctype = eantbl.doctype and grntbl.grnserlnum = SUBSTRING(eantbl.grnnum,6,4) where grnnum = '" + date + "' ORDER BY  eantbl.id DESC";
            
            
            
            
            //ResultSet
            System.out.println("qry" + GRNSQL);
            ResultSet rsset = connekt.createStatement().executeQuery(GRNSQL);

            while(rsset.next()){
            
               //ObservableList<Grn> data = FXCollections.observableArrayList();
                
               GrnDetails grd = new GrnDetails();
               
               grd.id.set(rsset.getString("id"));
               grd.material.set(rsset.getString("material"));
               grd.desc.set(rsset.getString("des"));
               grd.Good.set(rsset.getString("good"));
               grd.Box.set(rsset.getString("box"));
               grd.Leak.set(rsset.getString("leak"));
               grd.Breaken.set(rsset.getString("breaken"));
               grd.Qty.set(rsset.getString("qty"));             
               grd.Contry.set(rsset.getString("contry"));
               grd.EAN.set(rsset.getString("ean"));
               grd.Weight.set(rsset.getString("weight"));
               grd.Remark.set(rsset.getString("remark"));
               grd.Lotno.set(rsset.getString("Lotno"));
               grd.Doctype.set(rsset.getString("doctype"));
               grd.Docnum.set(rsset.getString("docnum"));
               grd.GRNnum.set(rsset.getString("GRNnum"));
               grd.Docstatus.set(rsset.getString("docstatus"));
               grd.GRNstatus.set(rsset.getString("grnstatus"));
             
               Grnrow.add(grd);
               
            }
            
            //FINALLY ADDED TO TableView
            //tbldata.setItems(row);
           return Grnrow;
        
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
        return null;
     }
     
     public ObservableList<GrnDetails> searchMaterial(String mat,String des)
     {
         try {
         
            connekt =  dbconn.conn();
            Grnrow = FXCollections.observableArrayList();
            
            
            /*remain to chanbge the query*/
            
            String MATSQL = "select eantbl.id as id, material as material,des,cond_goodqty as good, cond_dmgboxqty as box,cond_dmgleakqty as leak, cond_dmgbrkqty as breaken, qty,countrycode as contry, eancode as ean, weight, remark, eantbl.lotnumber as Lotno, eantbl.doctype as doctype, eantbl.docnum as docnum, grnnum as GRNnum, doctbl.docstatus as docstatus, grntbl.grnstatus as grnstatus from grnItem eantbl left join grnSource doctbl on doctbl.docnum = eantbl.docnum and eantbl.doctype = doctbl.doctype left join grnSeries grntbl on grntbl.doctype = eantbl.doctype and grntbl.grnserlnum = SUBSTRING(eantbl.grnnum,6,4) where 1 = 1";
            
            if (mat.length() > 2) {
                MATSQL = MATSQL + " AND material like  '%" + mat + "%'";
            } 
            
            if (des.length() > 3) {  
                MATSQL = MATSQL + " AND des like '%" + des + "%' ORDER BY  eantbl.id DESC ";
            }
            
            
        //ResultSet 
            
        System.out.println("qry" + MATSQL);
        ResultSet rsmat = connekt.createStatement().executeQuery(MATSQL);

            while(rsmat.next()){
            
            //ObservableList<Grn> data = FXCollections.observableArrayList();
               
            GrnDetails grd = new GrnDetails();
               
            grd.id.set(rsmat.getString("id"));
               grd.material.set(rsmat.getString("material"));
               grd.desc.set(rsmat.getString("des"));
               grd.Good.set(rsmat.getString("good"));
               grd.Box.set(rsmat.getString("box"));
               grd.Leak.set(rsmat.getString("leak"));
               grd.Breaken.set(rsmat.getString("breaken"));
               grd.Qty.set(rsmat.getString("qty"));             
               grd.Contry.set(rsmat.getString("contry"));
               grd.EAN.set(rsmat.getString("ean"));
               grd.Weight.set(rsmat.getString("weight"));
               grd.Remark.set(rsmat.getString("remark"));
               grd.Lotno.set(rsmat.getString("Lotno"));
               grd.Doctype.set(rsmat.getString("doctype"));
               grd.Docnum.set(rsmat.getString("docnum"));
               grd.GRNnum.set(rsmat.getString("GRNnum"));
               grd.Docstatus.set(rsmat.getString("docstatus"));
               grd.GRNstatus.set(rsmat.getString("grnstatus"));
           
               Grnrow.add(grd);
               
            }
            
           //FINALLY ADDED TO TableView
           //tbldata.setItems(row);
           return Grnrow;
        
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
        return null;
     }
     
             
public void GRNGenNumber(MouseEvent evt){

   
}

public int checkdoctypestatus(String currdoctype,String currdocnumtxt) throws Exception
{
    
 String docstat = "select docstatus from grnSource Where doctype = '" + currdoctype + "' and docnum = '" + currdocnumtxt + "'";
 System.out.println("uuuu" + docstat); 
 connekt =  dbconn.conn();
     
 ResultSet rsmat = connekt.createStatement().executeQuery(docstat);
    

if(rs.next())
    {
      String strstat = rsmat.getString("docstatus").toString(); 
            if(strstat.equals("Close"))
                {                  
            //JOptionPane.showMessageDialog(this, "Doctype "+ currdoctype +"with docnum" +  currdocnumtxt + "already used !!");      
             return 1;
                }  
            else if(strstat.equals("Break"))
                    {
             return 2;
                    }
            else if(strstat.equals("Start"))
                {
             return 3;
                }     
            } 
       else
        {
    return 0;  
        }  
 return 0;
}


/* Update doc status */

public void updatedocstat(String updatemat,String currdoctype) throws Exception
 {
connekt =  dbconn.conn();   
String updatematstr ="UPDATE grnSource SET docstatus = '" + updatemat + "'  WHERE doctype = '" + currdoctype + "' and docnum = '"+ currdoctype +"'" ; 
ResultSet rsmat = connekt.createStatement().executeQuery(updatematstr);
 }


public void insertdocstat(String matstat,String currdoctype,String currdocnumtxt) throws Exception
     {  
connekt =  dbconn.conn();   
String addnewmatstat = "insert into grnSource values ('"+ currdoctype +"','" + currdocnumtxt + "','" + matstat + "')";
System.out.println(addnewmatstat);
ResultSet rsmat = connekt.createStatement().executeQuery(addnewmatstat);
     }

public int checkGrnTypeStatus(String currdoctype,String currdocnumtxt) throws Exception
  {
     
    String docstat = "select TOP 1 grnserlnum, grnstatus from grnSeries Where doctype = '" + currdoctype + "' and  docnum = '" + currdocnumtxt + "' ORDER BY id DESC";
    System.out.println("tttt" + docstat); 
    connekt =  dbconn.conn();
   
    ResultSet rsmat = connekt.createStatement().executeQuery(docstat);
     
if(rsmat.next())
    {
       String strstat = rsmat.getString("grnstatus").trim(); 
       grnnum = rsmat.getString("grnserlnum").trim(); 
        
    if(strstat.equals("Close"))
                {         
                    
                 grnnum = getLastGrn(currdoctype);
                 //JOptionPane.showMessageDialog(this, "GRN Number with Doctype "+ currdoctype +"& docnum" +  currdocnumtxt + "already used !!");      
                  alertinfo.setTitle("Info");
                  alertinfo.setHeaderText(null);
                  alertinfo.setContentText("GRN Number with Doctype "+ currdoctype +"& docnum" +  currdocnumtxt + "already used !!");
                  alertinfo.showAndWait();
             grnnum = String.valueOf(Integer.parseInt(grnnum) + 1); 
                 return  1;         
              }  
            else if(strstat.equals("Break"))
                    {
                return 2;
                    }
            else if(strstat.equals("Start"))
                {
               return 3;
                }     
        } 
     else
        {
        grnnum = getLastGrn(currdoctype);
        
           return 0;
           
      //need to handle still
        }  
        return 0;
 }

public String getLastGrn(String currdoctype) throws Exception
{
    String isstr = "ISR";
    String grnlastnum = "";
    String grnnum = "";
    
    if(currdoctype.equals("ISR"))
      {
       grnlastnum = "select MAX(grnserlnum) as grnserlnummax from grnSeries Where doctype = '" + currdoctype + "'"; 
      }
      else
      {
       grnlastnum = "select MAX(grnserlnum) as grnserlnummax from grnSeries Where doctype IN ('MTN','LGR','AR')";   
      }
    
    System.out.println("tttt" + grnlastnum); 
    connekt =  dbconn.conn();
   
    ResultSet rsmat = connekt.createStatement().executeQuery(grnlastnum);
      
     if(rsmat.next())
          {
    grnnum = rsmat.getString("grnserlnummax").trim();    
          }
    System.out.println(grnnum);
    return grnnum;
    
}


public String grnDigit(String dig)
   {
       String myfourdigit = "";
      
           if (dig.length() == 1)
             return myfourdigit = "000" + dig;
           else if(dig.length() == 2)
             return myfourdigit = "00" + dig;  
           else if(dig.length() == 3)
             return myfourdigit = "0" + dig;
           else
               return dig;  
    }

public void grnNewSerialNumber(String currdoctype,String currdocnumtxt,String grnserialnum,String grnstatus) throws Exception
{
    String addnewgrnstat = "insert into grnSeries(doctype,docnum,grnserlnum,grnstatus) values ('"+ currdoctype +"','"+ currdocnumtxt +"','" + grnserialnum + "','"+grnstatus+"')";
    System.out.println(addnewgrnstat);
    connekt =  dbconn.conn();
    connekt.createStatement().executeUpdate(addnewgrnstat);
    
}

public void updategrndocstat(String currdoctype,String currdocnumtxt,String grnnum,String grnstat) throws Exception
 {
     
    String updategrnstr ="UPDATE grnSeries SET grnstatus = '" + grnstat + "'  WHERE doctype = '" + currdoctype + "' and docnum = '" + currdocnumtxt + "' and grnserlnum = '"+grnnum+"'"; ; 
    System.out.println(updategrnstr);
    connekt =  dbconn.conn();
    connekt.createStatement().executeUpdate(updategrnstr);
    
    
 }

public void updatedocstat(String updatemat,String currdoctype,String currdocnumtxt) throws Exception
 {
     
        String updatematstr ="UPDATE grnSource SET docstatus = '" + updatemat + "'  WHERE doctype = '" + currdoctype + "' and docnum = '"+ currdocnumtxt +"'" ; 
        System.out.println(updatematstr);
        connekt =  dbconn.conn();
        connekt.createStatement().executeUpdate(updatematstr); 
        
 }




public void saveGrnHead(String grnnumber,String compcode,String compname,String supplier,String suppinvc,String ctnnum,String grndate,String recvdate,String isrnumber,String prnumber,String sealnumber,String shipmode,String countsize,String skids,String weight) throws Exception {
     String addnewgrnstat = "insert into grnHead(grnnumber,compcode,compname,supplier,suppinvc,ctnnum,grndate,recvdate,isrnumber,prnumber,sealnumber,shipmode,countsize,skids,weight) values ('"+ grnnumber +"','"+ compcode +"','" + compname + "','"+supplier+"','"+ suppinvc+"','"+ ctnnum+"','"+ grndate+"','"+ recvdate+"','"+ isrnumber+"','"+ prnumber+"','"+ sealnumber+"','"+ shipmode+"','"+ countsize+"','"+ skids+"','"+ weight+"')";
     System.out.println(addnewgrnstat);
     
     connekt =  dbconn.conn();
     connekt.createStatement().executeUpdate(addnewgrnstat);
    
    //SC.defaultGrnList();
    
}

    

  

    



}