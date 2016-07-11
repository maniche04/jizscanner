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
public class Grn {
  public SimpleStringProperty supplier = new SimpleStringProperty();
  public SimpleStringProperty docnum = new SimpleStringProperty();
  public SimpleStringProperty date = new SimpleStringProperty();
   public SimpleStringProperty grnno = new SimpleStringProperty();

   public String getSupplier() {
      return supplier.get();
   }

   public String getDocnum() {
      return docnum.get();
   }

   public String getDate() {
      return date.get();
   }
    public String getGrno() {
      return grnno.get();
   }
    
    
    

}
