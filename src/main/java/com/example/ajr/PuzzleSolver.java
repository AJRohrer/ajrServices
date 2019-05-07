package com.example.ajr;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

final class PuzzleSolver {

    private static ArrayList<Location> _allLocations = new ArrayList<>();
    private static int i = 0;
    private static int j = 0;

    private PuzzleSolver() {}


    public static ArrayList<Location> solvePuzzle(String WordToFind, char[][] WordSearchArray){
        ArrayList<ArrayList<Location>> locations = getAllStrings(WordSearchArray);

        ArrayList<ArrayList<Location>> allSolvedLocations = findAndAddLocations(locations,WordSearchArray,WordToFind);

        return makeFinalList(allSolvedLocations);
    }

    private static ArrayList<Location> makeFinalList(ArrayList<ArrayList<Location>> allSolvedLocations){
        ArrayList<Location> finalSolvedLocations = new ArrayList<>();

        for (ArrayList<Location> locList : allSolvedLocations){
            for (Location loc : locList) {

                boolean locExists = false;
                for (int i = 0; i < finalSolvedLocations.size(); i++){
                    Location lToTest = finalSolvedLocations.get(i);
                    if (loc.ele1() == lToTest.ele1() && loc.ele2() == lToTest.ele2()){
                        if (loc.isUsedLetter == true && lToTest.isUsedLetter == false){
                            finalSolvedLocations.get(i).isUsedLetter = true;
                        }
                        locExists = true;
                    }
                }

                if (locExists == false) finalSolvedLocations.add(loc);

            }
        }

        return finalSolvedLocations;
    }

    private static boolean locListContains(Location loc, ArrayList<Location> finalSolvedLocations){
        for (int i = 0; i < finalSolvedLocations.size(); i++){
            Location lToTest = finalSolvedLocations.get(i);
            if (loc.ele1() == lToTest.ele1() && loc.ele2() == lToTest.ele2()){
                if (loc.isUsedLetter == false && lToTest.isUsedLetter == true){
                    finalSolvedLocations.get(i).isUsedLetter = true;
                }
                return true;
            }
        }
        return false;
    }

    private static ArrayList<ArrayList<Location>> findAndAddLocations(ArrayList<ArrayList<Location>> arrStrings, char[][] wsArray, String wordToFind){
        //loop through each array of positions (strings) generated from each possible direction.
        for (int x = 0; x < arrStrings.size();x++){
            int wordToFindCount = 0;
            boolean fullMatchFound = false;

            //loop through the positions of each string
            for (int strLocCount = 0; strLocCount < arrStrings.get(x).size(); strLocCount++){
                //if the current letter matches the first letter of the word to find
                 if (arrStrings.get(x).get(strLocCount).letter() == wordToFind.charAt(wordToFindCount)){

                    //peek ahead without incrementing strLocCount to see if there is a full match.
                    for (int matchCount = 0; matchCount < wordToFind.length(); matchCount++){
                        //if we aren't at the end of the array
                        if (arrStrings.get(x).size() - 1  >= strLocCount + matchCount) {
                            if (arrStrings.get(x).get(strLocCount + matchCount).letter() == wordToFind.charAt(wordToFindCount + matchCount)) {
                                fullMatchFound = true;
                            } else {
                                fullMatchFound = false;
                                break;
                            }
                        } else {
                            fullMatchFound = false;
                            break;
                        }
                    }
                    //if there is a full match to the word to find flip all locations to true
                    if (fullMatchFound == true){
                        for (int i = 0; i < wordToFind.length();i++){
                            //flip the locations to true that are used
                            arrStrings.get(x).get(strLocCount + i).setIsUsedLetter(true);
                        }
                    }
                    strLocCount = strLocCount + wordToFind.length();

                }
                //just increment if the letter doesnt match what we're trying to find.
            }
        }

        return arrStrings;
    }


