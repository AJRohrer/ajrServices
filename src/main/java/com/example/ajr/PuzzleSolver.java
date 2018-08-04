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


        for(Location l: readLeftToRight(WordSearchArray, WordToFind)){
            _allLocations.add(l);
        }

        for(Location l: readRightToLeft(WordSearchArray, WordToFind)){
            _allLocations.add(l);
        }

        for(Location l: readTopToBottom(WordSearchArray, WordToFind)){
            _allLocations.add(l);
        }

        for(Location l: readBottomToTop(WordSearchArray, WordToFind)){
            _allLocations.add(l);
        }

        for(Location l: readDownToRight(WordSearchArray, WordToFind)){
            _allLocations.add(l);
        }

        for(Location l: readDownToLeft(WordSearchArray, WordToFind)){
            _allLocations.add(l);
        }

        for(Location l: readUpToRight(WordSearchArray, WordToFind)){
            _allLocations.add(l);
        }

        for(Location l: readUpToLeft(WordSearchArray, WordToFind)){
            _allLocations.add(l);
        }

        return _allLocations;
    }

    private static ArrayList<Location> readLeftToRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (i = 0; i < wsArray.length; i++){

            for (j = 0; j < wsArray[i].length; j++){
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

        for (j = 0; j < wsArray[j].length; j++){

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

        for (i = wsArray.length - 3; i >= 0; i--){
            j = 0;
            for (Location l : iterateDownRight(WordToFind,wsArray)){
                locations.add(l);
            }
        }

        for(j=1; j < wsArray.length - 3; j++) {
            i = 0;
            for(Location l : iterateDownRight(WordToFind,wsArray)){
                locations.add(l);
            }
        }

        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readDownToLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (j = 2; j < wsArray[0].length; j++){
            i = 0;
            for (Location l : iterateDownLeft(WordToFind,wsArray)){
                locations.add(l);
            }
        }

        for(i=1; i < wsArray.length - 3; i++) {
            j = wsArray[0].length;
            for(Location l : iterateDownLeft(WordToFind,wsArray)){
                locations.add(l);
            }
        }

        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readUpToRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (i = 2; i < wsArray.length; i++){
            j = 0;
            for (Location l : iterateUpRight(WordToFind,wsArray)){
                locations.add(l);
            }
        }

        for(j = 1; j < wsArray[0].length - 3; j++) {
            i = wsArray.length;
            for(Location l : iterateUpRight(WordToFind,wsArray)){
                locations.add(l);
            }
        }

        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> readUpToLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (j = 2; j < wsArray[0].length; j++){
            i = wsArray.length;
            for (Location l : iterateUpLeft(WordToFind,wsArray)){
                locations.add(l);
            }
        }

        for(i = wsArray.length - 2; i > 1; i++) {
            j = wsArray[0].length - 1;
            for(Location l : iterateUpLeft(WordToFind,wsArray)){
                locations.add(l);
            }
        }

        i = j = 0;
        return locations;
    }

    private static ArrayList<Location> iterateLeftToRight(String wordToFind, char [][] wsArray){
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
                    locations.add(new Location(true, i, j-x, wsArray[i][j-x]));
                }
                j = j - wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i, j-x, wsArray[i][j-x]));
                }
                j = j - failedMatchCount + 1; //add one because the for loop will increment it the last required number.
            }

            wordToFindMatchCount = 0;
        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
        }
        return locations;
    }

    private static ArrayList<Location> iterateTopToBottom(String wordToFind, char [][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();

        int wordToFindMatchCount = 0;
        if (wsArray[i][j] == wordToFind.charAt(wordToFindMatchCount)){
            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (wsArray[i+x][j] == wordToFind.charAt(wordToFindMatchCount)){
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
        if (wsArray[i][j] == wordToFind.charAt(wordToFindMatchCount)){
            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (wsArray[i-x][j] == wordToFind.charAt(wordToFindMatchCount)){
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
                    locations.add(new Location(true, i-x, j, wsArray[i+x][j]));
                }
                i = i - wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i-x, j, wsArray[i+x][j]));
                }
                i = i - failedMatchCount - 1; //subtract one because the for loop will increment it the last required number.
            }
        } else {
            locations.add(new Location(false, i, j, wsArray[i][j]));
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
                    locations.add(new Location(true, i + x, j - x, wsArray[i + x][j - x]));
                }
                i = i + wordToFind.length() - 1;
                j = j - wordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
            } else {
                for (int x = 0; x < failedMatchCount; x++){
                    locations.add(new Location(false,i + x, j - x, wsArray[i + x][j - x]));
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

    private static ArrayList<Location> iterateUpRight(String wordToFind, char[][] wsArray){
        ArrayList<Location> locations = new ArrayList<>();
        int wordToFindMatchCount = 0;
        if (wsArray[i][j] == wordToFind.charAt(wordToFindMatchCount)){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (wsArray[i - x][j + x] == wordToFind.charAt(wordToFindMatchCount)){
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
        if (wsArray[i][j] == wordToFind.charAt(wordToFindMatchCount)){

            boolean isFullMatch = false;
            int failedMatchCount = 0;

            for (int x = 0; x < wordToFind.length(); x++){
                if (wsArray[i - x][j - x] == wordToFind.charAt(wordToFindMatchCount)){
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

    private static void InsertMoreLocations(ArrayList<Location> newLocations){

        Collections.sort(newLocations, new LocationComparator());

        for (int element = 0; element < newLocations.size(); element++){
            if (newLocations.get(element).isUsedLetter() == true && _allLocations.get(element).isUsedLetter() == false){
                _allLocations.get(element).isUsedLetter = true;
            }
        }
    }


}
