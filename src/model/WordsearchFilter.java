/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author andras
 */
public class WordsearchFilter extends FileFilter {
    private final String[] okFileExtensions
            = new String[]{".wsm"};

    public boolean accept(File file) {
        if(file.isDirectory()) {
            return true;
        }
        for (String extension : okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    public String getDescription() {
        return "*.wsm " + 
               "(Wordsearch maker file)";
    }    
}
