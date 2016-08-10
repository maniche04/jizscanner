/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import jizanscanner.controllers.ScannerGrnSetupController;

/**
 * @author santosh
 */
public class GrnModal {

    // Initialize year with two digits. To be used when generating new GRN Number.
    DateFormat yeartwodig = new SimpleDateFormat("yy"); // Just the year, with last 2 digits
    String formatyeartwodig = yeartwodig.format(Calendar.getInstance().getTime());
    
    HttpClient req = new HttpClient();
    CustomeControls Ccontrol = new CustomeControls();
    DbConnection dbconn = new DbConnection();
    GlobalVariable gv = new GlobalVariable();
    Connection connekt = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static ObservableList<Grn> row;
    private static ObservableList<GrnDetails> Grnrow;
    Alert alertinfo = new Alert(Alert.AlertType.INFORMATION);
    public String grnnum = "";
    String res = "";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    //ScannerGrnSetupController SC = new ScannerGrnSetupController();
    //public static javax.swing.JTextField entereancode;

    /*start -  for data setter and getter from child to parent */
    private final StringProperty text = new SimpleStringProperty();

    public StringProperty textProperty() {
        return text;
    }

    public final String getText() {
        return textProperty().get();
    }

    public final void setText(String text) {
        textProperty().set(text);
    }

    // Searches for the GRNs based on input parameter provided. Used for GRN Listing
    public ObservableList<Grn> searchGRN(String date, String filtertext) {
        try {

            connekt = dbconn.conn();
            row = FXCollections.observableArrayList();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT supplier, grnnumber, grndate from grnHead where 1 = 1 ";

            if (filtertext.length() > 2) {
                SQL = SQL + " AND (supplier LIKE '%" + filtertext + "%'" + " OR grnnumber LIKE '%" + filtertext + "%'" + " OR grndate LIKE '%" + filtertext + "%')";
            } else {
                if (date.length() > 2) {
                    SQL = SQL + " AND recvdate = '" + date + "'";
                }
            }

            //ResultSet
            System.out.println("qry" + SQL);
            ResultSet rs = connekt.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                //Iterate Row
                //ObservableList<Grn> data = FXCollections.observableArrayList();
                /*
                 **
                 */
                Grn cm = new Grn();
                cm.supplier.set(rs.getString("supplier"));
                cm.docnum.set(rs.getString("grnnumber"));
                cm.date.set(rs.getString("grndate"));
                row.add(cm);
            }

            //FINALLY ADDED TO TableView
            //tbldata.setItems(row);
            return row;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return null;
    }
    
