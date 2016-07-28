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

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author andras
 */
public class WordsearchFilter extends FileFilter {
    private final String[] okFileExtensions
            = new String[]{".wsm"};

    public boolean accept(File file) {
        if(file.isDirectory()) {
            return true;
        }
        for (String extension : okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    public String getDescription() {
        return "*.wsm " + 
               "(Wordsearch maker file)";
    }    
}