    private static ArrayList<ArrayList<Location>> getAllStrings(char[][] wsArray){
        ArrayList<ArrayList<Location>> allStrings = new ArrayList<>();


        for(ArrayList<Location> ltrarr: leftToRightLocations(wsArray)){ allStrings.add(ltrarr); }

        for (ArrayList<Location> rtlarr: rightToLeftLocations(wsArray)){ allStrings.add(rtlarr); }

        for (ArrayList<Location> ttbarr: topToBottomLocations(wsArray)){ allStrings.add(ttbarr); }

        for (ArrayList<Location> bttarr: bottomToTopLocations(wsArray)) { allStrings.add(bttarr); }

        for (ArrayList<Location> utrarr: upToRightLocations(wsArray)) { allStrings.add(utrarr); }

        for (ArrayList<Location> utlarr: upToLeftLocations(wsArray)) { allStrings.add(utlarr); }

        for (ArrayList<Location> dtlarr: downToLeftLocations(wsArray)) { allStrings.add(dtlarr); }

        for (ArrayList<Location> dtrarr: downToRightLocations(wsArray)) { allStrings.add(dtrarr); }

        return allStrings;
    }

    private static ArrayList<Location> addLineToAggregateList(ArrayList<Location> lineLocations){
        ArrayList<Location> templist = new ArrayList<>();
        for (Location l: lineLocations){ templist.add(l); }
        return templist;
    }

    private static ArrayList<ArrayList<Location>> leftToRightLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allLeftToRight = new ArrayList<>();
        ArrayList<Location> leftToRight = new ArrayList<Location>();

