/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author andras
 */
public class Osverify {
    
    public static String getOs() {
        String os = null;
        String osInfo = System.getProperty("os.name");
        if(osInfo.matches("^Win.*")) {
            os = "win";
        }
        if(osInfo.matches("^Lin.*")) {
            os = "lin";
        }
        if(osInfo.matches("^Mac.*")) {
            os = "mac";
        }        
        return os;
    }
    
}
