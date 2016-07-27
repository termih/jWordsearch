/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import view.Mainwindow;

/**
 *
 * @author andras
 */
public class Orgmodel {
   
    Mainwindow mainwindow;
    
    public Orgmodel(Mainwindow mainwindow) {
        
        this.mainwindow = mainwindow;
        
    }
    
    public void sortAlphabetOrganiserContent() {
        ArrayList<String> tmpList = new ArrayList<>();

        for (String line : mainwindow.editorTextArea.getText().split("\\n")) { //NOI18N
            if (!line.isEmpty()) {
                tmpList.add(line);
            }
        }
        Collator collator = Collator.getInstance(new Locale("hu")); //NOI18N
        Collections.sort(tmpList, collator);
        mainwindow.organizerTextArea.setText(""); //NOI18N
        for (String line : tmpList) {
            mainwindow.organizerTextArea.append(line);
            mainwindow.organizerTextArea.append("\n"); //NOI18N
        }
        mainwindow.statusTextField.setText("Sorted by alphabet");
    }    

    public void sortByLengthPerLineEditorContent() {
        ArrayList<String> list = getListFromOrganizerTextArea();
        mainwindow.organizerTextArea.setText(""); //NOI18N
        list = sortByLength(list);
        for (String line : list) {
            mainwindow.organizerTextArea.append(line);
            mainwindow.organizerTextArea.append("\n"); //NOI18N
        }
        mainwindow.statusTextField.setText("Sorted by word length, per line");
    }
    
    public void sortByLengthOneLineEditorContent() {
        ArrayList<String> list = getListFromOrganizerTextArea();
        mainwindow.organizerTextArea.setText(""); //NOI18N
        list = sortByLength(list);
        boolean firstWord = true;
        for (String line : list) {
            if(!firstWord) {
                mainwindow.organizerTextArea.append(", "); //NOI18N
            }            
            mainwindow.organizerTextArea.append(line);
            firstWord = false;
        }
        mainwindow.statusTextField.setText(
                "Sorted by word length, one line");
    }

    private ArrayList<String> getListFromOrganizerTextArea() {
        ArrayList<String> list = new ArrayList<>();

        for (String str : mainwindow.editorTextArea.getText().split("\\n")) { //NOI18N
            if (!str.isEmpty()) {
                list.add(str);
            }
        }
        return list;
    } 
    
    private ArrayList<String> sortByLength(ArrayList<String> list) {

        int listSize = list.size();
        for (int i = listSize - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {

                if (list.get(j).length() > list.get(j + 1).length()) {
                    String tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                }
            }
        }
        return list;
    }
    
    public void organizerContentToClipboard() {
        String words = mainwindow.organizerTextArea.getText();
        StringSelection stringSelection = new StringSelection(words);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        mainwindow.statusTextField.setText("Words transfered to clipboard from organizer");        
    }
    
}
