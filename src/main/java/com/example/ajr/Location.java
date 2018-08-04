package com.example.ajr;

public class Location {
    public boolean isUsedLetter = false;
    private int _ele1 = -1;
    private int _ele2 = -1;
    private char _letter;

    public Location(boolean isUsed, int ele1, int ele2, char letterChar){
        isUsedLetter = isUsed;
        _ele1 = ele1;
        _ele2 = ele2;
        _letter = letterChar;
    }

    public boolean isUsedLetter() {
        return isUsedLetter;
    }

    public int ele1() {
        return _ele1;
    }

    public int ele2() {
        return _ele2;
    }

    public char letter() {
        return _letter;
    }
}

