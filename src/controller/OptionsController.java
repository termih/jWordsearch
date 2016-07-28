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
