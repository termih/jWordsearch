/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.Mainwindow;
import view.PrintDialog;

/**
 *
 * @author andras
 */
public class Print {
    Mainwindow mainwindow;    
    PrintDialog printDialog;
    public Print(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
    }    
    
    public void print() {
        mainwindow.controller.model.printDialog.setVisible(false);
        PrintTool printTool = new PrintTool();
        printTool.setComponent(printDialog.pagePanel);
        printTool.print();
    }

    public void setParent(PrintDialog printDialog) {
        this.printDialog = printDialog;
    }
}
