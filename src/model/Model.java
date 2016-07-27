/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import view.HelpDialog;
import view.Mainwindow;
import view.OptionsDialog;
import view.PrintDialog;

/**
 *
 * @author andras
 */
public class Model {

    Mainwindow mainwindow;

    public final String applicationVersion = "0.9.0";
    public final String applicationName = "jWordSearch";
    public final String applicationAuthor = "Sallai Andras";
    public final String applicationCopyright = "Copyright (c) Sallai Andras, 2016";
    public final boolean applicationPortable = false;

    public DefaultListModel<String> wordsModel = new DefaultListModel<>();
    Generator generator;
    Random random;
    public BufferedImage tableCopyBi;
    boolean haseTableCopy = false;
    public DefaultTableModel tableModel = new DefaultTableModel();
    public PrintDialog printDialog;
    public String authorName = "nodata";
    public String helpFilePath = "helpresource/help_en.html";

    public Model(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
        this.generator = new Generator(mainwindow);
        this.random = new Random();
    }

    public void changeGridSize() {
        setTable();
        mainwindow.controller.filehandler.fileSaved = false;
        mainwindow.statusTextField.setText("Not saved");
    }

    public void makeTable() {
        checkAbnormalWord();
        emptyTable();
        generator.genWordsMingle();
        mainwindow.exportOriginGridToPngMenuItem.setEnabled(true);
        mainwindow.statusTextField.setText(
                "You can export the original grid to PNG from file menu"
        );
    }

    public void checkAbnormalWord() {
        //Abnormal 1 letter, or too length
        int rowCount = mainwindow.table.getRowCount();
        int colCount = mainwindow.table.getColumnCount();
        int abnormalCounter = 0;
        int listSize = wordsModel.getSize();
        for (int i = 0; i < listSize; i++) {
            String word = wordsModel.getElementAt(i);
            if (word.length() == 1) {
                abnormalCounter++;
            }
            if (word.length() > rowCount && word.length() > colCount) {
                abnormalCounter++;
            }
        }
        if (abnormalCounter > 0) {
            JOptionPane.showMessageDialog(mainwindow,
                    "Abnormal words count: " + 
                    abnormalCounter +
                    "\n\n" + //NOI18N
                    "Abnormal word: too large or too small");
        }

    }

