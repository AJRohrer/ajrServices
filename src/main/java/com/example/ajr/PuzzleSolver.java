package com.example.ajr;



import java.util.ArrayList;
import java.util.Collections;

final class PuzzleSolver {

    private static ArrayList<Location> _allLocations = new ArrayList<>();

    private PuzzleSolver() {}

    public static ArrayList<Location> solvePuzzle(String WordToFind, char[][] WordSearchArray){
        ArrayList<Location> locations = new ArrayList<>();


        readLefttoRight(WordSearchArray, WordToFind);

        return _allLocations;
    }

    private static ArrayList<Location> readLefttoRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < wsArray.length; i++){

            for (int j = 0; j < wsArray[i].length; j++){
                for (Location l : iterateNormal(i,j,WordToFind,wsArray)) {
                    locations.add(l);
                }
            }
        }

        return locations;
    }

    private static ArrayList<Location> readRighttoLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < wsArray.length; i++){

            for (int j = wsArray[i].length - 1; j >= 0; j--){
                for (Location l: iterateBackwards(i,j,WordToFind,wsArray)){
                    locations.add(l);
                }
            }
        }

        return locations;
    }

    private static ArrayList<Location> readToptoBottom(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < wsArray[i].length; i++){

            for (int j = 0; j < wsArray.length; j++){
                for (Location l : iterateNormal(i,j,WordToFind,wsArray)) {
                    locations.add(l);
                }
            }
        }

        return locations;
    }



    private static ArrayList<Location> readBottomtoTop(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < wsArray[0].length; i++) {
            for (int j = wsArray.length - 1; j >= 0; j--) {
                for (Location l : iterateBackwards(i,j,WordToFind,wsArray)){
                    locations.add(l);
                }
            }
        }
        return locations;
    }

    private static ArrayList<Location> readDowntoRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();



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

    private static char[][] InvertArrayHorizontally(char [][] preFlipArray){
        char[][] flippedArray = new char[preFlipArray.length][preFlipArray[0].length];

        int flippedArrayIndex = 0;
        for(int i = 0; i < preFlipArray.length; i++){
            for (int j = preFlipArray[0].length; j >= 0; j--){
                flippedArray[i][flippedArrayIndex] = preFlipArray[i][j];
            }
        }
        return flippedArray;
    }

    private static ArrayList<Location> iterateBackwards(int i, int j, String wordToFind, char [][] wsArray){
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

    private static ArrayList<Location> iterateNormal(int i, int j, String wordToFind, char [][] wsArray){
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

            wordToFindMatchCount = 0;
        } else {
            locations.add(new Location(false, j, i, wsArray[i][j]));
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
