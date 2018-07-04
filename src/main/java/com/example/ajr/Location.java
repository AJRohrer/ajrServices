package com.example.ajr;

public class Location {
    public boolean isUsedLetter = false;
    private int _xPos = -1;
    private int _yPos = -1;
    private char _letter;

    public Location(boolean isUsed, int xPos, int yPos, char letterChar){
        isUsedLetter = isUsed;
        _xPos = xPos;
        _yPos = yPos;
        _letter = letterChar;
    }

    public boolean isUsedLetter() {
        return isUsedLetter;
    }

    public int xPos() {
        return _xPos;
    }

    public int yPos() {
        return _yPos;
    }

    public char letter() {
        return _letter;
    }
}