    public void emptyTable() {
        int rowCount = mainwindow.table.getRowCount();
        int colCount = mainwindow.table.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                mainwindow.table.setValueAt("", row, col); //NOI18N
            }
        }
        mainwindow.controller.filehandler.fileSaved = false;
        mainwindow.statusTextField.setText("Not saved");
    }

    public void initTable() {
        mainwindow.table.setGridColor(Color.black);
        mainwindow.table.getTableHeader().setVisible(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        mainwindow.table.setDefaultRenderer(Object.class, centerRenderer);
        mainwindow.table.getTableHeader().setPreferredSize(new Dimension(0, 0));

        setTable();
        this.emptyTable();
    }

    public void setTable() {
        int colCount = (int) mainwindow.colCountSpinner.getValue();
        int rowCount = (int) mainwindow.rowCountSpinner.getValue();
        this.tableModel.setColumnCount(colCount);
        this.tableModel.setRowCount(rowCount);
        setTableCellSize();
        generator.chargeFacilities();
        dropTableCopy();
    }

    public void dropTableCopy() {
        tableCopyBi = null;
        haseTableCopy = false;
    }

    public void setTableCellSize() {
        mainwindow.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int cellSize = (int) mainwindow.cellSizeSpinner.getValue();
        for (int i = 0; i < mainwindow.table.getColumnCount(); i++) {
            mainwindow.table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
            mainwindow.table.getColumnModel().getColumn(i).setMinWidth(cellSize);
            mainwindow.table.getColumnModel().getColumn(i).setMaxWidth(cellSize);
        }
        mainwindow.table.setRowHeight(cellSize);
    }

    public void addWordToList() {
        String word = mainwindow.editorTextField.getText();
        if (word.isEmpty()) {
            mainwindow.statusTextField.setText("Have not a word!");
            return;
        } else {
            mainwindow.statusTextField.setText(""); //NOI18N
        }
        wordsModel.addElement(word);
        mainwindow.editorTextField.setText(""); //NOI18N
        mainwindow.editorTextField.requestFocus();
    }

    public void deleteWordFromList() {
        Integer selectedIndex = mainwindow.wordsList.getSelectedIndex();
        if (selectedIndex == -1) {
            mainwindow.statusTextField.setText(
                    "Have not selected word!");
            return;
        } else {
            mainwindow.statusTextField.setText(""); //NOI18N
        }
        wordsModel.remove(selectedIndex);
    }

    public void setTableFont() {
        int fontSize = (int) mainwindow.fontSizeSpinner.getValue();

        if (mainwindow.sansRadioButton.isSelected()) {
            mainwindow.table.setFont(new Font("Sans", Font.BOLD, fontSize)); //NOI18N
        } else {
            mainwindow.table.setFont(new Font("Serif", Font.BOLD, fontSize)); //NOI18N
        }
    }

    public void loadWordsPattern() {
        int res = JOptionPane.OK_OPTION;
        if (this.wordsModel.getSize() > 0) {
            res = JOptionPane.showConfirmDialog(mainwindow,
                    "Are you sure? This operation will delete the list.",
                    "Deleting message", 
                    JOptionPane.YES_NO_OPTION);
        }

        if (res == JOptionPane.OK_OPTION) {
            wordsModel.clear();
            wordsModel.addElement("Apple");
            wordsModel.addElement("Gooseberry");
            wordsModel.addElement("Grapefruit");
            wordsModel.addElement("Banana");
            wordsModel.addElement("Currant");
            wordsModel.addElement("Dragonfruit");
            wordsModel.addElement("Cherimoya");
            wordsModel.addElement("Jackfruit");
            wordsModel.addElement("Coconut");
            wordsModel.addElement("Lemon");
            wordsModel.addElement("KumquatT");
            wordsModel.addElement("Peach");
            wordsModel.addElement("Tamarind");
            wordsModel.addElement("Avocado");
            wordsModel.addElement("Jujube");
            wordsModel.addElement("Apricot");
        }
        mainwindow.controller.filehandler.fileSaved = false;
        mainwindow.statusTextField.setText("Not saved");
    }

    public void loadSimpleWordsPattern() {
        int res = JOptionPane.OK_OPTION;
        if (this.wordsModel.getSize() > 0) {
            res = JOptionPane.showConfirmDialog(mainwindow,
                    "Are you sure? This operation will delete the list.",
                    "Deleting message", 
                    JOptionPane.YES_NO_OPTION);
        }

        if (res == JOptionPane.OK_OPTION) {
            wordsModel.clear();
            wordsModel.addElement("Apple");
            wordsModel.addElement("Lemon");
            wordsModel.addElement("Coconut");

        }
        mainwindow.controller.filehandler.fileSaved = false;
        mainwindow.statusTextField.setText("Not saved");
    }

    public void emptyPlaceFillingRandom() {
        if (!haseTableCopy) {
            int width = mainwindow.table.getWidth();
            int height = mainwindow.table.getHeight();
            tableCopyBi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = tableCopyBi.createGraphics();
            mainwindow.table.paint(g2);
            g2.dispose();
            haseTableCopy = true;
        }

        int colCount = (int) mainwindow.colCountSpinner.getValue();
        int rowCount = (int) mainwindow.rowCountSpinner.getValue();
        String letters = "aábcdefghijklmnoőöőprstuúüűvwxyz"; //NOI18N
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                String str = mainwindow.table.getValueAt(row, col).toString();
                if (str.isEmpty()) {
                    Character ch = letters.charAt(random.nextInt(letters.length()));
                    str = ch.toString();
                    mainwindow.table.setValueAt(str, row, col);
                }
            }

        }
    }

    public void showAboutBox() {
        ImageIcon imageIcon = new ImageIcon("images/icon2.png", "Ikon"); //NOI18N
        JOptionPane.showMessageDialog(mainwindow,
            applicationName + 
            " " +  //NOI18N
            applicationVersion +
            "\n" + 
            "AUTHOR:"+
            " " +  //NOI18N
            applicationAuthor + 
            "\n"+ //NOI18N
            "LICENC:"+ 
            " GNU GPL" + //NOI18N
            "\n" +  //NOI18N
            applicationCopyright,
            "ABOUT"+
            " " +  //NOI18N
            applicationName, JOptionPane.INFORMATION_MESSAGE, imageIcon);
    }

    public void lowerCaseTable() {
        int rowCount = mainwindow.table.getRowCount();
        int colCount = mainwindow.table.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                String str = mainwindow.table.getValueAt(row, col).toString();
                if (!str.isEmpty()) {
                    mainwindow.table.setValueAt(str.toLowerCase(), row, col);
                }

            }
        }
    }

    public void upperCaseTable() {
        int rowCount = mainwindow.table.getRowCount();
        int colCount = mainwindow.table.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                String str = mainwindow.table.getValueAt(row, col).toString();
                if (!str.isEmpty()) {
                    mainwindow.table.setValueAt(str.toUpperCase(), row, col);
                }

            }
        }

    }

    public String prepareFilePathExtension(String filePath, String extension) {
        String newFilePath = filePath;
        boolean fileExtensionOk = filePath.matches(".*\\." + 
                extension + "$"); //NOI18N
        if (!fileExtensionOk) {
            newFilePath = filePath + "." + extension; //NOI18N
        }
        return newFilePath;
    }

    public void helpContentsShow() {
        HelpDialog helpdialog = new HelpDialog(mainwindow, false);
        helpdialog.setMainwindow(mainwindow);
        helpdialog.helpController.helpmodel.setApplicationVersion(getApplicationVersion());
        helpdialog.helpController.helpmodel.setApplicationName(getApplicationName());
        helpdialog.helpController.helpmodel.setApplicationAuthor(getApplicationAuthor());
        helpdialog.helpController.helpmodel.setApplicationCopyright(getApplicationCopyright());
        helpdialog.setVisible(true);
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public String getApplicationAuthor() {
        return this.applicationAuthor;
    }

    public String getApplicationCopyright() {
        return this.applicationCopyright;
    }

    public void wordListChangedShow() {
        Integer size = wordsModel.getSize();
        mainwindow.wordListSizeTextField.setText(size.toString());
    }

    public void printMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        printDialog = new PrintDialog(mainwindow, false);
        printDialog.setMainwindow(mainwindow);
        printDialog.printController.printModel.setPrintablePanel();
        printDialog.setVisible(true);
    }

    public void optionsShow() {
        OptionsDialog optionsDialog = new OptionsDialog(mainwindow, false);
        optionsDialog.optionsController.optionsModel.setHelpFilePath(this.helpFilePath);
        optionsDialog.optionsController.optionsModel.setAuthorName(this.authorName);
        optionsDialog.optionsController.optionsModel.setMainwindow(mainwindow);
        optionsDialog.setVisible(true);
        String str = optionsDialog.optionsController.optionsModel.getHelpFilePath();
        if(str == null) {
            str = helpFilePath;
        }
        this.helpFilePath = str;
    }
    
}
