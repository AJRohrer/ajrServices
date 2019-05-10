package com.example.wsSolver;

public class GroceryItem {

    private String itemDescription = "";
    private int itemAisle = -1;

    public GroceryItem(String desc, int aisle){
        itemDescription = desc;
        itemAisle = aisle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemAisle() {
        return itemAisle;
    }
}
