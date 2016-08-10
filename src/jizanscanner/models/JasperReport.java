/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.FontKey;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.PdfFont;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.swing.JRViewer;


/**
 *
 * @author santosh
 */
public class JasperReport {
  
    GlobalVariable gv = new GlobalVariable();
    
public void JasperGenerator(javafx.collections.ObservableList<GrnDetails> tableobserv,String title, String suppinvc,String ctn,String grnnum,String grndate,String recddate, String titlecompsec,String notetxt,String checktxt) throws Exception
{
 
int intval = 0;    
int start = 0;
String datos = "";
int grandtotal = 0;
int grdtotbox = 0;
int grdtotleak = 0;
int grdtotbrkn = 0;
int grdtotdmg = 0;
int countitem = 1;

int totboxval = 0;
int totleakval = 0;
int totbrknval = 0;
int grdtotval = 0;


List Resultados = new ArrayList();

JasperData collectdate;
Resultados.clear();



for(start = 0; start < tableobserv.size();start++)
{
    
totboxval = tableobserv.get(start).getBox().equals("") ? 0 : Integer.parseInt(String.valueOf(tableobserv.get(start).getBox()));
grdtotbox = grdtotbox + totboxval;
totleakval = tableobserv.get(start).getLeak().equals("") ? 0 : Integer.parseInt(String.valueOf(tableobserv.get(start).getLeak()));
grdtotleak = grdtotleak +  totleakval;
totbrknval = tableobserv.get(start).getBreaken().equals("") ? 0 : Integer.parseInt(String.valueOf(tableobserv.get(start).getBreaken()));
grdtotbrkn = grdtotbrkn + totbrknval; 
grdtotval = tableobserv.get(start).getQty().equals("") ? intval : Integer.parseInt(String.valueOf(tableobserv.get(start).getQty()));
grandtotal = grandtotal + grdtotval;

collectdate = new JasperData(String.valueOf(countitem++),      //table.getValueAt(start,2)
                       String.valueOf(tableobserv.get(start).getDesc()),
                       String.valueOf(tableobserv.get(start).getRemark()),  
                       String.valueOf(tableobserv.get(start).getGood()),
                       String.valueOf(tableobserv.get(start).getBox()),
                       String.valueOf(tableobserv.get(start).getLeak()),
                       String.valueOf(tableobserv.get(start).getBreaken()),
                       String.valueOf(tableobserv.get(start).getQty()),  //total
                       String.valueOf(tableobserv.get(start).getContry()), 
                       String.valueOf(tableobserv.get(start).getEAN()),
                       String.valueOf(tableobserv.get(start).getWeight()),
                       String.valueOf(tableobserv.get(start).getLotno()));

//grandtotal = grandtotal + Integer.parseInt(String.valueOf(table.getValueAt(start,7)));
Resultados.add(collectdate);

}


Map map = new HashMap();
JDialog reporte = new JDialog();
reporte.setSize(1100,700);
reporte.setLocationRelativeTo(null);

//reorte.setTtile("Reporte asdf");

if(notetxt.length() > 0)
{
  map.put("notelbl","Note:");
  map.put("note",notetxt);
}
else
{
  map.put("notelbl","");
  map.put("note","");  
}

map.put("inputtitle",title);//Parameter value is title
map.put("suppinvoice",suppinvc);
map.put("ctns",ctn);

if(grnnum.substring(0, 3).equals("LOC"))
{
   grnnum = grnnum.replace("MTN", "LOC");
   System.out.println("tttt"+grnnum);
   map.put("doctypelbl","LOC");    
}

 
    map.put("grnnum",grnnum);
    map.put("grndate",grndate);
    System.out.println("date"+grndate);
    map.put("recddate",recddate);
    map.put("recvtitle",titlecompsec);
    map.put("grndtot",String.valueOf(grandtotal));
    map.put("boxtot",String.valueOf(grdtotbox));
    map.put("leaktot",String.valueOf(grdtotleak));
    map.put("brkntot",String.valueOf(grdtotbrkn));
    map.put("preparedby",gv.getGlobusername());
    map.put("dmgtot",String.valueOf(grdtotbox + grdtotleak + grdtotbrkn ));
    map.put("checkby",checktxt);

//map.put("Fetch","2763238");//Parameter value is Fetch
   
   JasperPrint jPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("jizanscanner/reports/report.jasper"),map, new JRBeanCollectionDataSource(Resultados));    


//    JRViewer jv = new JRViewer(jPrint);
//    reporte.getContentPane().add(jv);
//    reporte.setVisible(true);
    
  
  // String reportName = "D:\\test\\myreport";
   
  JRExporter exporter = new JRPdfExporter();
  FileOutputStream file = new FileOutputStream("D:\\TMP\\"+grnnum+".pdf");
  

  JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "/images/ubuntu.ttf");
  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
  exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, file); // your output goes here

  exporter.exportReport();
  file.close();
  
  File myFile = new File("D:\\TMP\\"+grnnum+".pdf");
  Desktop.getDesktop().open(myFile);

}


