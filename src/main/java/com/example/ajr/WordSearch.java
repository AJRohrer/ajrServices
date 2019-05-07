package com.example.ajr;

import java.util.ArrayList;
import java.util.Arrays;

public class WordSearch {

    private String _wordSearch;
    private String _wordToFind;
    private ArrayList<Location> _solvedLetters;
    private char[][] _wordSearchArray;

    public WordSearch(String WordToFind, String WordSearch){
        _wordToFind = WordToFind;
        _wordSearch = WordSearchCleaner.cleanWordSearch(WordSearch);

        ArrayList<String> lines = new ArrayList<>(Arrays.asList(_wordSearch.split("\\n")));

        //_wordSearchArray[rows, number of rows][columns, number of letters in each row]
        _wordSearchArray = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size();i++){
            for (int j = 0; j < lines.get(i).length(); j++){
                _wordSearchArray[i][j] = lines.get(i).charAt(j);
            }
        }

    }

    public ArrayList<Location> Solve(){
        return PuzzleSolver.solvePuzzle(_wordToFind, _wordSearchArray);
    }
}
