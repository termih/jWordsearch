/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JFileChooser;
import view.Mainwindow;
import view.OptionsDialog;

/**
 *
 * @author andras
 */
public class OptionsModel {

    Mainwindow mainwindow;
    OptionsDialog optionsDialog;
    String helpFilePath = null;

    public OptionsModel(OptionsDialog optionsDialog) {
        this.optionsDialog = optionsDialog;
    }

    public void browseHelpFilePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HtmlFilter());
        fileChooser.showOpenDialog(optionsDialog);
    }

    public String getHelpFilePath() {
        return this.helpFilePath;
    }

    public void setHelpFilePath(String helpFilePath) {
        optionsDialog.helpFilePathTextField.setText(helpFilePath);
    }

    public void setAuthorName(String authorName) {
        optionsDialog.authorTextField.setText(authorName);
    }

    public void setMainwindow(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
    }

    public void applyOptions() {
        String authorName = optionsDialog.authorTextField.getText();
        mainwindow.controller.model.authorName = authorName;

        String helpFilePath = optionsDialog.helpFilePathTextField.getText();
        mainwindow.controller.model.helpFilePath = helpFilePath;
    }

    public void closeOptionsDialog() {
        optionsDialog.setVisible(false);
    }
}
