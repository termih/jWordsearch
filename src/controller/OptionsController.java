/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.OptionsModel;
import view.OptionsDialog;

/**
 *
 * @author andras
 */
public class OptionsController {
    
    OptionsDialog optionsDialog;
    public OptionsModel optionsModel;
    
    public OptionsController(OptionsDialog optionsDialog) {
        this.optionsDialog = optionsDialog;
        this.optionsModel = new OptionsModel(optionsDialog);
    }
    
    public void browseHelpFilePathButtonActionPerformed(java.awt.event.ActionEvent evt) {
        optionsModel.browseHelpFilePath();
    }
    
    public void applyOptionsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        optionsModel.applyOptions();
    }
    
    public void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        optionsModel.closeOptionsDialog();
    }
    
}
