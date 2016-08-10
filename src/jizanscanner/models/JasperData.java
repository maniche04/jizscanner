/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

/**
 *
 * @author santosh
 */
public class JasperData {
    String srno,description,remark,good,box,leak,brkn,total,orig,eancode,wgt,lotnum;

    

   

    
    public JasperData(String srno, String description, String remark, String good, String box, String leak, String brkn, String total, String orig, String eancode,String wgt, String lotnum) {
        this.srno = srno;
        this.description = description;
        this.good = good;
        this.box = box;
        this.leak = leak;
        this.brkn = brkn;
        this.total = total;
        this.orig = orig;
        this.eancode = eancode;
        this.wgt = wgt;
        this.remark = remark;
        this.lotnum = lotnum;
    }

    public String getwgt() {
        return wgt;
    }

    public void setwgt(String wgt) {
        this.wgt = wgt;
    }
    
  
    public String getsrno() {
        return srno;
    }

    public void setsrno(String srno) {
        this.srno = srno;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }
    
    
    public String getremark() {
        return remark;
    }

    public void setremark(String remark) {
        this.remark = remark;
    }
    

    public String getgood() {
        return good;
    }

    public void setgood(String good) {
        this.good = good;
    }

    public String getbox() {
        return box;
    }

    public void setbox(String box) {
        this.box = box;
    }

    public String getleak() {
        return leak;
    }

    public void setleak(String leak) {
        this.leak = leak;
    }

    public String getbrkn() {
        return brkn;
    }

    public void setbrkn(String brkn) {
        this.brkn = brkn;
    }

    public String gettotal() {
        return total;
    }

    public void settotal(String total) {
        this.total = total;
    }

    public String getorig() {
        return orig;
    }

    public void setorig(String orig) {
        this.orig = orig;
    }

    public String geteancode() {
        return eancode;
    }

    public void seteancode(String eancode) {
        this.eancode = eancode;
    }

     public String getlotnum() {
        return lotnum;
    }

    public void setlotnum(String lotnum) {
        this.lotnum = lotnum;
    }
    
}
