/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.event.ListDataEvent;
import model.EditorModel;
import model.Export;
import model.Filehandler;
import model.Model;
import model.Options;
import model.Orgmodel;
import view.Mainwindow;


/**
 *
 * @author andras
 */
public class Controller {

    Mainwindow mainwindow;
    public Model model;
    public Options options;
    public Export export;
    public EditorModel editormodel;
    public Orgmodel orgmodel;
    public Filehandler filehandler;


    public Controller(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
        this.model = new Model(mainwindow);
        this.options = new Options(mainwindow);
        this.export = new Export(mainwindow);
        this.editormodel = new EditorModel(mainwindow);
        this.orgmodel = new Orgmodel(mainwindow);
        this.filehandler = new Filehandler(mainwindow);
    }

    public void gridSizeApplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.changeGridSize();
    }

    public void makeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.makeTable();
    }

    public void addWordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.addWordToList();
    }

    public void deleteWordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.deleteWordFromList();
    }

    public void fontApplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.setTableFont();
    }

    public void formWindowClosing(java.awt.event.WindowEvent evt) {
        options.saveOptins();
    }

    public void exportGridToPngMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        export.exportGridToPng();
    }

    public void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public void helpContentsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        model.helpContentsShow();
    }

    public void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        model.showAboutBox();
    }

    public void loadWordsPatternMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        model.loadWordsPattern();
    }

    public void fillRandomPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.emptyPlaceFillingRandom();
    }

    public void exportOriginGridToPngMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        export.exportOriginGridToPng(evt);
    }

    public void toWordsPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        editormodel.wordsTransferEditorPanel();
    }

    public void toClipboardButtonActionPerformed(java.awt.event.ActionEvent evt) {
        editormodel.wordsTransferToClipboard();
    }

    public void toWorkerPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        editormodel.wordsTransferToWorkerList();
    }

    public void editorLowerCaseRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        editormodel.editorLowrCase();
    }

    public void editorUpperCaseRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        editormodel.editorUpperCase();
    }
    
    public void simplePatternMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        model.loadSimpleWordsPattern();
    }
    
    public void applyCellSizeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.setTableCellSize();
    }
    
    public void editorDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        mainwindow.editorTextArea.setText(""); //NOI18N
    }
    
    public void orgSortAlphabetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        orgmodel.sortAlphabetOrganiserContent();
    }

    public void orgSortByLengthPerLineButtonActionPerformed(java.awt.event.ActionEvent evt) {
        orgmodel.sortByLengthPerLineEditorContent();
    }
    
    public void orgSortByLengthOneLineButtonActionPerformed(java.awt.event.ActionEvent evt) {
        orgmodel.sortByLengthOneLineEditorContent();
    }
    
    public void lowerCaseRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.lowerCaseTable();
    }
    
    public void uperCaseRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model.upperCaseTable();
    }
    
    public void toOrganizerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        editormodel.transferToOrganizer();
    }
    
    public void editorBalanceToWordsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        editormodel.editorBalanceToWords();
    }
    
    public void orgToClipboardButtonActionPerformed(java.awt.event.ActionEvent evt) {
        orgmodel.organizerContentToClipboard();
    }
    
    public void deleteAllButtonActionPerformed(java.awt.event.ActionEvent evt) {
        mainwindow.controller.model.wordsModel.clear();
    }

    public void wordModelChangedListDataListener(ListDataEvent e) {
        model.wordListChangedShow();
    }
    
    public void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        filehandler.newWordSearchFile();
    }

    public void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        filehandler.openWordSearchFile();
    }

    public void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        filehandler.saveWordSearchFile();
    }

    public void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        filehandler.saveAsWordSearchFile();
    }
    
    public void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        filehandler.closeWordSearchFile();
    }
    
    public void printMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        model.printMenuItemActionPerformed(evt);
    }
    
    public void optionsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        model.optionsShow();
    }
}
