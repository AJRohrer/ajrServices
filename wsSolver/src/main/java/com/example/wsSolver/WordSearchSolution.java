package com.example.wsSolver;

import java.util.ArrayList;

public class WordSearchSolution {
    private ArrayList<ArrayList<Location>> allSolvedLocations = null;
    public ArrayList<Location> finalSolvedWordSearch = null;
    public int numberOfWords = 0;

    public WordSearchSolution(ArrayList<ArrayList<Location>> locations, int numWords){
        allSolvedLocations = locations;
        numberOfWords = numWords;
    }

    public ArrayList<ArrayList<Location>> getAllSolvedLocations(){
        return allSolvedLocations;
    }

    public void setAllSolvedLocations(ArrayList<ArrayList<Location>> newLocationsList){
        allSolvedLocations = newLocationsList;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public ArrayList<Location> getFinalSolvedWordSearch(){
        return finalSolvedWordSearch;
    }

    public void setFinalSolvedWordSearch(ArrayList<Location> finalLocations){
        finalSolvedWordSearch = finalLocations;
    }
}
