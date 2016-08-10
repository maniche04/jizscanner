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
public class Canias {
    
    private HttpClient http = new HttpClient();
    private String result;
    
    //get the eancode from system
    public String getMaterialData(String eancode) throws Exception {
        result = http.getRequest("api/ean/f/" + eancode);
        System.out.println(result);
        return result;
    }
    
    public String getMaterialFromCode(String caniascode) throws Exception {
        result = http.getRequest("api/ean/fc/" + caniascode);
        System.out.println(result);
        return result;
    }
}
