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



        return _allLocations;
    }

    private static void findAndAddLocations(ArrayList<Location> locList){

    }


    private static ArrayList<ArrayList<Location>> getAllStrings(char[][] wsArray){
        ArrayList<ArrayList<Location>> allStrings = new ArrayList<>();

        allStrings.add(leftToRightLocations(wsArray));
        allStrings.add(rightToLeftLocations(wsArray));
        allStrings.add(topToBottomLocations(wsArray));
        allStrings.add(bottomToTopLocations(wsArray));
        allStrings.add(upToRightLocations(wsArray));
        allStrings.add(upToLeftLocations(wsArray));
        allStrings.add(downToLeftLocations(wsArray));
        allStrings.add(downToRightLocations(wsArray));

        return allStrings;
    }

    private static ArrayList<Location> leftToRightLocations(char[][] wsArray){
        ArrayList<Location> leftToRight = new ArrayList<Location>();

        for (int x = 0; x < wsArray.length; x++){
            for (int z = 0; z < wsArray[0].length; z++){
                leftToRight.add(new Location(false,x,z,wsArray[x][z]));
            }
        }
        return leftToRight;
    }

    private static ArrayList<Location> rightToLeftLocations(char[][] wsArray){
        ArrayList<Location> rightToLeft = new ArrayList<Location>();

        for (int x = 0; x < wsArray.length; x++){
            for (int z = wsArray[0].length -1; z >= 0; z--){
                rightToLeft.add(new Location(false,x,z, wsArray[x][z]));
            }
        }
        return rightToLeft;
    }

    private static ArrayList<Location> topToBottomLocations(char[][]wsArray){
        ArrayList<Location> topToBottom = new ArrayList<Location>();

        for (int x = 0; x < wsArray[0].length; x++){
            for(int z = 0; z < wsArray.length; z++){
                topToBottom.add(new Location(false,z,x,wsArray[z][x]));
            }
        }
        return topToBottom;
    }

    private static ArrayList<Location> bottomToTopLocations(char[][]wsArray) {
        ArrayList<Location> bottomToTop = new ArrayList<Location>();

        for (int x = wsArray[0].length -1; x >= 0; x--){
            for (int z = wsArray.length -1; z >= 0; z--){
                bottomToTop.add(new Location(false,z,x,wsArray[z][x]));
            }
        }
        return bottomToTop;
    }

    private static ArrayList<Location> downToRightLocations(char[][] wsArray){
        ArrayList<Location> downToRight = new ArrayList<>();

        for (int z = wsArray[0].length - 3; z >=0; z--){

        }


        for (int x = wsArray.length-3; x >=0; x--){
            int tempx = x;
            for (int z = 0; z < wsArray[0].length; z++){
                downToRight.add(new Location(false,x,z, wsArray[x][z]));
                x++;
                if (x > wsArray.length - 1) break;
            }
            x = tempx;
        }

        for (int z = 1; z <= wsArray[0].length; z++){
            int tempz = z;

            for(int x = 0; x < wsArray.length-1;x++){
                downToRight.add(new Location(false,x,z,wsArray[x][z]));
                z++;
                if (z > wsArray[0].length-1) break;
            }

            z = tempz;
        }

        return downToRight;
    }

    private static ArrayList<Location> downToLeftLocations(char[][] wsArray){
        ArrayList<Location> downtoLeft = new ArrayList<>();

        for(int z  = 2; z < wsArray[0].length; z++){
            int tempz = z;
            for (int x  = 0; x < wsArray.length; x++){
                downtoLeft.add(new Location(false, x,z,wsArray[x][z]));
                z--;
                if (z < 0) break;
            }
            z = tempz;
        }

        for (int x = 1; x <= wsArray.length - 3; x++){
            int tempx = x;

            for (int z = wsArray[0].length-1; z >=0;z--){
                downtoLeft.add(new Location(false,x,z,wsArray[x][z]));
                x++;
                if (x > wsArray.length - 1) break;
            }

            x = tempx;
        }

        return downtoLeft;
    }

    private static ArrayList<Location> upToRightLocations(char[][] wsArray){
        ArrayList<Location> upToRight = new ArrayList<>();

        for (int x = wsArray.length-1; x >= 2; x--){
            int tempx = x;

            for (int z = 0; z < wsArray[0].length; z++){
                upToRight.add(new Location(false,x,z,wsArray[x][z]));
                x--;
                if (x < 0) break;
            }

            x = tempx;
        }

        for (int z = 1; z < wsArray[0].length-2;z++){
            int tempz = z;

            for(int x = wsArray.length-1;x >= 0; x--){
                upToRight.add(new Location(false,x,z,wsArray[x][z]));
                z++;
                if (z > wsArray[0].length -1) break;
            }

            z = tempz;
        }

        return upToRight;
    }

    private static ArrayList<Location> upToLeftLocations(char[][] wsArray){
        ArrayList<Location> upToLeft = new ArrayList<>();

        for (int z = 2; z < wsArray[0].length; z++){
            int tempz = z;
            for (int x = wsArray.length-1; x >= 0; x--){
                upToLeft.add(new Location(false, x,z,wsArray[x][z]));
                z--;
                if (z < 0) break;
            }
            z = tempz;
        }

        for (int x = wsArray.length-2; x >= 2; x--){
            int tempx = x;

            for (int z = wsArray[0].length -1; z >=0; z--){
                upToLeft.add(new Location(false,x,z,wsArray[x][z]));
                x--;
                if (x < 0) break;
            }

            x = tempx;
        }
        return upToLeft;
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
