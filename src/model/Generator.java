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

import java.util.ArrayList;
import java.util.Random;
import view.Mainwindow;

/**
 *
 * @author andras
 */
public class Generator {

    Mainwindow mainwindow;
    ArrayList<Facility> facilities;
    String[] rightdownDirections = {
        "right", "rightdown", "down" //NOI18N
    };
    String[] roundDirections = {
        "right", "rightdown", "down", "leftdown", //NOI18N
        "left", "leftup", "up", "rightup" //NOI18N
    };
    Random random = new Random();

    public Generator(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
        this.facilities = new ArrayList<>();

    }

    public void genWordsMingle() {
        mainwindow.statusTextField.setText(""); //NOI18N
        int listModelSize = mainwindow.controller.model.wordsModel.size();
        mainwindow.progressBar.setMinimum(0);
        mainwindow.progressBar.setMaximum(listModelSize);
        for (int i = 0; i < listModelSize; i++) {
            String word = mainwindow.controller.model.wordsModel.get(i);
            int progress = mainwindow.progressBar.getValue();
            mainwindow.progressBar.setValue(++progress);
            landWord(word.toLowerCase());
        }
    }

    private void landWord(String word) {
        chargeFacilities();
        boolean wordCheckEnd = false;
        while(facilities.size()>0) {
            Facility facility = new Facility();
            facility = getNextRandomFacility();
            if (facility.direction.equals("right")) { //NOI18N
                if (isGoodPlaceToRight(facility.row, facility.col, word)) {
                    writeRight(facility.row, facility.col, word);
                    wordCheckEnd = true;
                }
            }
            if (facility.direction.equals("down")) { //NOI18N
                if (isGoodPlaceToDown(facility.row, facility.col, word)) {
                    writeDown(facility.row, facility.col, word);
                    wordCheckEnd = true;
                }
            }
            if (facility.direction.equals("rightdown")) { //NOI18N
                if (isGoodPlaceToRightDown(facility.row, facility.col, word)) {
                    writeRightDown(facility.row, facility.col, word);
                    wordCheckEnd = true;
                }
            }
            if (facility.direction.equals("left")) { //NOI18N
                if (isGoodPlaceToLeft(facility.row, facility.col, word)) {
                    writeLeft(facility.row, facility.col, word);
                    wordCheckEnd = true;
                }
            }
            if (facility.direction.equals("leftdown")) { //NOI18N
                if (isGoodPlaceToLeftDown(facility.row, facility.col, word)) {
                    writeLeftDown(facility.row, facility.col, word);
                    wordCheckEnd = true;
                }
            }            
            if (facility.direction.equals("up")) { //NOI18N
                if (isGoodPlaceToUp(facility.row, facility.col, word)) {
                    writeUp(facility.row, facility.col, word);
                    wordCheckEnd = true;
                }
            }
            if (facility.direction.equals("leftup")) { //NOI18N
                if (isGoodPlaceToLeftUp(facility.row, facility.col, word)) {
                    writeLeftUp(facility.row, facility.col, word);
                    wordCheckEnd = true;
                }
            }
            if (facility.direction.equals("rightup")) { //NOI18N
                if (isGoodPlaceToRightUp(facility.row, facility.col, word)) {
                    writeRightUp(facility.row, facility.col, word);
                    wordCheckEnd = true;
                }
            }
            
            if(wordCheckEnd) {
                break;
            }
        }
        if(facilities.size()==0) {
            mainwindow.statusTextField.setText("Unable to place each word");
        }
    }

    private boolean isGoodPlaceToRight(int row, int col, String word) {
        int colCount = (int) mainwindow.colCountSpinner.getValue();
        boolean good = true;
        if (col + word.length() > colCount) {
            good = false;
        } else {
            String writedWord = getWritedWordFromRight(row, col, word.length());
            if (!isGoodWord(word, writedWord)) {
                good = false;
            }
        }
        return good;
    }

