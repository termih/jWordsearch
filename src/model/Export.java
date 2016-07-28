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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import view.Mainwindow;

/**
 *
 * @author andras
 */
public class Export {

    Mainwindow mainwindow;
    public File fileExportPath;
    JFileChooser fileChooser;

    public Export(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
        this.fileChooser = new JFileChooser();
        this.fileChooser.addChoosableFileFilter(new PngFilter());
        this.fileChooser.setAcceptAllFileFilterUsed(true);
    }

    public void saveToImage(File fileName) {
        try {
            trySaveToImage(fileName);
        } catch (IOException ex) {
            System.err.println("Error during writing the image to file!");
        }
    }

    private void trySaveToImage(File fileName) throws IOException {
        int width = mainwindow.table.getWidth();
        int height = mainwindow.table.getHeight();
        mainwindow.table.setGridColor(Color.white);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        mainwindow.table.paint(g2);
        g2.dispose();
        ImageIO.write(bi, "png", new File(fileName.toString())); //NOI18N
        mainwindow.table.setGridColor(Color.black);
    }

    public void saveCopyToImage(BufferedImage bi, File fileName) {
        try {
            trySaveCopyToImage(bi, fileName);
        } catch (IOException ex) {
            System.err.println("Error during writing the image to file!");
        }
    }

    private void trySaveCopyToImage(BufferedImage bi, File fileName) throws IOException {
        ImageIO.write(bi, "png", new File(fileName.toString())); //NOI18N
        mainwindow.table.setGridColor(Color.black);
    }

    public void exportGridToPng() {
        int res = this.fileChooser.showSaveDialog(mainwindow.getContentPane());
        if (res == JFileChooser.APPROVE_OPTION) {
            fileExportPath = this.fileChooser.getSelectedFile();
            fileExportPath = new File(mainwindow.controller.model.prepareFilePathExtension(fileExportPath.getPath(), "png")); //NOI18N
            mainwindow.controller.export.saveToImage(this.fileExportPath);
        }
    }

    public void exportOriginGridToPng(java.awt.event.ActionEvent evt) {
        int res = this.fileChooser.showSaveDialog(mainwindow.getContentPane());
        if (res == JFileChooser.APPROVE_OPTION) {
            fileExportPath = this.fileChooser.getSelectedFile();
            fileExportPath = new File(mainwindow.controller.model.prepareFilePathExtension(fileExportPath.getPath(), "png")); //NOI18N
            saveCopyToImage(mainwindow.controller.model.tableCopyBi, this.fileExportPath);
        }
    }

}
