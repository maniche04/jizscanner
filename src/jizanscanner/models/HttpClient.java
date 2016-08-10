/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jizanscanner.models;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



/**
*@author santosh
*/

public class HttpClient {
  String masterurl = "http://10.1.100.200/";
  String matcheckurl = masterurl + "api/ean/c/r"; 
  private final String USER_AGENT = "Mozilla/5.0";
  URL obj;
  public String getRequest(String url, String parameters, Integer debugflag) throws Exception {       
        url = "http://10.1.100.200/" + url;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = parameters;

            //Send post request
        
         /*item material*/
         con.setDoOutput(true);
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
         wr.writeBytes(urlParameters);
         wr.flush();
         wr.close();

         int responseCode = con.getResponseCode();
         System.out.println("\nSending 'GET' request to URL : " + url);
         System.out.println("\nPost parameters : " + urlParameters);
         System.out.println("\nResponse Code : " + responseCode);

         BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
         String inputLine;
         StringBuffer response = new StringBuffer();
        
             while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
             }
             
         in.close();
         return response.toString();
      
  }
    
    public String getRequest(String url) throws Exception {
        return getRequest(url, "", 1);
    }
    
    public String getRequest(String url, String parameters) throws Exception {
        return getRequest(url, parameters, 1);
    }

    public String MaterialCheckPost(String eancode,String desc, String caniascode, String qty, String contrycode, String wght) throws Exception {
        
                //disableSslVerification();
		obj = new URL(matcheckurl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                

                //add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "ean="+eancode+"&desc="+desc+"&code="+caniascode+"&qty="+qty+"&countrycode="+contrycode+"&weight="+wght;
                
                //Send post request
                
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
                int responseCode = con.getResponseCode();
                
                System.out.println("\nSending 'POST' request to URL : " + matcheckurl);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);
                
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
                
		while ((inputLine = in.readLine()) != null){
		      response.append(inputLine);
		}
                
		in.close();
                
                //print result
	        System.out.println(response.toString());
                return response.toString();                
                
    } 
    
    public String postRequest(String url, String parameters) throws Exception {
        
        url = "http://10.1.100.200/" + url;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        //add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = parameters;

            //Send post request
        
         /*item material*/
         con.setDoOutput(true);
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
         wr.writeBytes(urlParameters);
         wr.flush();
         wr.close();

         int responseCode = con.getResponseCode();
         System.out.println("\nSending 'Post' request to URL : " + url);
         System.out.println("\nPost parameters : " + urlParameters);
         System.out.println("\nResponse Code : " + responseCode);

         BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
         String inputLine;
         StringBuffer response = new StringBuffer();
        
             while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
             }
             
         in.close();
         return response.toString();   
     }   
    
}