    // Search for GRNs based on date provided.
    public ObservableList<GrnDetails> searchGRNDetails(String date) {
        try {

            connekt = dbconn.conn();
            Grnrow = FXCollections.observableArrayList();

            String GRNSQL = "select eantbl.id as id, material as Material,des,cond_goodqty as good, cond_dmgboxqty as box,cond_dmgleakqty as leak, cond_dmgbrkqty as breaken, qty,countrycode as contry, eancode as ean, weight, remarks, eantbl.lotnumber as Lotno, eantbl.doctype as doctype, eantbl.docnum as docnum, grnnum as GRNnum, doctbl.grnstatus as docstatus, doctbl.grnstatus as grnstatus from grnItem eantbl left join grnSeries doctbl on doctbl.grntype = SUBSTRING(eantbl.grnnum,0,4)  and  doctbl.grnserlnum = SUBSTRING(eantbl.grnnum,6,4) where grnnum = '" + date + "' ORDER BY  eantbl.id ASC";

            //ResultSet
            System.out.println("qry" + GRNSQL);
            ResultSet rsset = connekt.createStatement().executeQuery(GRNSQL);

            while (rsset.next()) {
               //ObservableList<Grn> data = FXCollections.observableArrayList();

                GrnDetails grd = new GrnDetails();

                grd.id.set(rsset.getString("id"));
                grd.Material.set(rsset.getString("Material"));
                grd.desc.set(rsset.getString("des"));
                grd.Good.set(rsset.getString("good"));
                grd.Box.set(rsset.getString("box"));
                grd.Leak.set(rsset.getString("leak"));
                grd.Breaken.set(rsset.getString("breaken"));
                grd.Qty.set(rsset.getString("qty"));
                grd.Contry.set(rsset.getString("contry"));
                grd.EAN.set(rsset.getString("ean"));
                grd.Weight.set(rsset.getString("weight"));
                grd.Remark.set(rsset.getString("remarks"));
                grd.Lotno.set(rsset.getString("Lotno"));
                grd.Doctype.set(rsset.getString("doctype"));
                grd.Docnum.set(rsset.getString("docnum"));
                grd.GRNnum.set(rsset.getString("GRNnum"));
                grd.Docstatus.set(rsset.getString("docstatus"));
                grd.GRNstatus.set(rsset.getString("grnstatus"));

                Grnrow.add(grd);
                 //Grnrow.add(4, grd);
            }

            /*
             */
            //FINALLY ADDED TO TableView
            //tbldata.setItems(row);
            return Grnrow;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return null;
    }

    //Search for material data based on the input fields
    public ObservableList<GrnDetails> searchMaterial(String mat, String des, String grnnum, Boolean isglobal) {
        try {

            connekt = dbconn.conn();
            Grnrow = FXCollections.observableArrayList();

            /* remain to chanbge the query */
            
            String MATSQL = "select eantbl.id as id, material as Material,des,cond_goodqty as good, cond_dmgboxqty as box,cond_dmgleakqty as leak, cond_dmgbrkqty as breaken, qty,countrycode as contry, eancode as ean, weight, remarks, eantbl.lotnumber as Lotno, eantbl.doctype as doctype, eantbl.docnum as docnum, grnnum as GRNnum, doctbl.grnstatus as docstatus, doctbl.grnstatus as grnstatus from grnItem eantbl left join grnSeries doctbl on doctbl.grntype = SUBSTRING(eantbl.grnnum,0,4)  and  doctbl.grnserlnum = SUBSTRING(eantbl.grnnum,6,4) where 1 = 1";
            
            if (mat.length() > 2) {
                MATSQL = MATSQL + " AND material like  '%" + mat + "%'";
            }

            if (des.length() > 3) {
                MATSQL = MATSQL + " AND des like '%" + des + "%'";
            }

            if (isglobal == false) {
                MATSQL = MATSQL + " AND grnnum = '" + grnnum + "'";
            }

            MATSQL = MATSQL + " ORDER BY  eantbl.id asc ";

            //ResultSet 
            System.out.println("qry" + MATSQL);
            ResultSet rsmat = connekt.createStatement().executeQuery(MATSQL);

            while (rsmat.next()) {

            //ObservableList<Grn> data = FXCollections.observableArrayList();
                GrnDetails grd = new GrnDetails();

                grd.id.set(rsmat.getString("id"));
                grd.Material.set(rsmat.getString("Material"));
                grd.desc.set(rsmat.getString("des"));
                grd.Good.set(rsmat.getString("good"));
                grd.Box.set(rsmat.getString("box"));
                grd.Leak.set(rsmat.getString("leak"));
                grd.Breaken.set(rsmat.getString("breaken"));
                grd.Qty.set(rsmat.getString("qty"));
                grd.Contry.set(rsmat.getString("contry"));
                grd.EAN.set(rsmat.getString("ean"));
                grd.Weight.set(rsmat.getString("weight"));
                grd.Remark.set(rsmat.getString("remarks"));
                grd.Lotno.set(rsmat.getString("Lotno"));
                grd.Doctype.set(rsmat.getString("doctype"));
                grd.Docnum.set(rsmat.getString("docnum"));
                grd.GRNnum.set(rsmat.getString("GRNnum"));
                grd.Docstatus.set(rsmat.getString("docstatus"));
                grd.GRNstatus.set(rsmat.getString("grnstatus"));

                Grnrow.add(grd);

            }

         // FINALLY ADDED TO TableView
         // tbldata.setItems(row);
         /* 45845 */ 
            
        return Grnrow;
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return null;
    }
    
    /*
    
    */

    // Checks the Document Type Status
    public int checkdoctypestatus(String currdoctype, String currdocnumtxt) throws Exception {

         String docstat = "select docstatus from grnSource Where doctype = '" + currdoctype + "' and docnum = '" + currdocnumtxt + "'";
         System.out.println("uuuu" + docstat);
         System.out.println("uuuu" + docstat);
         connekt = dbconn.conn();
        
        ResultSet rsmat = connekt.createStatement().executeQuery(docstat);

        if (rs.next()) {
            String strstat = rsmat.getString("docstatus").toString();
            if (strstat.equals("Close")) {
                //JOptionPane.showMessageDialog(this, "Doctype "+ currdoctype +"with docnum" +  currdocnumtxt + "already used !!");      
                return 1;
            } else if (strstat.equals("Break")) {
                return 2;
            } else if (strstat.equals("Start")) {
                return 3;
            }
        } else {
            return 0;
        }
        return 0;
    }


    // Update Document Status
    public void updatedocstat(String updatemat, String currdoctype) throws Exception {
        connekt = dbconn.conn();
        String updatematstr = "UPDATE grnSource SET docstatus = '" + updatemat + "'  WHERE doctype = '" + currdoctype + "' and docnum = '" + currdoctype + "'";
        ResultSet rsmat = connekt.createStatement().executeQuery(updatematstr);
    }

    // Insert Document Status
    public void insertdocstat(String matstat, String currdoctype, String currdocnumtxt) throws Exception {
        connekt = dbconn.conn();
        String addnewmatstat = "insert into grnSource values ('" + currdoctype + "','" + currdocnumtxt + "','" + matstat + "')";
        System.out.println(addnewmatstat);
        ResultSet rsmat = connekt.createStatement().executeQuery(addnewmatstat);
    }
    
    // Checks if the GRN has been already used. Used when creating GRN
    public int checkGrnTypeStatus(String currdoctype, String currdocnumtxt, String curryear) throws Exception {

//        String docstat = "select TOP 1 grnserlnum, grnstatus from grnSeries Where doctype = '" + currdoctype + "' and  docnum = '" + currdocnumtxt + "' AND grnyear = '" + curryear + "' ORDER BY id asc";
//        System.out.println("tttt" + docstat);
//        connekt = dbconn.conn();
//
//        ResultSet rsmat = connekt.createStatement().executeQuery(docstat);

//            if (rsmat.next()) {
//              String strstat = rsmat.getString("grnstatus").trim();
//            grnnum = rsmat.getString("grnserlnum").trim();
//            grnnum = String.valueOf(Integer.parseInt(grnnum) + 1);
//            grnnum = getLastGrn(currdoctype);
//            return 1;
//
//        if (strstat.equals("Close")) {
////
////      grnnum = getLastGrn(currdoctype);
////      alertinfo.setTitle("Info");
////      alertinfo.setHeaderText(null);
////      alertinfo.setContentText("GRN Number with document type " + currdoctype + " & document number " + currdocnumtxt + " has already been used.");
////      alertinfo.showAndWait();
////      grnnum = String.valueOf(Integer.parseInt(grnnum) + 1);
////      return 1;
////      } else if (strstat.equals("Break")) {
        
////      return 2;
        
////      } else if (strstat.equals("Start")) {
        
////      return 3;
        
////        }
//        } else {
//            grnnum = getLastGrn(currdoctype);
//
//            return 0;
//
//            //need to handle still
//        }
        System.out.println("The last GRN is being checked for " + currdoctype);
        grnnum = getLastGrn(currdoctype);
        grnnum = String.valueOf(Integer.parseInt(grnnum));
        return 0;
    }

    // Gets the Last GRN Number. Used when creating a new GRN.
    public String getLastGrn(String currdoctype) throws Exception {
        String isstr = "ISR";
        String grnlastnum = "";
        String grnnum = "";
        if (currdoctype.equals("ISR")) {
            grnlastnum = "select ISNULL(MAX(grnserlnum),0) as grnserlnummax from grnSeries Where doctype = '" + currdoctype + "' AND grnyear = '" + formatyeartwodig + "'";
        } else {
            grnlastnum = "select ISNULL(MAX(grnserlnum),0) as grnserlnummax from grnSeries Where doctype IN ('MTN','LGR','AR') AND grnyear = '" + formatyeartwodig + "'";
        }
        System.out.println("tttt" + grnlastnum);
        connekt = dbconn.conn();

        ResultSet rsmat = connekt.createStatement().executeQuery(grnlastnum);

        if (rsmat.next()) {
            grnnum = rsmat.getString("grnserlnummax").trim();
        } 
        System.out.println(grnnum);
        return grnnum;
    }

        //Formats the GRN to be of four digits.
    public String grnDigit(String dig) {
        String myfourdigit = "";

        if (dig.length() == 1) {
            return myfourdigit = "000" + dig;
        } else if (dig.length() == 2) {
            return myfourdigit = "00" + dig;
        } else if (dig.length() == 3) {
            return myfourdigit = "0" + dig;
        } else {
            return dig;
        }
    }

        //Inserts a new GRN Number in the Series Table
    public void grnNewSerialNumber(String currdoctype, String currdocnumtxt, String grnserialnum, String grnstatus, String grntype) throws Exception {
        System.out.println("ABOUT TO INSERT INTO SERIES!");
        String addnewgrnstat = "insert into grnSeries(doctype,docnum,grnserlnum,grnyear, grnstatus, grntype) values ('" + currdoctype + "','" + currdocnumtxt + "','" + grnserialnum + "','" + formatyeartwodig + "','" + grnstatus + "','" + grntype + "')";

        System.out.println(addnewgrnstat);
                System.out.println("INSERTED INTO SERIES!");
        connekt = dbconn.conn();
        connekt.createStatement().executeUpdate(addnewgrnstat);

    }

      //Updates the Document Status (Document Level)
    public void updategrndocstat(String currdoctype, String currdocnumtxt, String grnnum, String grnstat, String grnyear) throws Exception {
        // CURRENTLY UNUSED
        String updategrnstr = "UPDATE grnSeries SET grnstatus = '" + grnstat + "'  WHERE doctype = '" + currdoctype + "' and docnum = '" + currdocnumtxt + "' and grnserlnum = '" + grnnum + "' AND grnyear = '" + grnyear + "'";;
        System.out.println(updategrnstr);
        connekt = dbconn.conn();
        connekt.createStatement().executeUpdate(updategrnstr);

    }

    //Updates the Document Status (Currently Unused)
    public void updateDocumentStatus(String updatemat, String currdoctype, String currdocnumtxt, String abc, String xyz) throws Exception {
        String updatematstr = "UPDATE grnSource SET docstatus = '" + updatemat + "'  WHERE doctype = '" + currdoctype + "' and docnum = '" + currdocnumtxt + "'";
        System.out.println(updatematstr);
        connekt = dbconn.conn();
        connekt.createStatement().executeUpdate(updatematstr);

    }

    //Saves the GRN Head
    public int saveGrnHead(String grnnumber, String compcode, String compname, String supplier, String suppinvc, String ctnnum, String grndate, String recvdate, String isrnumber, String prnumber, String sealnumber, String shipmode, String countsize, String skids, String weight) throws Exception {
        try {
            String addnewgrnstat = "insert into grnHead(grnnumber,compcode,compname,supplier,suppinvc,ctnnum,grndate,recvdate,isrnumber,prnumber,sealnumber,shipmode,countsize,skids,weight) values ('" + grnnumber + "','" + compcode + "','" + compname + "','" + supplier + "','" + suppinvc + "','" + ctnnum + "','" + grndate + "','" + recvdate + "','" + isrnumber + "','" + prnumber + "','" + sealnumber + "','" + shipmode + "','" + countsize + "','" + skids + "','" + weight + "')";
            System.out.println(addnewgrnstat);

            connekt = dbconn.conn();
            connekt.createStatement().executeUpdate(addnewgrnstat);
            return 1;
        } catch (Exception ex) {
            System.out.println("Exception while saving" + ex);
        }

        return 0;
    //SC.defaultGrnList();  
    }

    //insert scanned material to transaction table(if is its exist materail) and if new materail then both in master and transaction table both
    public String insertneweancode(TableView currtabl, ObservableList<Grn> allRow, String curryearToDigit, String eancode, String desc, String matcode, String qty, String contry, String wght) throws Exception {
         //if(desc.length() > 0 && matcode.length() > 0 && qty.length() > 0 && contry.length() > 0 && wght.length() > 0)
         //{
         //String grntxt = "";
         //String currgrntxt = "";
         //String goodqtystr = qty;
         //String dmgboxqtystr = "";
         //String dmgleakqtystr = "";
        //String dmgbrkqtystr = "";
//                String remarkstr = "";
//                String lotnum = "";
//                String currdoctype = "";
//           
//             if(currtabl.getSelectionModel().getSelectedItem() != null){
//                    int currow = currtabl.getSelectionModel().getFocusedIndex();
//                    //System.out.println("docnum value:"+ allRow.get(currow).getDocnum());   
//                    grntxt = allRow.get(currow).getDocnum();
//                    currdoctype = allRow.get(currow).getGrno();
//                    System.out.println("grntxt :"+ grntxt +"& currdoctype :" + currdoctype );
//                                }
//             
//                    if(!grntxt.isEmpty() && currdoctype.equals("ISR") )
//                             {
//                        currgrntxt = "IMP. "+ grntxt.trim().substring(5,9)+ " - "+ curryearToDigit;
//                             }
//                    else if(!grntxt.isEmpty() && currdoctype.equals("LGR"))
//                            {
//                        currgrntxt = "LOC. "+grntxt.trim().substring(5,9)+" - "+curryearToDigit;
//                             }
//                    else if(!grntxt.isEmpty() && currdoctype.equals("MTN"))
//                             {
//                        currgrntxt = "LOC. "+grntxt.trim().substring(5,9)+" - "+curryearToDigit;
//                             }
//                    else if(!grntxt.isEmpty() && currdoctype.equals("AR"))
//                             {
//                        currgrntxt = "LOC. "+grntxt.trim().substring(5,9)+" - "+curryearToDigit;
//                             }
//                    else
//                            {
//                        currgrntxt = "";
//                            }
        /* Login conver in post method thats why not checked upper part */        
            String respoflag = req.MaterialCheckPost(eancode, desc, matcode, qty, contry, wght);
            return respoflag;        
        /* Login conver in post method thats why not checked upper part */

    }
    
    public String syncReq(String url) throws Exception
    {
        String reqstr =  req.getRequest(url);
        return reqstr;
    }
    
public String isMatScanned(String ean,String mat,String des, String grnnum) throws Exception
{
    
        
        String recexist = "";
        String checksql = "select * from grnItem where grnnum = '" + grnnum + "' AND ((eancode = '" + ean + "' AND eancode != 'NA') OR (eancode = 'NA' and material = '" + mat + "'))";
        connekt = dbconn.conn();
        ResultSet rscomt = connekt.createStatement().executeQuery(checksql);
        

         System.out.println("Sql query"+ checksql);

        if(rscomt.next()) {
           recexist = "1 record found"; 
        }

  return recexist;        
}

// Updates the GRN with comments
public void saveHeadComment(String grnnum, String sealnumber, String shipmode,String countsize, String skids,String prnumber,String weight,String ctnnum, String comment) throws Exception {

        String commenttxt = "UPDATE grnHead SET sealnumber = '"+ sealnumber +"', shipmode = '"+ shipmode +"', countsize = '" + countsize + "', skids = '"+ skids +"', prnumber = '"+ prnumber +"', weight = '"+ weight + "', ctnnum = '"+ ctnnum +"', comment = '" + comment + "' WHERE grnnumber = '" + grnnum + "'";
        System.out.println(commenttxt);
        connekt = dbconn.conn();
        connekt.createStatement().executeUpdate(commenttxt);

    }

    // Gets the current comment from GRN Head
    public String getHeadComment(String grnnum) throws Exception {

        String Comment = "";
        String sealnumber = "";
        String shipmode = "";
        String countsize = "";
        String skids = "";
        String prnumber = "";
        String weight = "";
        String ctnnum = "";
        
        String totstr = "";
        String getcommenttxt = "select isnull(sealnumber,'') as sealnumber, isnull(shipmode,'') as shipmode, isnull(countsize,'') as countsize, isnull(skids,'') as skids, isnull(prnumber,'') as prnumber, isnull(weight,'') as weight, isnull(ctnnum,'') as ctnnum, isnull(comment,'') as comment from grnHead WHERE grnnumber = '" + grnnum + "'";
        System.out.println(getcommenttxt);

        connekt = dbconn.conn();
        ResultSet rscomt = connekt.createStatement().executeQuery(getcommenttxt);

        if (rscomt.next()) {            
            
             sealnumber = rscomt.getString("sealnumber").trim();
             shipmode =  rscomt.getString("shipmode").trim();
             countsize = rscomt.getString("countsize").trim();
             skids =  rscomt.getString("skids").trim();
             prnumber = rscomt.getString("prnumber").trim();
             weight = rscomt.getString("weight").trim();
             ctnnum  = rscomt.getString("ctnnum").trim();
             Comment = rscomt.getString("comment").trim();
             
        }
        
        //if making 2 entry for 1 grn number then this section will have problem
        
        totstr = sealnumber + "," +  shipmode + "," + countsize +","+ skids + "," + prnumber + "," + weight + "," + ctnnum + "," + Comment;
        System.out.println("Full string:" + totstr);
        return totstr;

    }

    // Gets the details from GRN Head
    public ArrayList<String> getGrnHeadInfo(String GrnNum) throws Exception {

        ArrayList<String> grnheadinfo = new ArrayList<String>();
        String repoSql = "select compname, suppinvc, isnull(ctnnum,'') as ctnnum, grnnumber, grndate, recvdate, supplier, isnull(comment,'') as comment from grnHead where grnnumber = '" + GrnNum + "'";

        connekt = dbconn.conn();
        ResultSet rshead = connekt.createStatement().executeQuery(repoSql);

        if (rshead.next()) {

            grnheadinfo.add(rshead.getString("compname").trim());
            grnheadinfo.add(rshead.getString("suppinvc").trim());
            grnheadinfo.add(rshead.getString("ctnnum").trim());
            grnheadinfo.add(rshead.getString("grnnumber").trim());
            grnheadinfo.add(rshead.getString("grndate").trim());
            grnheadinfo.add(rshead.getString("recvdate").trim());
            grnheadinfo.add(rshead.getString("supplier").trim());
            grnheadinfo.add(rshead.getString("comment").trim());

        }
        return grnheadinfo;
    }

    public ArrayList<String> getGrnImportHeadInfo(String GrnNum) throws Exception {

        ArrayList<String> grnheadinfo = new ArrayList<String>();

        String repoSql = "select compname, grnnumber, grndate, recvdate, prnumber, sealnumber, shipmode, countsize, skids, weight, isnull(comment,'') as comment from grnHead where grnnumber = '" + GrnNum + "'";

        connekt = dbconn.conn();
        ResultSet rshead = connekt.createStatement().executeQuery(repoSql);

        if (rshead.next()) {
            grnheadinfo.add(rshead.getString("compname").trim());
            grnheadinfo.add(rshead.getString("grnnumber").trim());
            grnheadinfo.add(rshead.getString("grndate").trim());
            grnheadinfo.add(rshead.getString("recvdate").trim());
            grnheadinfo.add(rshead.getString("grndate").trim());
            grnheadinfo.add(rshead.getString("recvdate").trim());
            grnheadinfo.add(rshead.getString("prnumber").trim());
            grnheadinfo.add(rshead.getString("sealnumber").trim());
            grnheadinfo.add(rshead.getString("shipmode").trim());
            grnheadinfo.add(rshead.getString("skids").trim());
            grnheadinfo.add(rshead.getString("weight").trim());
            grnheadinfo.add(rshead.getString("comment").trim());
        }
        return grnheadinfo;
    }

    public void infouserType() throws Exception {

        String usertype = "select TOP 1 usertype FROM eancodeuser_role WHERE username = '" + gv.getGlobusername() + "'";
        System.out.println("sqlqry" + usertype);
        connekt = dbconn.conn();
        ResultSet rsuser = connekt.createStatement().executeQuery(usertype);

        if (rsuser.next()) {
            System.out.println("datebased test passed");
            gv.setUsertype(rsuser.getString(1));
        }

    }

    // Inserts the GRN Item Record into the System
    public void insertIntogrnITem(String newean, String empromismat, String des, String currdoctype, String currdocnumtxt, String goodqtystr, String dmgboxqtystr, String dmgleakqtystr, String dmgbrkqtystr, String qty, String whgt, String ctry, String remarkstr, String currusername, String lotnum, String currgrntxt) throws Exception {
        String currdate = dateFormat.format(date);
        String addnewrow = "insert into grnItem(date,eancode,material,des,doctype,docnum,cond_goodqty,cond_dmgboxqty,cond_dmgleakqty, cond_dmgbrkqty,qty,weight,countrycode,remarks,username,lotnumber,grnnum) values ('" + currdate + "','" + newean + "','" + empromismat + "','" + des + "',"
                + "'" + currdoctype + "','" + currdocnumtxt + "','" + goodqtystr + "','" + dmgboxqtystr + "','" + dmgleakqtystr + "','" + dmgbrkqtystr + "','" + qty + "','" + whgt + "','" + ctry + "','" + remarkstr + "','" + currusername + "','" + lotnum + "','" + currgrntxt + "')";

        System.out.println("insert into grnItem");
        System.out.println(addnewrow);
        connekt = dbconn.conn();
        connekt.createStatement().executeUpdate(addnewrow);
    }

    // Delets the Selected row from System
    public void deleteRow(String rowid) throws Exception {
        String currdate = dateFormat.format(date);
        String deleterow = "delete from grnItem where id = '" + rowid + "'";

        System.out.println("row deleted");
        System.out.println(deleterow);
        connekt = dbconn.conn();
        connekt.createStatement().executeUpdate(deleterow);
    }

    // Updates the GRN Item row
    public void updateRow(String matcode, String desc, String good, String box, String leak, String breaken, String qty, String wght, String cntry, String ean, String remark, String lot, String doctype, String docnum, String grnnum, String id) throws Exception {

        String currdate = dateFormat.format(date);
        String strupdate = "UPDATE grnItem SET material = '" + matcode + "', des = '" + desc + "', cond_goodqty  = '" + good + "', cond_dmgboxqty = '" + box + "', cond_dmgleakqty = '" + leak + "', cond_dmgbrkqty = '" + breaken + "',"
                + " qty = '" + qty + "',countrycode = '" + cntry + "',eancode = '" + ean + "', weight = '" + wght + "', remarks = '" + remark + "', lotnumber = '" + lot + "', doctype = '" + doctype + "', docnum = '" + docnum + "', grnnum = '" + grnnum + "' WHERE id = '" + id + "'";

        System.out.println("row update");
        System.out.println(strupdate);
        connekt = dbconn.conn();
        connekt.createStatement().executeUpdate(strupdate);
    }

    // Updates the GRN Status
    public void updateGRNStatus(String updatemat, String currdoctype, String currdocnumtxt, String grnyear) throws Exception {
        String updatematstr = "UPDATE grnSeries SET grnStatus = '" + updatemat + "'  WHERE grntype = '" + currdoctype + "' and grnserlnum = '" + currdocnumtxt + "' AND grnyear = '" + grnyear + "'";

        System.out.println("status update");
        System.out.println(updatematstr);
        connekt = dbconn.conn();
        connekt.createStatement().executeUpdate(updatematstr);
    }

//    public void updateDocumentStatus(String grnstat,String currdoctype,String docnum, Integer abc) throws Exception
//        { 
//   
//     String updategrnstr ="UPDATE grnSeries SET grnstatus = '" + grnstat + "'  WHERE doctype = '" + currdoctype + "' and docnum = '" + docnum + "'";// and grnserlnum = '"+grnserlnum+"'";
//     System.out.println("status update");
//     System.out.println(updategrnstr);
//     connekt =  dbconn.conn();
//     connekt.createStatement().executeUpdate(updategrnstr);   
//    
//        }
    
    // Returns the Current Status of the GRN
 public String getGRNStatus(String currdoctype, String docnum, String grnyear) throws Exception {
        String newStat = "";
        String getDocStat = "SELECT grnstatus from grnSeries WHERE grntype = '" + currdoctype + "' and grnserlnum = '" + docnum + "' AND grnyear = '" + grnyear + "'";// and grnserlnum = '"+grnserlnum+"'";
        System.out.println("doc status");
        System.out.println(getDocStat);
        connekt = dbconn.conn();
        ResultSet stat = connekt.createStatement().executeQuery(getDocStat);

        if (stat.next()) {

            newStat = stat.getString(1);
        }

        return newStat;
  }

}
