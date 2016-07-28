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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import view.Mainwindow;

/**
 *
 * @author andras
 */
public class Filehandler {

    Mainwindow mainwindow;

    boolean fileSaved = false;
    public String filePath = "noname";
    public File fileSavePath = null;

    public Filehandler(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
    }

    public void newWordSearchFile() {
        int res = JOptionPane.OK_OPTION;
        if (!fileSaved) {
            res = JOptionPane.showConfirmDialog(
                    mainwindow, 
                    "Are you sure? The file not saved!", 
                    "Not saved!", 
                    JOptionPane.YES_NO_OPTION);
        }
        if (res == JOptionPane.OK_OPTION) {
            mainwindow.colCountSpinner.setValue(12);
            mainwindow.rowCountSpinner.setValue(12);
            mainwindow.controller.model.setTable();
            mainwindow.controller.model.wordsModel.clear();
            setFilePath(java.util.ResourceBundle.getBundle("model/model").getString("NONAME"));
            mainwindow.controller.model.dropTableCopy();
        }
    }

    public void openWordSearchFile() {
        int resOpen = JOptionPane.OK_OPTION;
        if (!fileSaved) {
            resOpen = JOptionPane.showConfirmDialog(mainwindow,
                    "Are yout sure? File nos saved!",
                    "Not saved fjile", 
                    JOptionPane.YES_NO_OPTION);
        }
        if (resOpen == JOptionPane.YES_OPTION) {
            mainwindow.fileChooser.addChoosableFileFilter(new WordsearchFilter());
            mainwindow.fileChooser.setAcceptAllFileFilterUsed(true);
            int res = mainwindow.fileChooser.showOpenDialog(mainwindow.getContentPane());
            if (res == JFileChooser.APPROVE_OPTION) {
                String str = mainwindow.fileChooser.getSelectedFile().getPath();
                setFilePath(str);
                fileSavePath = new File(filePath);
                openFile();
                mainwindow.controller.model.dropTableCopy();
                mainwindow.controller.filehandler.fileSaved = true;
                mainwindow.statusTextField.setText(""); //NOI18N
            }
        }
    }

    public void openFile() {
        try {
            tryOpenFile();
        } catch (FileNotFoundException ex) {
            System.err.println("Error during loading file!");
        }
    }

    public void tryOpenFile() throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        Scanner reader = new Scanner(fr);

        reader.nextLine(); //read program name and version
        mainwindow.controller.model.authorName = reader.nextLine();
        int rowCount = reader.nextInt();
        int colCount = reader.nextInt();

        mainwindow.rowCountSpinner.setValue(rowCount);
        mainwindow.colCountSpinner.setValue(colCount);
        mainwindow.controller.model.setTable();

        reader.nextLine();// read \n
        reader.nextLine();// read :

        for (int row = 0; row < rowCount; row++) {
            String[] line = reader.nextLine().split(" "); //NOI18N
            for (int col = 0; col < colCount; col++) {
                String str = line[col];
                if (str.equals("_")) { //NOI18N
                    str = ""; //NOI18N
                }
                mainwindow.table.setValueAt(str, row, col);
            }
        }

        reader.nextLine();// read :
        int listSize = reader.nextInt();// size
        reader.nextLine(); // read \n

        while (reader.hasNextLine()) {
            mainwindow.controller.model.wordsModel.addElement(reader.nextLine());
        }
    }

    public void saveWordSearchFile() {
        if (mainwindow.controller.filehandler.fileSavePath == null) {
            mainwindow.fileChooser.addChoosableFileFilter(new WordsearchFilter());
            mainwindow.fileChooser.setAcceptAllFileFilterUsed(true);
            int res = mainwindow.fileChooser.showSaveDialog(mainwindow.getContentPane());
            if (res == JFileChooser.APPROVE_OPTION) {
                String str = mainwindow.fileChooser.getSelectedFile().getPath();
                setFilePath(str);
            }
        }
        saveToFile();
        this.fileSaved = true;
        mainwindow.statusTextField.setText("Saved");
    }

    public void saveAsWordSearchFile() {
        mainwindow.fileChooser.addChoosableFileFilter(new WordsearchFilter());
        mainwindow.fileChooser.setAcceptAllFileFilterUsed(true);
        int res = mainwindow.fileChooser.showSaveDialog(mainwindow.getContentPane());
        if (res == JFileChooser.APPROVE_OPTION) {
            String str = mainwindow.fileChooser.getSelectedFile().getPath();
            setFilePath(str);
        }
        saveToFile();
        this.fileSaved = true;
        mainwindow.statusTextField.setText("Saved");
    }

    public void closeWordSearchFile() {
        int res = JOptionPane.OK_OPTION;
        if (!fileSaved) {
            res = JOptionPane.showConfirmDialog(mainwindow,
                    "Are you sure? The file not saved!",
                    "Not saved",
                    JOptionPane.YES_NO_OPTION);
        }
        if (res == JOptionPane.OK_OPTION) {
            setFilePath(""); //NOI18N
            mainwindow.controller.model.dropTableCopy();
            mainwindow.colCountSpinner.setValue(0);
            mainwindow.rowCountSpinner.setValue(0);
            mainwindow.controller.model.setTable();
            mainwindow.controller.model.wordsModel.clear();
            mainwindow.controller.model.dropTableCopy();
            mainwindow.controller.filehandler.fileSaved = false;
            mainwindow.statusTextField.setText("Not saved");
        }

    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        mainwindow.workdContainerPanel.setBorder(new TitledBorder(filePath));
    }

    public void saveToFile() {
        try {
            trySaveToFile();
        } catch (FileNotFoundException ex) {
            System.err.println("Error! File not found!");
        } catch (IOException ex) {
            System.err.println("Error during writing file!");
        }
    }

    public void trySaveToFile() throws FileNotFoundException, IOException {

        setFilePath(mainwindow.controller.model.prepareFilePathExtension(filePath, "wsm")); //NOI18N
        this.fileSavePath = new File(filePath);
        FileWriter fw = new FileWriter(this.fileSavePath, false);
        PrintWriter pw = new PrintWriter(fw);

        //Save applicaton name and version
        String appNameVersion = 
                mainwindow.controller.model.getApplicationName() + 
                " " + //NOI18N
                mainwindow.controller.model.getApplicationVersion();
        pw.printf("%s\n", appNameVersion); //NOI18N
        
        //Save author
        pw.printf("%s\n", mainwindow.controller.model.authorName); //NOI18N
        
        //Save table
        int colCount = mainwindow.table.getColumnCount();
        int rowCount = mainwindow.table.getRowCount();

        pw.printf("%d %d\n:\n", rowCount, colCount); //NOI18N

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                String str = mainwindow.table.getValueAt(row, col).toString();
                if (str.isEmpty()) {
                    str = "_"; //NOI18N
                }
                pw.printf("%s ", str); //NOI18N
            }
            pw.println();
        }
        //Save words
        pw.println(":"); //NOI18N
        Integer listSize = mainwindow.wordsList.getModel().getSize();
        pw.printf("%d\n", listSize); //NOI18N
        for (int i = 0; i < listSize; i++) {
            String word = mainwindow.wordsList.getModel().getElementAt(i);
            pw.printf("%s\n", word); //NOI18N
        }

        pw.close();
        fw.close();
    }

}
