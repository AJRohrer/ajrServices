package com.example.wsSolver;

public class Location {
    public boolean isUsedLetter = false;
    public int _ele1 = -1;
    public int _ele2 = -1;
    public char _letter;

    public Location(boolean isUsed, int ele1, int ele2, char letterChar){
        isUsedLetter = isUsed;
        _ele1 = ele1;
        _ele2 = ele2;
        _letter = letterChar;
    }

    public boolean isUsedLetter() {
        return isUsedLetter;
    }

    public void setIsUsedLetter(boolean value) { isUsedLetter = value; }

    public int ele1() {
        return _ele1;
    }

    public int ele2() {
        return _ele2;
    }

    public char letter() {
        return _letter;
    }

    public void setLetter(char l){ _letter = l; }
}

