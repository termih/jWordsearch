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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;
import view.Mainwindow;

/**
 *
 * @author andras
 */
public class Options {

    Mainwindow mainwindow;
    final String optionsFile = "jWordSearch.properties"; //NOI18N
    String optionsFileUserPath = null;

    public Options(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
        setupOptionFilePath();
    }

    public void setupOptionFilePath() {
        String proDir = null;
        if (Osverify.getOs().equals("lin")) { //NOI18N
            String linUserHome = System.getenv("HOME"); //NOI18N
            optionsFileUserPath = linUserHome
                    + "/.config/jWordSearch/jWordSearch.properties"; //NOI18N
            proDir = linUserHome + "/.config/jWordSearch"; //NOI18N
        } else if (Osverify.getOs().equals("win")) { //NOI18N
            String winUserSet = System.getenv("LOCALAPPDATA"); //NOI18N
            //c:\Users\joska\AppData\Local\
            optionsFileUserPath = winUserSet
                    + "jWordSearch/jWordSearch.properties"; //NOI18N
            proDir = winUserSet + "/jWordSearch"; //NOI18N
        }
        File proDirFile = new File(proDir);
        if (!proDirFile.exists()) {
            proDirFile.mkdir();
        }

    }

    public void saveOptins() {
        try {
            trySaveOptins();
        } catch (IOException ex) {
            System.err.println("Error during write file!");
        }
    }

    public void trySaveOptins() throws IOException {
        Properties pro = new Properties();
        Integer windowX = mainwindow.getX();
        Integer windowY = mainwindow.getY();
        String authorName = mainwindow.controller.model.authorName;
        String helpFilePath = mainwindow.controller.model.helpFilePath;

        pro.setProperty("windowX", windowX.toString()); //NOI18N
        pro.setProperty("windowY", windowY.toString()); //NOI18N
        pro.setProperty("authorName", authorName); //NOI18N
        pro.setProperty("helpFilePath", helpFilePath); //NOI18N

        FileOutputStream fos;
        if (mainwindow.controller.model.applicationPortable) {
            fos = new FileOutputStream(optionsFile);
        } else {
            fos = new FileOutputStream(optionsFileUserPath);
        }

        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); //NOI18N
        Writer writer = new BufferedWriter(osw);
        pro.store(writer, null);

    }

    public void loadOptions() {
        try {
            tryLoadOptions();
        } catch (IOException ex) {
            System.err.println("Error during load options from file!");
        }
    }

    public void tryLoadOptions() throws IOException {
        String filePath = null;
        if (mainwindow.controller.model.applicationPortable) {
            filePath = optionsFile;            
        }else {
            filePath = optionsFileUserPath;
        }        
        if (new File(filePath).exists()) {
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); //NOI18N
            Reader reader = new BufferedReader(isr);
            Properties pro = new Properties();
            pro.load(reader);
            int x = Integer.parseInt(pro.getProperty("windowX")); //NOI18N
            int y = Integer.parseInt(pro.getProperty("windowY")); //NOI18N
            if (mainwindow.controller.model.authorName.isEmpty()) {
                mainwindow.controller.model.authorName = 
                        "nodata";
            }
            mainwindow.controller.model.authorName = pro.getProperty("authorName"); //NOI18N
            mainwindow.controller.model.helpFilePath = pro.getProperty("helpFilePath"); //NOI18N
            mainwindow.setLocation(x, y);
        }
    }
}
