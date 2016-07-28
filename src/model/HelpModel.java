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

import javax.swing.tree.TreePath;
import view.HelpDialog;

/**
 *
 * @author andras
 */
public class HelpModel {

    HelpDialog helpdialog;
    String applicationVersion;
    String applicationName;
    String applicationAuthor;
    String applicationCopyright;

    public HelpModel(HelpDialog helpdialog) {
        this.helpdialog = helpdialog;
    }

    public void helpAction(java.awt.event.MouseEvent evt) {
        TreePath tp = helpdialog.helpTree.getPathForLocation(evt.getX(), evt.getY());
        String style = "<style>\n" + //NOI18N
"h1 {\n" + //NOI18N //NOI18N
"	font-family: sans;\n" + //NOI18N //NOI18N
"	color: blue;\n" + //NOI18N //NOI18N
"}\n" + //NOI18N
"h2 {\n" + //NOI18N
"	font-family: sans;\n" + //NOI18N
"}\n" + //NOI18N
"</style>"; //NOI18N
        
        if (tp != null) {
            String selected = tp.getLastPathComponent().toString();
            if (selected.equals("About")) {
                helpdialog.helpTextPane.setText(
                        "<!doctype html><html><head>" +  //NOI18N
                        style + 
                        "</head><body><H1>" + //NOI18N
                        "About jWordSearch" +
                        "</H1>" +
                        applicationName + 
                        " " +  //NOI18N
                        applicationVersion + 
                        "<br>" + //NOI18N
                        "Autohor" + 
                        ": " + 
                        applicationAuthor + 
                        "<br>" + //NOI18N
                        applicationCopyright + 
                        "<br><br>" + //NOI18N
                        "This is the word search maker." +
                        "</BODY></HTML>");
            }
            if (selected.equals("Usage")) {
                String helpContent = null;
                helpContent = FileUtils.readFileToString(
                        helpdialog.mainwindow.controller.model.helpFilePath);
                helpdialog.helpTextPane.setText(helpContent);
                helpdialog.helpTextPane.setCaretPosition(0);
            }
        }
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setApplicationAuthor(String applicationAuthor) {
        this.applicationAuthor = applicationAuthor;
    }

    public void setApplicationCopyright(String applicationCopyright) {
        this.applicationCopyright = applicationCopyright;
    }

}
