package com.example.ajr;

public class Location {
    private boolean _isUsedLetter = false;
    private int _xPos = -1;
    private int _yPos = -1;

    public Location(boolean isUsed, int xPos, int yPos){
        _isUsedLetter = isUsed;
        _xPos = xPos;
        _yPos = yPos;
    }
}
