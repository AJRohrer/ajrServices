package com.example.ajr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class AjrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AjrApplication.class, args);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(@RequestParam("num") int number){
        return "Hi nerd. This is your number multiplied by 2: " + (number*2);
    }

    @RequestMapping("/katherine")
    public String HiKatherine(){
        return "Hi Katherine, you're a nerd!";
    }

    @RequestMapping("/groceryitem")
    public ArrayList<GroceryItem> getGroceryItemInfo(){
        ArrayList<GroceryItem> gil = new ArrayList<GroceryItem>();
        for(int i = 0; i < 10; i++) {
            GroceryItem gi = new GroceryItem("apple", i);
            gil.add(i,gi);
        }
        return gil;
    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    public GroceryItem addGroceryItem(@RequestBody GroceryItem giPost){
        GroceryItem gi = giPost;
        return gi;
    }

    @RequestMapping(value="/solvewordsearch", method = RequestMethod.POST)
    public ArrayList<Location> solveWordSearch(@RequestParam("WordSearch") String WordSearch, @RequestParam("WordToFind") String WordToFind){
        WordSearch ws = new WordSearch(WordToFind, WordSearch);
        return ws.Solve();
    }
}
