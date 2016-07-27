/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 *
 * @author andras
 */
public class PrintTool implements Printable {

    private Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    public void print() {
        try {
            tryPrint();
        } catch (PrinterException e) {
            System.err.println("Error during printing!");
        }
    }

    public void tryPrint() throws PrinterException {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);
        boolean doPrint = printerJob.printDialog();
        if (doPrint) {            
            printerJob.print();
        }
    }

    public int print(Graphics g, PageFormat pageFormat, int page) {
        if (page > 0) {
            return (NO_SUCH_PAGE);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            this.component.paint(g2d);
            return (PAGE_EXISTS);
        }
    }
}
