package com.example.ajr;

import java.util.Comparator;

//Write a custom comparison between locations so they can be placed in the correct order in the array.
public class LocationComparator implements Comparator<Location> {
    public int compare(Location L1, Location L2){
        int i = L1.xPos() - L2.xPos();
        if (i != 0) return i;

        i = L1.yPos() - L2.yPos();
        if (i != 0) return i;

        return 0;
    }
}
