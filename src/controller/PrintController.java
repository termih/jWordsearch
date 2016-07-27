/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.PrintModel;
import view.PrintDialog;

/**
 *
 * @author andras
 */
public class PrintController {
    
    PrintDialog printDialog;
    public PrintModel printModel;
    
    
    public PrintController(PrintDialog printDialog) {
        this.printDialog = printDialog;
        this.printModel = new PrintModel(printDialog);
    }
    
}
