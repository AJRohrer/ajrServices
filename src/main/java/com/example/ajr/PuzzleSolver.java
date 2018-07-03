package com.example.ajr;

import org.graalvm.compiler.word.Word;

import java.util.ArrayList;

final class PuzzleSolver {

    private PuzzleSolver() {}

    public static ArrayList<Location> solvePuzzle(String WordToFind, char[][] WordSearchArray){
        ArrayList<Location> locations = new ArrayList<>();

        for (Location l : readLefttoRight(WordSearchArray, WordToFind)){ locations.add(l); }
        for (Location l : readRighttoLeft(WordSearchArray, WordToFind)){ locations.add(l); }
        for (Location l : readToptoBottom(WordSearchArray, WordToFind)){ locations.add(l); }
        for (Location l : readBottomtoTop(WordSearchArray, WordToFind)){ locations.add(l); }
        for (Location l : readDowntoRight(WordSearchArray, WordToFind)){ locations.add(l); }
        for (Location l : readDowntoLeft(WordSearchArray, WordToFind)){ locations.add(l); }
        for (Location l : readUptoRight(WordSearchArray, WordToFind)){ locations.add(l); }
        for (Location l : readUptoLeft(WordSearchArray, WordToFind)){ locations.add(l); }

        return locations;
    }

    private static ArrayList<Location> readLefttoRight(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();

        for (int i = 0; i < wsArray.length; i++){
            int wordToFindMatchCount = 0;
            for (int j = 0; j < wsArray[i].length; j++){

                if (wsArray[i][j] == WordToFind.charAt(wordToFindMatchCount)){
                    boolean isFullMatch = false;
                    int failedMatchCount = 0;

                    for (int x = j; x < WordToFind.length(); x++){
                        if (wsArray[i][x] == WordToFind.charAt(wordToFindMatchCount)){
                            isFullMatch = true;
                            failedMatchCount++;
                        } else {
                            isFullMatch = false;
                            break;
                        }
                    }

                    if (isFullMatch){
                        for (int x = 0; x < WordToFind.length(); x++){
                            locations.add(new Location(true, j+x, i));
                            j++;
                        }
                    } else {
                        for (int x = 0; x < failedMatchCount; x++){
                            locations.add(new Location(false,j+x, i));
                            j++;
                        }
                    }
                    wordToFindMatchCount = 0;
                } else {
                    locations.add(new Location(false, j, i));
                }
            }
        }

        return locations;
    }

    private static ArrayList<Location> readRighttoLeft(char [][] wsArray, String WordToFind){
        ArrayList<Location> locations = new ArrayList<>();



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


}
