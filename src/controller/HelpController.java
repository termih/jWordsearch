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
