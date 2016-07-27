/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import view.PrintDialog;

/**
 *
 * @author andras
 */
public class PrintModel {
    
    PrintDialog printDialog;
    DefaultTableModel tableModel = new DefaultTableModel();
    
    public PrintModel(PrintDialog printDialog) {
        this.printDialog = printDialog;
    }
    
    public void setPrintablePanel() {
        initTable();        
    }
    
    public void initTable() {
        this.printDialog.table.setGridColor(Color.white);
        this.printDialog.table.getTableHeader().setVisible(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.printDialog.table.setDefaultRenderer(Object.class, centerRenderer);        
        this.printDialog.table.getTableHeader().setPreferredSize(new Dimension(0, 0));
        Font font = this.printDialog.mainwindow.table.getFont();
        this.printDialog.table.setFont(font);
        this.printDialog.table.setModel(tableModel);
        setTable();
        fillTable();
        if(this.printDialog.mainwindow.organizerTextArea.getText().isEmpty()) {
            int listSize = this.printDialog.mainwindow.controller.model.wordsModel.size();
            for(int i=0; i<listSize; i++) {
                String str = this.printDialog.mainwindow.controller.model.wordsModel.getElementAt(i);
                this.printDialog.wordsTextArea.append(str);
                this.printDialog.wordsTextArea.append("\n");
            }
        }else {
            String str = this.printDialog.mainwindow.organizerTextArea.getText();
            this.printDialog.wordsTextArea.setText(str);
        }        
    }    
    public void setTable() {
        int colCount = (int) this.printDialog.mainwindow.colCountSpinner.getValue();
        int rowCount = (int) this.printDialog.mainwindow.rowCountSpinner.getValue();
        this.tableModel.setColumnCount(colCount);
        this.tableModel.setRowCount(rowCount);
        setTableCellSize();        
        int cellSize = (int) this.printDialog.mainwindow.cellSizeSpinner.getValue();
        int width = cellSize * colCount;
        int height = cellSize * rowCount;
        this.printDialog.table.setSize(width, height);
        this.printDialog.tableScrollPane.setSize(width, height);
        this.printDialog.tablePanel.setSize(width, height);
    }
    
    public void setTableCellSize() {        
        int cellSize = (int) this.printDialog.mainwindow.cellSizeSpinner.getValue();
        for (int i = 0; i < this.printDialog.table.getColumnCount(); i++) {
            this.printDialog.table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
            this.printDialog.table.getColumnModel().getColumn(i).setMinWidth(cellSize);
            this.printDialog.table.getColumnModel().getColumn(i).setMaxWidth(cellSize);
        }
        this.printDialog.table.setRowHeight(cellSize);
    }   

    public void fillTable() {
        int rowCount = this.printDialog.table.getRowCount();
        int colCount = this.printDialog.table.getColumnCount();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                String str = this.printDialog.mainwindow.table.getValueAt(row, col).toString();
                this.printDialog.table.setValueAt(str, row, col);
            }
        }
    }
    
    public void print() {
        Print print = new Print(this.printDialog.mainwindow);
        print.setParent(this.printDialog);        
        print.print();  
        
    }
}
