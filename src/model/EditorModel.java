/* 
 * Copyright (C) 2016 Sallai András <termih@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;
import view.Mainwindow;

/**
 *
 * @author andras
 */
public class EditorModel {
    
    Mainwindow mainwindow;
    
    public EditorModel(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
    }

    public void wordsTransferEditorPanel() {
        mainwindow.editorTextArea.setText(""); //NOI18N
        int listSize = mainwindow.wordsList.getModel().getSize();
        for (int i = 0; i < listSize; i++) {
            String word = mainwindow.wordsList.getModel().getElementAt(i);
            mainwindow.editorTextArea.append(word);
            mainwindow.editorTextArea.append("\n"); //NOI18N
        }
        mainwindow.statusTextField.setText("Words transfered");
    }

    public void wordsTransferToClipboard() {
        String words = mainwindow.editorTextArea.getText();
        StringSelection stringSelection = new StringSelection(words);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        mainwindow.statusTextField.setText("Words copy to clipboard");
    }

    public void wordsTransferToWorkerList() {
        int workerListSize = mainwindow.wordsList.getModel().getSize();
        int res = JOptionPane.OK_OPTION;
        if (workerListSize > 0) {
            res = JOptionPane.showConfirmDialog(mainwindow,
                    "Are you sure? This operation delete the words from list on worker face",
                    "Delete message", JOptionPane.YES_NO_OPTION);
        }
        if (res == JOptionPane.OK_OPTION) {
            for (String line : mainwindow.editorTextArea.getText().split("\\n")) {
                if (!line.isEmpty()) {
                    mainwindow.controller.model.wordsModel.addElement(line);
                }
            }
            mainwindow.wordsList.setSelectedIndex(0);
        }

    }

    public void editorLowrCase() {
        String content = mainwindow.editorTextArea.getText();
        mainwindow.organizerTextArea.setText(content.toLowerCase());
    }

    public void editorUpperCase() {
        String content = mainwindow.editorTextArea.getText();
        mainwindow.editorTextArea.setText(content.toUpperCase());
    }

    public void editorBalanceToWords() {

        for (String str : mainwindow.balanceTextArea.getText().split("\\n")) {
            if (!str.isEmpty()) {
                mainwindow.editorTextArea.append(str);
                mainwindow.editorTextArea.append("\n");
            }
        }                
    }
    
    public void transferToOrganizer() {
        String organizerContent = mainwindow.organizerTextArea.getText();
        int res = JOptionPane.OK_OPTION;
        if (!organizerContent.isEmpty()) {
            res = JOptionPane.showConfirmDialog(mainwindow,
                    "Are you sure? This operaton delete the words from organizer",
                    "Delete message", 
                    JOptionPane.YES_NO_OPTION);
        }
        if (res == JOptionPane.OK_OPTION) {
            String content = mainwindow.editorTextArea.getText();
            mainwindow.organizerTextArea.setText(content);

        }        
    }

}
