/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author andras
 */
public class FileUtils {

    public static String readFileToString(String fileName) {
        String fileContent = null;
        try {
            fileContent = fileContent = tryReadFileToString(fileName);
        }catch(FileNotFoundException ex) {
            System.err.println("Error. File not found!");
        }
        return fileContent;
    }
    
    private static String tryReadFileToString(String fileName) throws FileNotFoundException {
        StringBuilder fileContentSb = new StringBuilder();
        FileReader fr = new FileReader(fileName);
        Scanner olvaso = new Scanner(fr);

        while (olvaso.hasNextLine()) {
            fileContentSb.append(olvaso.nextLine());
            fileContentSb.append("\n"); //NOI18N
        }
        return fileContentSb.toString();
    }
}
