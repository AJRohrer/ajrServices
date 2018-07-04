package com.example.ajr;



import java.util.ArrayList;

final class PuzzleSolver {

    private static ArrayList<Location> _allLocations = new ArrayList<>();

    private PuzzleSolver() {}

    public static ArrayList<Location> solvePuzzle(String WordToFind, char[][] WordSearchArray){
        ArrayList<Location> locations = new ArrayList<>();


        readHorizontalLine(InvertArrayHorizontally(WordSearchArray), WordToFind);

        return _allLocations;
    }

    private static ArrayList<Location> readHorizontalLine(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < wsArray.length; i++){
            int wordToFindMatchCount = 0;
            for (int j = 0; j < wsArray[i].length; j++){

                if (wsArray[i][j] == WordToFind.charAt(wordToFindMatchCount)){
                    boolean isFullMatch = false;
                    int failedMatchCount = 0;

                    for (int x = 0; x < WordToFind.length(); x++){
                        if (wsArray[i][x+j] == WordToFind.charAt(wordToFindMatchCount)){
                            isFullMatch = true;
                            failedMatchCount++;
                            wordToFindMatchCount++;
                        } else {
                            isFullMatch = false;
                            break;
                        }
                    }

                    if (isFullMatch){
                        for (int x = 0; x < WordToFind.length(); x++){
                            locations.add(new Location(true, j+x, i, wsArray[i][j+x]));
                        }
                        j = j + WordToFind.length() - 1; //subtract one because the for loop will increment it the last required number.
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
            }
        }

        return locations;
    }

    private static ArrayList<Location> readToptoBottom(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();



        return locations;
    }

    private static ArrayList<Location> readBottomtoTop(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();



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
    }

    private static void InsertMoreLocations(ArrayList<Location> newLocations){
        for (int element = 0; element < newLocations.size(); element++){
            if (newLocations.get(element).isUsedLetter() == true && _allLocations.get(element).isUsedLetter() == false){
                _allLocations.get(element).isUsedLetter = true;
            }
        }
    }


}