        for (int x = 0; x < wsArray.length; x++){
            for (int z = 0; z < wsArray[0].length; z++){
                leftToRight.add(new Location(false,x,z,wsArray[x][z]));
            }
            allLeftToRight.add(addLineToAggregateList(leftToRight));
            leftToRight.clear();
        }
        return allLeftToRight;
    }

    private static ArrayList<ArrayList<Location>> rightToLeftLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allRightToLeft = new ArrayList<>();
        ArrayList<Location> rightToLeft = new ArrayList<>();

        for (int x = 0; x < wsArray.length; x++){
            for (int z = wsArray[0].length -1; z >= 0; z--){
                rightToLeft.add(new Location(false,x,z, wsArray[x][z]));
            }
            //reverse line order to read lef to right, locations are still remembered where they originally were
            allRightToLeft.add(addLineToAggregateList(rightToLeft));
            rightToLeft.clear();
        }
        return allRightToLeft;
    }

    private static ArrayList<ArrayList<Location>> topToBottomLocations(char[][]wsArray){
        ArrayList<ArrayList<Location>> allTopToBottom = new ArrayList<>();
        ArrayList<Location> topToBottom = new ArrayList<Location>();

        for (int x = 0; x < wsArray[0].length; x++){
            for(int z = 0; z < wsArray.length; z++){
                topToBottom.add(new Location(false,z,x,wsArray[z][x]));
            }
            allTopToBottom.add(addLineToAggregateList(topToBottom));
            topToBottom.clear();
        }
        return allTopToBottom;
    }

    private static ArrayList<ArrayList<Location>> bottomToTopLocations(char[][]wsArray) {
        ArrayList<ArrayList<Location>> allBottomToLeft = new ArrayList<>();
        ArrayList<Location> bottomToTop = new ArrayList<Location>();

        for (int x = wsArray[0].length -1; x >= 0; x--){
            for (int z = wsArray.length -1; z >= 0; z--){
                bottomToTop.add(new Location(false,z,x,wsArray[z][x]));
            }
            allBottomToLeft.add(addLineToAggregateList(bottomToTop));
            bottomToTop.clear();
        }
        return allBottomToLeft;
    }

    private static ArrayList<ArrayList<Location>> downToRightLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allDownToRight = new ArrayList<>();
        ArrayList<Location> downToRight = new ArrayList<>();

        for (int z = wsArray[0].length - 3; z >=0; z--){
            int tempz = z;

            for (int x = 0; x < wsArray.length; x++){
                downToRight.add(new Location(false, x,z,wsArray[x][z]));
                z++;
                if (z > wsArray[0].length - 1) break;
            }
            allDownToRight.add(addLineToAggregateList(downToRight));
            downToRight.clear();
            z = tempz;
        }

        for (int x = 1; x <= wsArray.length - 3; x++){
            int tempx = x;

            for (int z = 0; z < wsArray[0].length; z++){
                downToRight.add(new Location(false,x,z,wsArray[x][z]));
                x++;
                if (x > wsArray.length - 1) break;
            }
            allDownToRight.add(addLineToAggregateList(downToRight));
            downToRight.clear();
            x = tempx;
        }

        return allDownToRight;
    }

    private static ArrayList<ArrayList<Location>> downToLeftLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allDownToLeft = new ArrayList<>();
        ArrayList<Location> downtoLeft = new ArrayList<>();

        for(int z  = 2; z < wsArray[0].length; z++){
            int tempz = z;
            for (int x  = 0; x < wsArray.length; x++){
                downtoLeft.add(new Location(false, x,z,wsArray[x][z]));
                z--;
                if (z < 0) break;
            }
            allDownToLeft.add(addLineToAggregateList(downtoLeft));
            downtoLeft.clear();
            z = tempz;
        }

        for (int x = 1; x <= wsArray.length - 3; x++){
            int tempx = x;

            for (int z = wsArray[0].length-1; z >=0;z--){
                downtoLeft.add(new Location(false,x,z,wsArray[x][z]));
                x++;
                if (x > wsArray.length - 1) break;
            }
            allDownToLeft.add(addLineToAggregateList(downtoLeft));
            downtoLeft.clear();
            x = tempx;
        }

        return allDownToLeft;
    }

    private static ArrayList<ArrayList<Location>> upToRightLocations(char[][] wsArray){
        ArrayList<ArrayList<Location>> allUpToRight = new ArrayList<>();
        ArrayList<Location> upToRight = new ArrayList<>();

        for (int x = wsArray.length-1; x >= 2; x--){
            int tempx = x;

            for (int z = 0; z < wsArray[0].length; z++){
                upToRight.add(new Location(false,x,z,wsArray[x][z]));
                x--;
                if (x < 0) break;
            }
            allUpToRight.add(addLineToAggregateList(upToRight));
            upToRight.clear();
            x = tempx;
        }

        for (int z = 1; z < wsArray[0].length-2;z++){
            int tempz = z;

            for(int x = wsArray.length-1;x >= 0; x--){
                upToRight.add(new Location(false,x,z,wsArray[x][z]));
                z++;
                if (z > wsArray[0].length -1) break;
            }
            allUpToRight.add(addLineToAggregateList(upToRight));
            upToRight.clear();
            z = tempz;
        }

        return allUpToRight;
    }

    private static ArrayList<ArrayList<Location>> upToLeftLocations(char[][] wsArray){

        ArrayList<ArrayList<Location>> allUpToLeft = new ArrayList<>();
        ArrayList<Location> upToLeft = new ArrayList<>();

        for (int z = 2; z < wsArray[0].length; z++){
            int tempz = z;
            for (int x = wsArray.length-1; x >= 0; x--){
                upToLeft.add(new Location(false, x,z,wsArray[x][z]));
                z--;
                if (z < 0) break;
            }
            allUpToLeft.add(addLineToAggregateList(upToLeft));
            upToLeft.clear();
            z = tempz;
        }

        for (int x = wsArray.length-2; x >= 2; x--){
            int tempx = x;

            for (int z = wsArray[0].length -1; z >=0; z--){
                upToLeft.add(new Location(false,x,z,wsArray[x][z]));
                x--;
                if (x < 0) break;
            }
            allUpToLeft.add(addLineToAggregateList(upToLeft));
            upToLeft.clear();
            x = tempx;
        }
        return allUpToLeft;
    }
}