    private boolean isGoodPlaceToDown(int row, int col, String word) {
        int rowCount = (int) mainwindow.rowCountSpinner.getValue();
        boolean good = true;
        if (row + word.length() > rowCount) {
            good = false;
        } else {
            String writedWord = getWritedWordFromDown(row, col, word.length());
            if (!isGoodWord(word, writedWord)) {
                good = false;
            }
        }
        return good;
    }

    private boolean isGoodPlaceToRightDown(int row, int col, String word) {
        int colCount = (int) mainwindow.colCountSpinner.getValue();
        int rowCount = (int) mainwindow.rowCountSpinner.getValue();
        boolean good = true;
        if (col + word.length() > colCount || row + word.length() > rowCount) {
            good = false;
        } else {
            String writedWord = getWritedWordFromRightDown(row, col, word.length());
            if (!isGoodWord(word, writedWord)) {
                good = false;
            }
        }
        return good;
    }

    private boolean isGoodPlaceToLeft(int row, int col, String word) {
        boolean good = true;
        if (col - word.length() < -1) {
            good = false;
        } else {
            String writedWord = getWritedWordFromLeft(row, col, word.length());
            if (!isGoodWord(word, writedWord)) {
                good = false;
            }
        }
        return good;
    }

    private boolean isGoodPlaceToLeftDown(int row, int col, String word) {
        int rowCount = (int) mainwindow.rowCountSpinner.getValue();
        boolean good = true;
        if (col - word.length() < -1 || row + word.length() > rowCount) {
            good = false;
        } else {
            String writedWord = getWritedWordFromLeftDown(row, col, word.length());
            if (!isGoodWord(word, writedWord)) {
                good = false;
            }
        }
        return good;
    }    

    private boolean isGoodPlaceToUp(int row, int col, String word) {
        boolean good = true;
        if (row - word.length() < -1) {
            good = false;
        } else {
            String writedWord = getWritedWordFromUp(row, col, word.length());
            if (!isGoodWord(word, writedWord)) {
                good = false;
            }
        }
        return good;
    }

    private boolean isGoodPlaceToLeftUp(int row, int col, String word) {
        boolean good = true;
        if (col - word.length() < -1 || row - word.length() < -1) {
            good = false;
        } else {
            String writedWord = getWritedWordFromLeftUp(row, col, word.length());
            if (!isGoodWord(word, writedWord)) {
                good = false;
            }
        }
        return good;
    }
    
    private boolean isGoodPlaceToRightUp(int row, int col, String word) {
        int colCount = (int) mainwindow.colCountSpinner.getValue();
        boolean good = true;
        if (col + word.length() > colCount || row - word.length() < -1) {
            good = false;
        } else {
            String writedWord = getWritedWordFromRightUp(row, col, word.length());
            if (!isGoodWord(word, writedWord)) {
                good = false;
            }
        }
        return good;
    }    
    
    private String getWritedWordFromRight(int row, int col, int wordLength) {

        StringBuilder sb = new StringBuilder();
        for (int i = col; i < col + wordLength; i++) {
            String str = mainwindow.table.getValueAt(row, i).toString();
            if (str.isEmpty()) {
                str = "."; //NOI18N
            }
            sb.append(str);
        }
        return sb.toString();
    }
    
