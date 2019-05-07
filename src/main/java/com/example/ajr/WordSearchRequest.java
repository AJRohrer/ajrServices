package com.example.ajr;

public class WordSearchRequest {
    public String WordToFind;
    public String WordSearch;

    public WordSearchRequest(String findWord, String wsMatrix){
        WordToFind = findWord;
        WordSearch = wsMatrix;
    }

    public String getWordToFind(){return WordToFind;}
    public String getWordSearch(){return WordSearch;}

}
