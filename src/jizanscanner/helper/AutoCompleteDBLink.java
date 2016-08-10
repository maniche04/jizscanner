/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.helper;


import jizanscanner.models.GlobalVariable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sun.awt.GlobalCursorManager;

/**
* @author SANTOSH JAISWAL
*/

public class AutoCompleteDBLink {
String query = "";
String query1 = "";
AutoTextComplete ac = null;
AutoTextComplete acc = null;
java.sql.Connection connection = null;
java.sql.Connection connection1 = null;
GlobalVariable gv = new GlobalVariable();

public AutoCompleteDBLink(AutoTextComplete atc,java.sql.Connection con,String inputchar) throws SQLException {       /*, String query, java.sql.Connection con*/

/*this.query = query;
connection = con;*/
    
this.query1 = "select TOP 500 ite_name,ite_code from grnCaniasItems Where ite_name like '%" + inputchar + "%'";
System.out.println("need sql" + query1); 
connection1 = con;
acc = atc;
populate();

}


public void populate() throws SQLException
{
java.sql.Statement st = connection1.createStatement();
java.sql.ResultSet rs = st.executeQuery(query1);
java.util.ArrayList<String> aal = new java.util.ArrayList<String>();
Map<String, String> myMapp = new HashMap<String, String>(); 

while(rs.next())
    {
    
//    System.out.println(rs.getString(1).trim());
//    System.out.println(rs.getString(2).trim());
    //myMapp.put(rs.getString(2).trim(), rs.getString(1).trim());   
    
    myMapp.put(rs.getString(2).trim(), rs.getString(1).trim());     
    aal.add(rs.getString(1).trim());   
    gv.setMyMap(myMapp);
    acc.setItems(aal); 
    
    }

}


public AutoCompleteDBLink(AutoTextComplete atc, String itename, java.sql.Connection con,int col) {
  
if(col != 3)
{
  itename = "%" + itename + "%"; 
  this.query = "select ite_name,ite_code from dicihmas"; // where ite_name like '"+ itename  +"'
  System.out.println("need sql" + query);
}
else
{
  this.query = "select Description, Country from countrymas";   
}


ac = atc;
connection = con;
populate(col);

}
public void populate(int col) {
try {
    
java.sql.Statement st = connection.createStatement();
java.sql.ResultSet rs = st.executeQuery(query);
java.util.ArrayList<String> al = new java.util.ArrayList<String>();
java.util.ArrayList<String> all = new java.util.ArrayList<String>();
Map<String, String> myMap = new HashMap<String, String>(); 

while(rs.next())
//    if(col == 0)
//    {
//      if(rs.getString(1) != null && !rs.getString(1).equalsIgnoreCase(""))
//           {
//        myMap.put(rs.getString(2).trim(), rs.getString(1).trim());    
//        al.add(rs.getString(1).trim());
//           }
//    }
    
 if(col == 2)
    {
        if(rs.getString(1) != null && !rs.getString(1).equalsIgnoreCase(""))
           {
        myMap.put(rs.getString(2).trim(), rs.getString(1).trim());    
        al.add(rs.getString(1).trim());
           }
    }


//if(col == 3)
// {
//al.add(rs.getString(1).trim()+ "-" + rs.getString(2).trim()); 
// }  
// gv.setMyMap(myMap);
// ac.setItems(al);


} catch(Exception ex) {
    ex.printStackTrace();
}
}


}


