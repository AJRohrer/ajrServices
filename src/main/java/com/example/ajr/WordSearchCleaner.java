package com.example.ajr;

final class WordSearchCleaner {

    private WordSearchCleaner() {}

    public static String cleanWordSearch(String dirtyWordSearch){
        dirtyWordSearch = dirtyWordSearch.replaceAll(" ", "");
        dirtyWordSearch = dirtyWordSearch.replaceAll("\n\n", "\n");
        return dirtyWordSearch;
    }

}
