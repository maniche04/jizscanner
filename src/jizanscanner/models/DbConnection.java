/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DbConnection {
    
Connection conn = null;
Connection epconn = null;

public Connection conn() throws ClassNotFoundException
{
   try{
        String dbURL = "jdbc:sqlserver://10.1.100.200\\SQLEXPRESS:1433;databaseName=intranet";
        
        //String dbURL = "jdbc:sqlserver://10.1.100.200\\SQLEXPRESS:1433;databaseName=tst_jizan_epromise";
        String user = "manish";
        String pass = "Almunia24";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
           conn = DriverManager.getConnection(dbURL, user, pass);
        if(conn != null){
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());   
            }        
          return conn;   
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn == null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } 
      return conn;
      
 }
  
    // System.out.println("this addList Name");
    // this.JavaFXML.container();
    // this.MaterialName();


}
