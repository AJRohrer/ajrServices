package com.example.ajr;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

final class PuzzleSolver {

    private static ArrayList<Location> _allLocations = new ArrayList<>();
    private static int i = 0;
    private static int j = 0;

    private PuzzleSolver() {}


    public static ArrayList<Location> solvePuzzle(String WordToFind, char[][] WordSearchArray){
        ArrayList<ArrayList<Location>> locations = new ArrayList<>();

        locations = getAllStrings(WordSearchArray);

        findAndAddLocations(locations,WordSearchArray,WordToFind);

        return _allLocations;
    }

    private static ArrayList<ArrayList<Location>> findAndAddLocations(ArrayList<ArrayList<Location>> arrStrings, char[][] wsArray, String wordToFind){
        ArrayList<ArrayList<Location>> wsBase = new ArrayList<>();

        wsBase = arrStrings;

        //loop through each array of positions (strings) generated from each possible direction.
        for (int x = 0; x < arrStrings.size();x++){
            int wordToFindCount = 0;
            boolean fullMatchFound = false;

            //loop through the positions of each string
            for (int strLocCount = 0; strLocCount < arrStrings.get(x).size(); strLocCount++){
                //if the current letter matches the first letter of the word to find
                if (arrStrings.get(x).get(strLocCount).letter() == wordToFind.charAt(wordToFindCount)){

                    //peek ahead without incrementing strLocCount to see if there is a full match.
                    for (int matchCount = 1; matchCount <= wordToFind.length(); matchCount++){
                        if (arrStrings.get(x).get(strLocCount + matchCount).letter() == wordToFind.charAt(wordToFindCount + matchCount)){
                            fullMatchFound = true;
                        } else {
                            fullMatchFound = false;
                        }
                    }
                    //if there is a full match to the word to find flip all locations to true
                    if (fullMatchFound == true){
                        for (int i = 0; i < wordToFind.length();i++){
                            //flip the locations to true that are used
                            wsBase.get(x + i).get(strLocCount + i).setIsUsedLetter(true);
                        }
                    }
                    strLocCount = strLocCount + wordToFind.length();

                } else {
                    strLocCount++;
                }
            }
        }

        return wsBase;
    }


    private static ArrayList<ArrayList<Location>> getAllStrings(char[][] wsArray){
        ArrayList<ArrayList<Location>> allStrings = new ArrayList<>();


        for(ArrayList<Location> ltrarr: leftToRightLocations(wsArray)){ allStrings.add(ltrarr); }

        for (ArrayList<Location> rtlarr: rightToLeftLocations(wsArray)){ allStrings.add(rtlarr); }

        for (ArrayList<Location> ttbarr: topToBottomLocations(wsArray)){ allStrings.add(ttbarr); }

        for (ArrayList<Location> bttarr: bottomToTopLocations(wsArray)) { allStrings.add(bttarr); }

        for (ArrayList<Location> utrarr: upToRightLocations(wsArray)) { allStrings.add(utrarr); }

        for (ArrayList<Location> utlarr: upToLeftLocations(wsArray)) { allStrings.add(utlarr); }

        for (ArrayList<Location> dtlarr: downToLeftLocations(wsArray)) { allStrings.add(dtlarr); }

        for (ArrayList<Location> dtrarr: downToRightLocations(wsArray)) { allStrings.add(dtrarr); }

        return allStrings;
    }

    private static ArrayList<Location> addLineToAggregateList(ArrayList<Location> lineLocations){
        ArrayList<Location> templist = new ArrayList<>();
        for (Location l: lineLocations){ templist.add(l); }
        return templist;
    }

    private static ArrayList<ArrayList<Location>> leftToRightLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allLeftToRight = new ArrayList<>();
        ArrayList<Location> leftToRight = new ArrayList<Location>();

