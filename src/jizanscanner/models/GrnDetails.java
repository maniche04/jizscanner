/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

import javafx.beans.property.SimpleStringProperty;
import jizanscanner.controllers.ScannerGrnSetupController;

/**
 *
 * @author santosh
 */
public class GrnDetails {
    
  public final SimpleStringProperty id;    //= new SimpleStringProperty();
  public final SimpleStringProperty Material;   //= new SimpleStringProperty();
  public final SimpleStringProperty desc;   //= new SimpleStringProperty();
  public final SimpleStringProperty Good;   //= new SimpleStringProperty(); 
  public final SimpleStringProperty Box;    //= new SimpleStringProperty();
  public final SimpleStringProperty Leak;   //= new SimpleStringProperty();
  public final SimpleStringProperty Breaken;    //= new SimpleStringProperty();
  public final SimpleStringProperty Qty;   //= new SimpleStringProperty();  
  public final SimpleStringProperty Contry;   //= new SimpleStringProperty();
  public final SimpleStringProperty EAN;      //= new SimpleStringProperty();
  public final SimpleStringProperty Weight;   //= new SimpleStringProperty();
  public final SimpleStringProperty Remark;    //= new SimpleStringProperty();  
  public final SimpleStringProperty Lotno;    //= new SimpleStringProperty();
  public final SimpleStringProperty Doctype;  //= new SimpleStringProperty();
  public final SimpleStringProperty Docnum;   //= new SimpleStringProperty();
  public final SimpleStringProperty GRNnum;   //= new SimpleStringProperty();
  public final SimpleStringProperty Docstatus;    //= new SimpleStringProperty();
  public final SimpleStringProperty GRNstatus;    //= new SimpleStringProperty();
  
  

  
    public GrnDetails(String id,String Material,String desc,String Good,String Box,String Leak,String Breaken,String Qty,String Contry,
                        String EAN,String Weight,String Remark,String Lotno,String Doctype,String Docnum,String GRNnum,String Docstatus,String GRNstatus) {
             
                this.id = new SimpleStringProperty(id);
                this.Material = new SimpleStringProperty(Material);
                this.desc = new SimpleStringProperty(desc);
                this.Good = new SimpleStringProperty(Good);
                this.Box = new SimpleStringProperty(Box);
                this.Leak = new SimpleStringProperty(Leak);
                this.Breaken = new SimpleStringProperty(Breaken);
                this.Qty = new SimpleStringProperty(Qty);
                this.Contry = new SimpleStringProperty(Contry);
                this.EAN = new SimpleStringProperty(EAN);
                this.Weight = new SimpleStringProperty(Weight);
                this.Remark = new SimpleStringProperty(Remark);
                this.Lotno = new SimpleStringProperty(Lotno);
                this.Doctype = new SimpleStringProperty(Doctype);
                this.Docnum = new SimpleStringProperty(Docnum);
                this.GRNnum = new SimpleStringProperty(GRNnum);
                this.Docstatus = new SimpleStringProperty(Docstatus);
                this.GRNstatus = new SimpleStringProperty(GRNstatus);
                
        }



    public GrnDetails() {
     id = new SimpleStringProperty();
     Material = new SimpleStringProperty();
     desc = new SimpleStringProperty();
     Good = new SimpleStringProperty(); 
     Box = new SimpleStringProperty();
     Leak = new SimpleStringProperty();
     Breaken = new SimpleStringProperty();
     Qty = new SimpleStringProperty();  
     Contry = new SimpleStringProperty();
     EAN = new SimpleStringProperty();
     Weight = new SimpleStringProperty();
     Remark = new SimpleStringProperty();  
     Lotno = new SimpleStringProperty();
     Doctype = new SimpleStringProperty();
     Docnum = new SimpleStringProperty();
     GRNnum = new SimpleStringProperty();
     Docstatus = new SimpleStringProperty();
     GRNstatus = new SimpleStringProperty();
    }
  
  
    public void setId(String id) {
        this.id.set(id);
    }

    public void setMaterial(String Material) {
         this.Material.set(Material);
    }

    public void setDesc(String desc) {
          this.desc.set(desc);   
         }

    public void setGood(String Good) {
       
          this.Good.set(Good);
       
    }

   
    
public void setBox(String Box) {
         this.Box.set(Box);
  
}

     public void setLeak(String Leak) {
           this.Leak.set(Leak); 
     }

     public void setBreaken(String Breaken) {
           this.Breaken.set(Breaken);  
        }

     public void setQty(String Qty) {
        this.Qty.set(Qty);
     }
    
    /*
    ** this is test item material name; 
    */

     public void setContry(String Contry) {
        this.Contry.set(Contry);
     }

     public void setEAN(String EAN) {
       this.EAN.set(EAN);
     }

     public void setWeight(String Weight) {
        this.Weight.set(Weight);
     }

     public void setRemark(String Remark) {
        this.Remark.set(Remark);
     }

     public void setLotno(String Lotno) {
       this.Lotno.set(Lotno);
     }

     public void setDoctype(String Doctype) {
        this.Doctype.set(Doctype);
     }

     public void setDocnum(String Docnum) {
         this.Docnum.set(Docnum);
     }

     public void setGRNnum(String GRNnum) {
        this.GRNnum.set(GRNnum);
     }

     public void setDocstatus(String Docstatus) {
        this.Docstatus.set(Docstatus);
     }

     public void setGRNstatus(String GRNstatus) {
       this.GRNstatus.set(GRNstatus);
     }

     public String getId() {
        return id.get();
     }

     public String getMaterial() {
        return Material.get();
    }

    public String getDesc() {
        return desc.get();
    }

    public String getGood() {
        return Good.get();
    }

    public String getBox() {
        return Box.get();
    }

    public String getLeak() {
        return Leak.get();
    }

     public String getBreaken() {
        return Breaken.get();
     }
    
      public void updateTotals() {        
         
         int goodqty = Good.get().equals("") ?  0 : Integer.parseInt(Good.get()) ; 
         int boxqty = Box.get().equals("") ?   0 : Integer.parseInt(Box.get());
         int leakqty = Leak.get().equals("") ? 0 : Integer.parseInt(Leak.get());
         int brkqty = Breaken.get().equals("") ?  0 : Integer.parseInt(Breaken.get());

         System.out.println("check G"+goodqty+"B "+boxqty+"L "+leakqty+"B "+brkqty);     
         int total = goodqty + boxqty + leakqty + brkqty;
         System.out.println("The total is :" + Integer.toString(total));
         setQty(Integer.toString(goodqty + boxqty + leakqty + brkqty));
         
      }

        
     
     public String getQty(){
        return Qty.get(); 
     }
    
     public String getContry() {
        return Contry.get();
     }

     public String getEAN() {
        return EAN.get();
     }

     public String getWeight() {
        return Weight.get();
     }

     public String getRemark() {
        return Remark.get();
     }

     public String getLotno() {
        return Lotno.get();
     }

    public String getDoctype() {
        return Doctype.get();
    }

    public String getDocnum() {
        return Docnum.get();
    }

    public String getGRNnum() {
        return GRNnum.get();
    }

    public String getDocstatus() {
        return Docstatus.get();
    }

    public String getGRNstatus() {
        return GRNstatus.get();
    }

}
