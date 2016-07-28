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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author andras
 */
public class FileUtils {

    public static String readFileToString(String fileName) {
        String fileContent = null;
        try {
            fileContent = fileContent = tryReadFileToString(fileName);
        }catch(FileNotFoundException ex) {
            System.err.println("Error. File not found!");
        }
        return fileContent;
    }
    
    private static String tryReadFileToString(String fileName) throws FileNotFoundException {
        StringBuilder fileContentSb = new StringBuilder();
        FileReader fr = new FileReader(fileName);
        Scanner olvaso = new Scanner(fr);

        while (olvaso.hasNextLine()) {
            fileContentSb.append(olvaso.nextLine());
            fileContentSb.append("\n"); //NOI18N
        }
        return fileContentSb.toString();
    }
}
