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

//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.X509Certificate;
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;

/**
 *
 * @author santosh
 */
public class UserIntraVerification {
   
    
    private final String USER_AGENT = "Mozilla/5.0";
    public void sendPost() throws Exception {
 
            GlobalVariable gvauth = new GlobalVariable();
            
            
            String url = "http://10.1.100.200/auth/check";
	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
                    
	    //add reuqest header
             
	    con.setRequestMethod("POST");
	    con.setRequestProperty("User-Agent",USER_AGENT); 

	    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String urlParameters = "username=" + gvauth.getGlobusername() + "&password=" + gvauth.getGlobpassword();
 
	    //Send post request
	    con.setDoOutput(true);
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    wr.writeBytes(urlParameters);
	    wr.flush();
	    wr.close();
 
	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + urlParameters);
	    System.out.println("Response Code : " + responseCode);
 
	    BufferedReader in = new BufferedReader(
	    new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();
 
	    while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
                
		//print result
                if ("1".equals(response.toString())) {
                    gvauth.setPassFail(1);
                    System.out.println(response.toString());
                } else {
                    System.out.println("Incorrect Username/Password");
                }
           
	}
    
//private void disableSslVerification() {
//    try
//    {
//        // Create a trust manager that does not validate certificate chains
//        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//            public void checkClientTrusted(X509Certificate[] certs, String authType) {
//            }
//            public void checkServerTrusted(X509Certificate[] certs, String authType) {
//            }
//        }
//        };
//
//        // Install the all-trusting trust manager
//        SSLContext sc = SSLContext.getInstance("SSL");
//        sc.init(null, trustAllCerts, new java.security.SecureRandom());
//        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//        // Create all-trusting host name verifier
//        HostnameVerifier allHostsValid = new HostnameVerifier() {
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        };
//
//    //Install the all-trusting host verifier
//    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//    } catch (NoSuchAlgorithmException e) {
//        e.printStackTrace();
//    } catch (KeyManagementException e) {
//        e.printStackTrace();
//    }
//}    
    
    
    
}
