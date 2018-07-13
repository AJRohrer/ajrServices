package com.example.ajr;

import java.util.ArrayList;
import java.util.Collections;

final class PuzzleSolver {

    private static ArrayList<Location> _allLocations = new ArrayList<>();
    private static int i = 0;
    private static int j = 0;

    private PuzzleSolver() {}

    public static ArrayList<Location> solvePuzzle(String WordToFind, char[][] WordSearchArray){
        ArrayList<Location> locations = new ArrayList<>();


        readLefttoRight(WordSearchArray, WordToFind);

        return _allLocations;
    }

    private static ArrayList<Location> readLefttoRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (i = 0; i < wsArray.length; i++){

            for (j = 0; j < wsArray[i].length; j++){
                for (Location l : iterateNormal(WordToFind,wsArray)) {
                    locations.add(l);
                }
            }
        }

        return locations;
    }

    private static ArrayList<Location> readRighttoLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (i = 0; i < wsArray.length; i++){

            for (j = wsArray[i].length - 1; j >= 0; j--){
                for (Location l: iterateBackwards(WordToFind,wsArray)){
                    locations.add(l);
                }
            }
        }

        return locations;
    }

    private static ArrayList<Location> readToptoBottom(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (i = 0; i < wsArray[i].length; i++){

            for (j = 0; j < wsArray.length; j++){
                for (Location l : iterateNormal(WordToFind,wsArray)) {
                    locations.add(l);
                }
            }
        }
        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readBottomtoTop(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (i = 0; i < wsArray[0].length; i++) {
            for (j = wsArray.length - 1; j >= 0; j--) {
                for (Location l : iterateBackwards(WordToFind,wsArray)){
                    locations.add(l);
                }
            }
        }
        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readDowntoRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();
        //subtract 4 because the shortest word is 3 letters and arrays start at 0
        for (i = wsArray.length - 4; i >= 0; i--){
            for (j = i; j < wsArray.length; j++){
                for (Location l : iterateDownRight(WordToFind,wsArray)){
                    locations.add(l);
                }
            }
        }
        j = 0;

        //set i to 1 but still only count to length of the first row -3
        for (i = 1; i < wsArray[0].length - 3; i++){
            for (j = 0; j < wsArray[0].length - 3; j++) {
                for (Location l : iterateDownRight(WordToFind, wsArray)) {
                    locations.add(l);
                }
            }
        }
        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readDowntoLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();
        //subtract 4 because the shortest word is 3 letters and arrays start at 0
        for (i = 2; i < wsArray[0].length; i++){
            for (j = 0; j < wsArray.length; j++){
                for (Location l : iterateDownRight(WordToFind,wsArray)){
                    locations.add(l);
                }
            }
        }
        j = 0;

        //set i to 1 but still only count to length of the first row -3
        for (i = 1; i < wsArray[0].length - 3; i++){
            for (j = 0; j < wsArray[0].length - 3; j++) {
                for (Location l : iterateDownRight(WordToFind, wsArray)) {
                    locations.add(l);
                }
            }
        }
        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readDowntoLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();



        return locations;
    }

    private static ArrayList<Location> readUptoRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();



        return locations;
    }

    private static ArrayList<Location> readUptoLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();



        return locations;
    }

    private static ArrayList<Location> iterateBackwards(String wordToFind, char [][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();
        int wordToFindMatchCount = 0;
        if (wsArray[i][j] == wordToFind.charAt(wordToFindMatchCount)){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (wsArray[i][j-x] == wordToFind.charAt(wordToFindMatchCount)){
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
                    locations.add(new Location(true, j-x, i, wsArray[i][j-x]));
                }
                j = j + wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,j-x, i, wsArray[i][j-x]));
                }
                j = j - failedMatchCount + 1; //add one because the for loop will increment it the last required number.
            }

            wordToFindMatchCount = 0;
        } else {
            locations.add(new Location(false, j, i, wsArray[i][j]));
        }
        return locations;
    }

    private static ArrayList<Location> iterateNormal(String wordToFind, char [][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();

        int wordToFindMatchCount = 0;
        if (wsArray[i][j] == wordToFind.charAt(wordToFindMatchCount)){
            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (wsArray[i][x+j] == wordToFind.charAt(wordToFindMatchCount)){
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
                    locations.add(new Location(true, j+x, i, wsArray[i][j+x]));
                }
                j = j + wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,j+x, i, wsArray[i][j+x]));
                }
                j = j + failedMatchCount - 1; //subtract one because the for loop will increment it the last required number.
            }
        } else {
            locations.add(new Location(false, j, i, wsArray[i][j]));
        }

        return locations;
    }

    private static ArrayList<Location> iterateDownRight(String wordToFind, char[][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();
        int wordToFindMatchCount = 0;
        if (wsArray[i][j] == wordToFind.charAt(wordToFindMatchCount)){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (wsArray[i + x][j + x] == wordToFind.charAt(wordToFindMatchCount)){
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
                j = j + wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i + x, j + x, wsArray[i + x][j + x]));
                }
                i = i + failedMatchCount - 1;
                j = j + failedMatchCount - 1; //add one because the for loop will increment it the last required number.
            }

        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
            //no increment i or j because they will be incremented correctly by the loop calling this function.
        }
        return locations;
    }

    private static ArrayList<Location> iterateDownLeft(String wordToFind, char[][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();
        int wordToFindMatchCount = 0;
        if (wsArray[i][j] == wordToFind.charAt(wordToFindMatchCount)){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (wsArray[i + x][j - x] == wordToFind.charAt(wordToFindMatchCount)){
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
                    locations.add(new Location(true, i + x, j - x, wsArray[i + x][j + x]));
                }
                i = i + wordToFind.length() - 1;
                j = j + wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i + x, j - x, wsArray[i + x][j + x]));
                }
                i = i + failedMatchCount - 1;
                j = j - failedMatchCount - 1; //add one because the for loop will increment it the last required number.
            }

        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
            //no increment i or j because they will be incremented correctly by the loop calling this function.
        }
        return locations;
    }

    private static void InsertMoreLocations(ArrayList<Location> newLocations){

        Collections.sort(newLocations, new LocationComparator());

        for (int element = 0; element < newLocations.size(); element++){
            if (newLocations.get(element).isUsedLetter() == true && _allLocations.get(element).isUsedLetter() == false){
                _allLocations.get(element).isUsedLetter = true;
            }
        }
    }


}
