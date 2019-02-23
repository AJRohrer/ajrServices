package com.example.ajr;

import java.util.ArrayList;
import java.util.Collections;

final class PuzzleSolver {

    private static ArrayList<Location> _allLocations = new ArrayList<>();
    private static int i = 0;
    private static int j = 0;

    private PuzzleSolver() {}

    private static void InitializeAllLocations(char [][] wsArray){
        for (int x = 0; x < wsArray.length; x++){
            for (int y = 0; y < wsArray[0].length; y++){
                _allLocations.add(new Location(false,x,y,wsArray[x][y]));
            }
        }
    }

    public static ArrayList<Location> solvePuzzle(String WordToFind, char[][] WordSearchArray){
        ArrayList<Location> locations = new ArrayList<>();

        InitializeAllLocations(WordSearchArray);

        InsertMoreLocations(readLeftToRight(WordSearchArray, WordToFind));
        InsertMoreLocations(readRightToLeft(WordSearchArray, WordToFind));
        InsertMoreLocations(readTopToBottom(WordSearchArray, WordToFind));
        InsertMoreLocations(readBottomToTop(WordSearchArray, WordToFind));
        InsertMoreLocations(readDownToRight(WordSearchArray, WordToFind));
        InsertMoreLocations(readDownToLeft(WordSearchArray, WordToFind));
        InsertMoreLocations(readUpToRight(WordSearchArray, WordToFind));
        InsertMoreLocations(readUpToLeft(WordSearchArray, WordToFind));

        return _allLocations;
    }

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
