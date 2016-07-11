/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author santosh
 */
public class GrnDetails {
    
  public SimpleStringProperty id = new SimpleStringProperty();
  public SimpleStringProperty material = new SimpleStringProperty();
  public SimpleStringProperty desc = new SimpleStringProperty();
  public SimpleStringProperty Good = new SimpleStringProperty(); 
  public SimpleStringProperty Box = new SimpleStringProperty();
  public SimpleStringProperty Leak = new SimpleStringProperty();
  public SimpleStringProperty Breaken = new SimpleStringProperty();
  public SimpleStringProperty Qty = new SimpleStringProperty();  
  public SimpleStringProperty Contry = new SimpleStringProperty();
  public SimpleStringProperty EAN = new SimpleStringProperty();
  public SimpleStringProperty Weight = new SimpleStringProperty();
  public SimpleStringProperty Remark = new SimpleStringProperty();  
  public SimpleStringProperty Lotno = new SimpleStringProperty();
  public SimpleStringProperty Doctype = new SimpleStringProperty();
  public SimpleStringProperty Docnum = new SimpleStringProperty();
  public SimpleStringProperty GRNnum = new SimpleStringProperty();
  public SimpleStringProperty Docstatus = new SimpleStringProperty();
  public SimpleStringProperty GRNstatus = new SimpleStringProperty();

    public String getId() {
        return id.get();
    }

    public String getMaterial() {
        return material.get();
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

    public String getQty() {
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
