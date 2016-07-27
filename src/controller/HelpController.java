/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.HelpModel;
import view.HelpDialog;

/**
 *
 * @author andras
 */
public class HelpController {
    
    HelpDialog helpdialog;
    public HelpModel helpmodel;
    
    public HelpController(HelpDialog helpdialog) {
        this.helpdialog = helpdialog;
        this.helpmodel = new HelpModel(helpdialog);
    }
    
    public void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        helpdialog.setVisible(false);
    }
    
    public void helpTreeMouseClicked(java.awt.event.MouseEvent evt) {
        helpmodel.helpAction(evt);
    }
}