public void JasperGeneratorImportGrn(javafx.collections.ObservableList<GrnDetails> tableobserv,String title,String grnnum,String grndate,String recddate,String isr,String prnum,String seal,String shipmode,String countsize,String skids,String wgt,String impnote, String checktxt) throws Exception
{
    
int intval = 0;    
int start = 0;
String datos = "";
int grandtotal = 0;
int grdtotbox = 0;
int grdtotleak = 0;
int grdtotbrkn = 0;
int grdtotdmg = 0;
int countitem = 1;
int totboxval = 0;
int totleakval = 0;
int totbrknval = 0;
int grdtotval = 0;

List Resultados = new ArrayList();

ImpotGrnBean collectdate1;
Resultados.clear();

for(start = 0; start< tableobserv.size();start++)
{
    
totboxval = tableobserv.get(start).getBox().equals("") ? 0 : Integer.parseInt(String.valueOf(tableobserv.get(start).getBox()));
grdtotbox = grdtotbox + totboxval;
totleakval = tableobserv.get(start).getLeak().equals("") ? 0 : Integer.parseInt(String.valueOf(tableobserv.get(start).getLeak()));
grdtotleak = grdtotleak +  totleakval;
totbrknval = tableobserv.get(start).getBreaken().equals("") ? 0 : Integer.parseInt(String.valueOf(tableobserv.get(start).getBreaken()));
grdtotbrkn = grdtotbrkn + totbrknval; 
grdtotval = tableobserv.get(start).getQty().equals("") ? intval : Integer.parseInt(String.valueOf(tableobserv.get(start).getQty()));
grandtotal = grandtotal + grdtotval;

collectdate1 = new ImpotGrnBean( String.valueOf(countitem++), //table.getValueAt(start,2)
                       String.valueOf(tableobserv.get(start).getDesc()),
                       String.valueOf(tableobserv.get(start).getGood()),
                       String.valueOf(tableobserv.get(start).getBox()),
                       String.valueOf(tableobserv.get(start).getLeak()),
                       String.valueOf(tableobserv.get(start).getBreaken()),
                       String.valueOf(tableobserv.get(start).getQty()), //total
                       String.valueOf(tableobserv.get(start).getContry()), 
                       String.valueOf(tableobserv.get(start).getLotno()),
                       String.valueOf(tableobserv.get(start).getWeight()),
                       String.valueOf(tableobserv.get(start).getRemark()));
        
//grandtotal = grandtotal + Integer.parseInt(String.valueOf(table.getValueAt(start,7)));
Resultados.add(collectdate1);

}


Map map = new HashMap();
JDialog reporte = new JDialog();
reporte.setSize(900,700);
reporte.setLocationRelativeTo(null);
//reorte.setTtile("Reporte asdf");
map.put("comptitle",title);//Parameter value is title
map.put("grnnum",grnnum);
map.put("grndate",grndate);
map.put("grnrecivdate",recddate);
map.put("isrval",isr);
map.put("shipmodel",shipmode);
map.put("skidnum",skids);
map.put("prnuum",prnum);
map.put("countsizenum",countsize);
map.put("wgtnum",wgt);
map.put("sealnum",seal);
map.put("grdtotal",String.valueOf(grandtotal));
map.put("dmgtot",String.valueOf(grdtotbox + grdtotleak + grdtotbrkn ));
map.put("checkby",checktxt);


if(!impnote.isEmpty())
{
    map.put("impnote","Note");
    map.put("impnotevar",impnote);
}else
 {
     map.put("impnote","");
     map.put("impnotevar",""); 
 }


     JasperPrint jPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("jizanscanner/reports/ImportGrn.jasper"),map, new JRBeanCollectionDataSource(Resultados));
     
     //JRViewer jv = new JRViewer(jPrint);
     //reporte.getContentPane().add(jv);
     //reporte.setVisible(true);
     
     
    
    JRExporter exporter = new JRPdfExporter();
    FileOutputStream file = new FileOutputStream("D:\\TMP\\"+grnnum+".pdf");
     
    /*
    
      FileOutputStream file = new FileOutputStream("D:\\TMP\\"+grnname+".pdf");
      JRExporter exporter = new JRPdfExporter();
      JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "/images/ubuntu.ttf");
      
      
      
      
    
    */
  

 JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "/images/ubuntu.ttf");
 exporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
 exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, file);  //your output goes here
   
 exporter.exportReport();
 file.close();
  
     File myFile = new File("D:\\TMP\\"+grnnum+".pdf");
     Desktop.getDesktop().open(myFile);

}  



    
    
    //public void JasperGenerator(javafx.collections.ObservableList<GrnDetails> tableobserv,String title, String suppinvc,String ctn,String grnnum,String grndate,String recddate, String titlecompsec,String notetxt) throws Exception
    //{          
    //System.out.println("row in table are "+ tableobserv.size());
    //System.out.println("Before --- This is 4th value of tableview cell");   
    //String nullorval = tableobserv.get(0).getBox();
    //System.out.println("null or val "+ nullorval);
    //String totboxval = tableobserv.get(0).getGood();
    //String desc = tableobserv.get(1).getDesc();
    //String totto = tableobserv.get(1).getBox();
    //System.out.println("After --- This is 4th value of tableview cell"+totboxval+"desc"+desc+"box"+totto);  
    //} 

}
