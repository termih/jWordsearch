/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