        for (int x = 0; x < wsArray.length; x++){
            for (int z = 0; z < wsArray[0].length; z++){
                leftToRight.add(new Location(false,x,z,wsArray[x][z]));
            }
            allLeftToRight.add(addLineToAggregateList(leftToRight));
            leftToRight.clear();
        }
        return allLeftToRight;
    }

    private static ArrayList<ArrayList<Location>> rightToLeftLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allRightToLeft = new ArrayList<>();
        ArrayList<Location> rightToLeft = new ArrayList<Location>();

        for (int x = 0; x < wsArray.length; x++){
            for (int z = wsArray[0].length -1; z >= 0; z--){
                rightToLeft.add(new Location(false,x,z, wsArray[x][z]));
            }
            allRightToLeft.add(addLineToAggregateList(rightToLeft));
            rightToLeft.clear();
        }
        return allRightToLeft;
    }

    private static ArrayList<ArrayList<Location>> topToBottomLocations(char[][]wsArray){
        ArrayList<ArrayList<Location>> allTopToBottom = new ArrayList<>();
        ArrayList<Location> topToBottom = new ArrayList<Location>();

        for (int x = 0; x < wsArray[0].length; x++){
            for(int z = 0; z < wsArray.length; z++){
                topToBottom.add(new Location(false,z,x,wsArray[z][x]));
            }
            allTopToBottom.add(addLineToAggregateList(topToBottom));
            topToBottom.clear();
        }
        return allTopToBottom;
    }

    private static ArrayList<ArrayList<Location>> bottomToTopLocations(char[][]wsArray) {
        ArrayList<ArrayList<Location>> allBottomToLeft = new ArrayList<>();
        ArrayList<Location> bottomToTop = new ArrayList<Location>();

        for (int x = wsArray[0].length -1; x >= 0; x--){
            for (int z = wsArray.length -1; z >= 0; z--){
                bottomToTop.add(new Location(false,z,x,wsArray[z][x]));
            }
            allBottomToLeft.add(addLineToAggregateList(bottomToTop));
            bottomToTop.clear();
        }
        return allBottomToLeft;
    }

    private static ArrayList<ArrayList<Location>> downToRightLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allDownToRight = new ArrayList<>();
        ArrayList<Location> downToRight = new ArrayList<>();

        for (int z = wsArray[0].length - 3; z >=0; z--){
            int tempz = z;

            for (int x = 0; x < wsArray.length; x++){
                downToRight.add(new Location(false, x,z,wsArray[x][z]));
                z++;
                if (z > wsArray[0].length - 1) break;
            }
            allDownToRight.add(addLineToAggregateList(downToRight));
            downToRight.clear();
            z = tempz;
        }

        for (int x = 1; x <= wsArray.length - 3; x++){
            int tempx = x;

            for (int z = 0; z < wsArray[0].length; z++){
                downToRight.add(new Location(false,x,z,wsArray[x][z]));
                x++;
                if (x > wsArray.length - 1) break;
            }
            allDownToRight.add(addLineToAggregateList(downToRight));
            downToRight.clear();
            x = tempx;
        }

        return allDownToRight;
    }

    private static ArrayList<ArrayList<Location>> downToLeftLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allDownToLeft = new ArrayList<>();
        ArrayList<Location> downtoLeft = new ArrayList<>();

        for(int z  = 2; z < wsArray[0].length; z++){
            int tempz = z;
            for (int x  = 0; x < wsArray.length; x++){
                downtoLeft.add(new Location(false, x,z,wsArray[x][z]));
                z--;
                if (z < 0) break;
            }
            allDownToLeft.add(addLineToAggregateList(downtoLeft));
            downtoLeft.clear();
            z = tempz;
        }

        for (int x = 1; x <= wsArray.length - 3; x++){
            int tempx = x;

            for (int z = wsArray[0].length-1; z >=0;z--){
                downtoLeft.add(new Location(false,x,z,wsArray[x][z]));
                x++;
                if (x > wsArray.length - 1) break;
            }
            allDownToLeft.add(addLineToAggregateList(downtoLeft));
            downtoLeft.clear();
            x = tempx;
        }

        return allDownToLeft;
    }

    private static ArrayList<ArrayList<Location>> upToRightLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allUpToRight = new ArrayList<>();
        ArrayList<Location> upToRight = new ArrayList<>();

        for (int x = wsArray.length-1; x >= 2; x--){
            int tempx = x;

            for (int z = 0; z < wsArray[0].length; z++){
                upToRight.add(new Location(false,x,z,wsArray[x][z]));
                x--;
                if (x < 0) break;
            }
            allUpToRight.add(addLineToAggregateList(upToRight));
            upToRight.clear();
            x = tempx;
        }

        for (int z = 1; z < wsArray[0].length-2;z++){
            int tempz = z;

            for(int x = wsArray.length-1;x >= 0; x--){
                upToRight.add(new Location(false,x,z,wsArray[x][z]));
                z++;
                if (z > wsArray[0].length -1) break;
            }
            allUpToRight.add(addLineToAggregateList(upToRight));
            upToRight.clear();
            z = tempz;
        }

        return allUpToRight;
    }

    private static ArrayList<ArrayList<Location>> upToLeftLocations(char[][] wsArray){

        ArrayList<ArrayList<Location>> allUpToLeft = new ArrayList<>();
        ArrayList<Location> upToLeft = new ArrayList<>();

        for (int z = 2; z < wsArray[0].length; z++){
            int tempz = z;
            for (int x = wsArray.length-1; x >= 0; x--){
                upToLeft.add(new Location(false, x,z,wsArray[x][z]));
                z--;
                if (z < 0) break;
            }
            allUpToLeft.add(addLineToAggregateList(upToLeft));
            upToLeft.clear();
            z = tempz;
        }

        for (int x = wsArray.length-2; x >= 2; x--){
            int tempx = x;

            for (int z = wsArray[0].length -1; z >=0; z--){
                upToLeft.add(new Location(false,x,z,wsArray[x][z]));
                x--;
                if (x < 0) break;
            }
            allUpToLeft.add(addLineToAggregateList(upToLeft));
            upToLeft.clear();
            x = tempx;
        }
        return allUpToLeft;
    }


























































    //----------------------------------------------------------------------------------------------------------------------------
    private static ArrayList<Location> readLeftToRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (i = 0; i < wsArray.length; i++){

             for (j = 0; j < wsArray[0].length; j++){
                for (Location l : iterateLeftToRight(WordToFind,wsArray)) {
                    locations.add(l);
                }
            }
        }
        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readRightToLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (i = 0; i < wsArray.length; i++){

            for (j = wsArray[0].length - 1; j >= 0; j--){
                for (Location l: iterateRightToLeft(WordToFind,wsArray)){
                    locations.add(l);
                }
            }
        }
        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readTopToBottom(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (j = 0; j < wsArray[0].length; j++){

            for (i = 0; i < wsArray.length; i++){
                for (Location l : iterateTopToBottom(WordToFind,wsArray)) {
                    locations.add(l);
                }
            }
        }
        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readBottomToTop(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (j = 0; j < wsArray[0].length; j++) {
            for (i = wsArray.length - 1; i >= 0; i--) {
                for (Location l : iterateBottomToTop(WordToFind,wsArray)){
                    locations.add(l);
                }
            }
        }
        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readDownToRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        locations.add(new Location(false,wsArray.length - 1, 0, 'X'));
        locations.add(new Location(false, wsArray.length - 2, 0, 'X'));
        locations.add(new Location(false, wsArray.length - 1, 1, 'X'));
        locations.add(new Location(false, 1, wsArray[0].length - 1, 'X'));
        locations.add(new Location(false, 0, wsArray[0].length - 2, 'X'));
        locations.add(new Location(false, 0, wsArray[0].length - 1, 'X'));

        for (i = wsArray.length - 3; i >= 0; i--){
            int tempI = i;
            for (j = 0; j < wsArray[0].length - 1; j++) {
                for (Location l : iterateDownRight(WordToFind, wsArray)) {
                    locations.add(l);
                }
                i++;
                if (i >= wsArray.length) break;
            }
            i = tempI;
        }

        for(j=1; j <= wsArray[0].length - 1; j++) {
            int tempJ = j;
            if (j > wsArray[0].length - 3 && i == 0) break;
            for (i=0; i <= wsArray.length - 1; i++) {
                 for (Location l : iterateDownRight(WordToFind, wsArray)) {
                     locations.add(l);
                 }

                 j++;
                 if (j > wsArray[0].length - 1) break;
            }
            i = 0;
              j = tempJ;
        }

        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readDownToLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();
        //add filler locations for the top left and bottom right corners that don't have words. They will be sorted by the comparator later.
        locations.add(new Location(false, 0,0, 'X')); //public Location(boolean isUsed, int ele1, int ele2, char letterChar){
        locations.add(new Location(false, 0, 1, 'X'));
        locations.add(new Location(false, 1,0, 'X'));
        locations.add(new Location(false, wsArray.length - 2,wsArray[0].length - 1,  'X')); //b
        locations.add(new Location(false,  wsArray.length - 1,wsArray[0].length - 2,  'X')); //c
        locations.add(new Location(false, wsArray.length - 1,wsArray[0].length - 1,  'X')); //a

        for (j = 2; j <= wsArray[0].length - 1; j++){
            int tempJ = j; // hold where we were at top.
            for (i = 0; i <= wsArray.length - 1; i++) {
                for (Location l : iterateDownLeft(WordToFind, wsArray)) {
                    locations.add(l);
                }
                j--;
                if (j < 0) break;
            }
            j = tempJ;
        }



        for(i=1; i <= wsArray.length - 3; i++) {
            int tempI = i;
            for (j = wsArray[0].length - 1; j >= 0; j--) {
                for (Location l : iterateDownLeft(WordToFind, wsArray)) {
                    locations.add(l);
                }
                i++;
                if (i > wsArray.length-1) break;
            }
            i = tempI;
        }

        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readUpToRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        locations.add(new Location(false,wsArray.length - 1, 0, 'X'));
        locations.add(new Location(false, wsArray.length - 2, 0, 'X'));
        locations.add(new Location(false, wsArray.length - 1, 1, 'X'));
        locations.add(new Location(false, 1, wsArray[0].length - 1, 'X'));
        locations.add(new Location(false, 0, wsArray[0].length - 2, 'X'));
        locations.add(new Location(false, 0, wsArray[0].length - 1, 'X'));

        for (i = 2; i < wsArray.length; i++){
            int tempI = i;
            for (j = 0; j <= wsArray.length - 1; j++) {
                for (Location l : iterateUpRight(WordToFind, wsArray)) {
                    locations.add(l);
                }
                i--;
                if (i < 0) break;
            }
            i = tempI;
        }

        for(j = 1; j < wsArray[0].length - 3; j++) {
            for (i = wsArray.length - 1; i >= 0; i--) {
                for (Location l : iterateUpRight(WordToFind, wsArray)) {
                    locations.add(l);
                }
                if (j == wsArray[0].length - 3) break;
            }
        }

        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readUpToLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        locations.add(new Location(false, 0,0, 'X')); //public Location(boolean isUsed, int ele1, int ele2, char letterChar){
        locations.add(new Location(false, 0, 1, 'X'));
        locations.add(new Location(false, 1,0, 'X'));
        locations.add(new Location(false, wsArray.length - 2,wsArray[0].length - 1,  'X')); //b
        locations.add(new Location(false,  wsArray.length - 1,wsArray[0].length - 2,  'X')); //c
        locations.add(new Location(false, wsArray.length - 1,wsArray[0].length - 1,  'X')); //a

        for (j = 2; j <= wsArray[0].length - 1; j++){
            for (i = wsArray.length - 1; i >= 0; i--) {
                for (Location l : iterateUpLeft(WordToFind, wsArray)) {
                    locations.add(l);
                }
                if (j == wsArray[0].length) break;
            }
        }

        for(i = wsArray.length - 1; i >= 3; i--) {
            for (j = wsArray[0].length - 1; j >= 0; j--) {
                for (Location l : iterateUpLeft(WordToFind, wsArray)) {
                    locations.add(l);
                }
                if (i == 3) break;
            }
        }

        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> iterateLeftToRight(String wordToFind, char [][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();

        int wordToFindMatchCount = 0;
        if (Character.toUpperCase(wsArray[i][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                //if we hit the end of the word we don't want to keep looking off the end of the array.
                if (x+j >= wsArray[0].length){
                    isFullMatch = false;
                    break;
                }
                if (Character.toUpperCase(wsArray[i][x+j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
                    isFullMatch = true;
                    failedMatchCount++;
                    wordToFindMatchCount++;
                } else {
                    isFullMatch = false;
                    break;
                }
            }

            if (isFullMatch){
                for (int x = 0; x < wordToFind.length(); x++){
                    locations.add(new Location(true, i, j+x, wsArray[i][j+x]));
                }
                j = j + wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i, j+x, wsArray[i][j+x]));
                }
                j = j + failedMatchCount - 1; //subtract one because the for loop will increment it the last required number.
            }
        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
        }

        return locations;
    }

    private static ArrayList<Location> iterateRightToLeft(String wordToFind, char [][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();

        int wordToFindMatchCount = 0;
        if (Character.toUpperCase(wsArray[i][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (j-x < 0){
                    isFullMatch = false;
                    break;
                }
                if (Character.toUpperCase(wsArray[i][j-x]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
                    isFullMatch = true;
                    failedMatchCount++;
                    wordToFindMatchCount++;
                } else {
                    isFullMatch = false;
                    break;
                }
            }

            if (isFullMatch){
                for (int x = 0; x < wordToFind.length(); x++){
                    locations.add(new Location(true, i, j-x, wsArray[i][j-x]));
                }
                j = j - wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i, j-x, wsArray[i][j-x]));
                }
                j = j - failedMatchCount + 1; //add one because the for loop will increment it the last required number.
            }
        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
        }
        return locations;
    }

    private static ArrayList<Location> iterateTopToBottom(String wordToFind, char [][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();

        int wordToFindMatchCount = 0;
        if (Character.toUpperCase(wsArray[i][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if(i+x >= wsArray.length){
                    isFullMatch = false;
                    break;
                }
                if (Character.toUpperCase(wsArray[i+x][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
                    isFullMatch = true;
                    failedMatchCount++;
                    wordToFindMatchCount++;
                } else {
                    isFullMatch = false;
                    break;
                }
            }

            if (isFullMatch){
                for (int x = 0; x < wordToFind.length(); x++){
                    locations.add(new Location(true, i+x, j, wsArray[i+x][j]));
                }
                i = i + wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i+x, j, wsArray[i+x][j]));
                }
                i = i + failedMatchCount - 1; //subtract one because the for loop will increment it the last required number.
            }
        } else {
            locations.add(new Location(false, j, i, wsArray[i][j]));
        }

        return locations;
    }

    private static ArrayList<Location> iterateBottomToTop(String wordToFind, char [][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();

        int wordToFindMatchCount = 0;
        if (Character.toUpperCase(wsArray[i][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (i-x < 0){
                    isFullMatch = false;
                    break;
                }
                if (Character.toUpperCase(wsArray[i-x][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
                    isFullMatch = true;
                    failedMatchCount++;
                    wordToFindMatchCount++;
                } else {
                    isFullMatch = false;
                    break;
                }
            }

            if (isFullMatch){
                for (int x = 0; x < wordToFind.length(); x++){
                    locations.add(new Location(true, i-x, j, wsArray[i-x][j]));
                }
                i = i - wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i-x, j, wsArray[i-x][j]));
                }
                i = i + failedMatchCount - 1; //subtract one because the for loop will increment it the last required number.
            }
        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
        }

        return locations;
    }

    private static ArrayList<Location> iterateDownRight(String wordToFind, char[][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();
        int wordToFindMatchCount = 0;
        if (Character.toUpperCase(wsArray[i][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (i + x >= wsArray.length || j + x >= wsArray[0].length){
                    isFullMatch = false;
                    break;
                }
                if (Character.toUpperCase(wsArray[i + x][j + x]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
                    isFullMatch = true;
                    failedMatchCount++;
                    wordToFindMatchCount++;
                } else {
                    isFullMatch = false;
                    break;
                }
            }

            if (isFullMatch){
                for (int x = 0; x < wordToFind.length(); x++){
                    locations.add(new Location(true, i + x, j + x, wsArray[i + x][j + x]));
                }
                i = i + wordToFind.length() - 1;
                j = j + wordToFind.length() - 1;
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i + x, j + x, wsArray[i + x][j + x]));
                }
                i = i + failedMatchCount - 1;
                j = j + failedMatchCount - 1;
            }

        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
        }
        return locations;
    }

    private static ArrayList<Location> iterateDownLeft(String wordToFind, char[][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();
        int wordToFindMatchCount = 0;
        if (Character.toUpperCase(wsArray[i][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (i + x >= wsArray[0].length || j - x < 0){
                    isFullMatch = false;
                    break;
                }
                if (Character.toUpperCase(wsArray[i + x][j - x]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
                    isFullMatch = true;
                    failedMatchCount++;
                    wordToFindMatchCount++;
                } else {
                    isFullMatch = false;
                    break;
                }
            }

            if (isFullMatch){
                for (int x = 0; x < wordToFind.length(); x++){
                    locations.add(new Location(true, i + x, j - x, wsArray[i + x][j - x]));
                }

                i = i + wordToFind.length();
                j = j - wordToFind.length();
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i + x, j - x, wsArray[i + x][j - x]));
                }
                i = i + failedMatchCount - 1;
                j = j - failedMatchCount + 1; //add one because the for loop will increment it the last required number.
            }

        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
        }
        return locations;
    }

    private static ArrayList<Location>   iterateUpRight(String wordToFind, char[][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();
        int wordToFindMatchCount = 0;
        if (Character.toUpperCase(wsArray[i][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (i - x < 0 || j + x >= wsArray.length){
                    isFullMatch = false;
                    break;
                }
                if (Character.toUpperCase(wsArray[i - x][j + x]) == (wordToFind.charAt(wordToFindMatchCount))){
                    isFullMatch = true;
                    failedMatchCount++;
                    wordToFindMatchCount++;
                } else {
                    isFullMatch = false;
                    break;
                }
            }

            if (isFullMatch){
                for (int x = 0; x < wordToFind.length(); x++){
                    locations.add(new Location(true, i - x, j + x, wsArray[i - x][j + x]));
                }
                i = i - wordToFind.length() - 1;
                j = j + wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i - x, j + x, wsArray[i - x][j + x]));
                }
                i = i - failedMatchCount - 1;
                j = j + failedMatchCount - 1; //add one because the for loop will increment it the last required number.
            }

        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
            //no increment i or j because they will be incremented correctly by the loop calling this function.
        }
        return locations;
    }

    private static ArrayList<Location> iterateUpLeft(String wordToFind, char[][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();
        int wordToFindMatchCount = 0;
        if (Character.toUpperCase(wsArray[i][j]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (i - x >= 0 || j - x >= 0){
                    isFullMatch = false;
                    break;
                }
                if (Character.toUpperCase(wsArray[i - x][j - x]) == Character.toUpperCase(wordToFind.charAt(wordToFindMatchCount))){
                    isFullMatch = true;
                    failedMatchCount++;
                    wordToFindMatchCount++;
                } else {
                    isFullMatch = false;
                    break;
                }
            }

            if (isFullMatch){
                for (int x = 0; x < wordToFind.length(); x++){
                    locations.add(new Location(true, i - x, j - x, wsArray[i - x][j - x]));
                }
                i = i - wordToFind.length() - 1;
                j = j - wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i - x, j - x, wsArray[i - x][j - x]));
                }
                i = i - failedMatchCount - 1;
                j = j - failedMatchCount - 1; //add one because the for loop will increment it the last required number.
            }

        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
            //no increment i or j because they will be incremented correctly by the loop calling this function.
        }
        return locations;
    }

    //insert letters by sorting the passed in list of letters to make sure that they correspond with the correct matrix element
    //the _allLocations array has a default 0 so make sure to assign the letter regardless if it is used or not.
    private static void InsertMoreLocations(ArrayList<Location> newLocations){

        Collections.sort(newLocations, new LocationComparator());

         for (int element = 0; element < newLocations.size(); element++){
             if (newLocations.get(element).isUsedLetter() == true && _allLocations.get(element).isUsedLetter() == false){
                _allLocations.get(element).isUsedLetter = true;
            }
        }
    }


}
