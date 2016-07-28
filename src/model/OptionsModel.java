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
