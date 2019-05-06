package com.example.ajr;

public class WordSearchRequest {
    private String wordToFind = "";
    private String wordSearch = "";

    public WordSearchRequest(String findWord, String wsMatrix){
        wordToFind = findWord;
        wordSearch = wsMatrix;
    }

    public String getWordToFind(){return wordToFind;}
    public String getWordSearch(){return wordSearch;}

}
