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

public class ImpotGrnBean {
    
String srno,desc,good,dmgbox,dmgleak,dmgbrkn,totat,orig,lotnum,wgt,remarks;

 
public ImpotGrnBean(String srno, String desc, String good, String dmgbox, String dmgleak, String dmgbrkn, String totat, String orig, String lotnum, String wgt,String remarks) {
    
        this.srno = srno;
        this.desc = desc;
        this.good = good;
        this.dmgbox = dmgbox;
        this.dmgleak = dmgleak;
        this.dmgbrkn = dmgbrkn;
        this.totat = totat;
        this.orig = orig;
        this.lotnum = lotnum;
        this.wgt = wgt;
        this.remarks = remarks;
        
    }
    
   

public String getsrno() {
        return srno;
    }

public void setsrno(String srno) {
        this.srno = srno;
    }

public String getdesc() {
        return desc;
    }

public void setdesc(String desc) {
        this.desc = desc;
    }

public String getgood() {
        return good;
    }

public void setgood(String good) {
        this.good = good;
    }

public String getdmgbox() {
        return dmgbox;
    }

public void setdmgbox(String dmgbox) {
        this.dmgbox = dmgbox;
    }

public String getdmgleak() {
        return dmgleak;
    }

public void setdmgleak(String dmgleak) {
        this.dmgleak = dmgleak;
    }

public String getdmgbrkn() {
        return dmgbrkn;
    }

public void setdmgbrkn(String dmgbrkn) {
        this.dmgbrkn = dmgbrkn;
    }

public String gettotat() {
        return totat;
    }

public void settotat(String totat) {
        this.totat = totat;
    }

public String getorig() {
        return orig;
    }

public void setorig(String orig) {
        this.orig = orig;
    }

public String getlotnum() {
        return lotnum;
    }

public void setlotnum(String lotnum) {
        this.lotnum = lotnum;
    }

public String getwgt() {
        return wgt;
    }

public void setwgt(String wgt) {
        this.wgt = wgt;
    }
    
public String getremarks() {
        return remarks;
    }

public void setremarks(String remarks) {
        this.remarks = remarks;
    }
    
       
}