    private String getWritedWordFromDown(int row, int col, int wordLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = row; i < row + wordLength; i++) {
            String str = mainwindow.table.getValueAt(i, col).toString();
            if (str.isEmpty()) {
                str = "."; //NOI18N
            }
            sb.append(str);
        }
        return sb.toString();
    }
    
    private String getWritedWordFromRightDown(int row, int col, int wordLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            String str = mainwindow.table.getValueAt(row + i, col + i).toString();
            if (str.isEmpty()) {
                str = "."; //NOI18N
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private String getWritedWordFromLeft(int row, int col, int wordLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            String str = mainwindow.table.getValueAt(row, col-i).toString();
            if (str.isEmpty()) {
                str = "."; //NOI18N
            }
            sb.append(str);
        }
        return sb.toString();
    }
 
    private String getWritedWordFromLeftDown(int row, int col, int wordLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            String str = mainwindow.table.getValueAt(row + i, col - i).toString();
            if (str.isEmpty()) {
                str = "."; //NOI18N
            }
            sb.append(str);
        }
        return sb.toString();
    }    

    private String getWritedWordFromUp(int row, int col, int wordLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            String str = mainwindow.table.getValueAt(row - i, col).toString();
            if (str.isEmpty()) {
                str = "."; //NOI18N
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private String getWritedWordFromLeftUp(int row, int col, int wordLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            String str = mainwindow.table.getValueAt(row - i, col - i).toString();
            if (str.isEmpty()) {
                str = "."; //NOI18N
            }
            sb.append(str);
        }
        return sb.toString();
    }    
 
    private String getWritedWordFromRightUp(int row, int col, int wordLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            String str = mainwindow.table.getValueAt(row - i, col + i).toString();
            if (str.isEmpty()) {
                str = "."; //NOI18N
            }
            sb.append(str);
        }
        return sb.toString();
    }    

    private boolean isGoodWord(String word, String writedWord) {
        boolean good = true;
        good = word.matches(writedWord);
        return good;
    }

    private void writeRight(int row, int col, String word) {
        for (int i = 0; i < word.length(); i++) {
            mainwindow.table.setValueAt(word.charAt(i), row, col + i);
        }
    }
    
    private void writeDown(int row, int col, String word) {
        for (int i = 0; i < word.length(); i++) {
            mainwindow.table.setValueAt(word.charAt(i), row + i, col);
        }
    }
    
    private void writeRightDown(int row, int col, String word) {
        for (int i = 0; i < word.length(); i++) {
            mainwindow.table.setValueAt(word.charAt(i), row + i, col + i);
        }
    }

    private void writeLeft(int row, int col, String word) {
        for (int i = 0; i < word.length(); i++) {
            mainwindow.table.setValueAt(word.charAt(i), row, col - i);
        }
    }
    
    private void writeLeftDown(int row, int col, String word) {
        for (int i = 0; i < word.length(); i++) {
            mainwindow.table.setValueAt(word.charAt(i), row + i, col - i);
        }
    }

    private void writeUp(int row, int col, String word) {
        for (int i = 0; i < word.length(); i++) {
            mainwindow.table.setValueAt(word.charAt(i), row - i, col);
        }
    }

    private void writeLeftUp(int row, int col, String word) {
        for (int i = 0; i < word.length(); i++) {
            mainwindow.table.setValueAt(word.charAt(i), row - i, col - i);
        }
    }    
    
    private void writeRightUp(int row, int col, String word) {
        for (int i = 0; i < word.length(); i++) {
            mainwindow.table.setValueAt(word.charAt(i), row - i, col + i);
        }
    }    
    
    public void chargeFacilities() {
        facilities.clear();
        int colCount = (int) mainwindow.colCountSpinner.getValue();
        int rowCount = (int) mainwindow.rowCountSpinner.getValue();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (mainwindow.downRightRadioButton.isSelected()) {
                    for (int k = 0; k < rightdownDirections.length; k++) {
                        Facility facility = new Facility();
                        facility.row = row;
                        facility.col = col;
                        facility.direction = rightdownDirections[k];
                        facilities.add(facility);
                    }
                } else if (mainwindow.roundRadioButton.isSelected()) {
                    for (int k = 0; k < roundDirections.length; k++) {
                        Facility facility = new Facility();
                        facility.row = row;
                        facility.col = col;
                        facility.direction = roundDirections[k];
                        facilities.add(facility);
                    }
                }
            }
        }
    }

    public Facility getNextRandomFacility() {
        Facility facility;
        int facilitySize = facilities.size();
        int numberOfFacility = random.nextInt(facilitySize);
        facility = facilities.get(numberOfFacility);
        facilities.remove(numberOfFacility);
        return facility;
    }
}
