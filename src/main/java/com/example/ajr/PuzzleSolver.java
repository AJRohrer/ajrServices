package com.example.ajr;

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
