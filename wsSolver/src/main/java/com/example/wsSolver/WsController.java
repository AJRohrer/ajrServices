package com.example.wsSolver;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class WsController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(@RequestParam("num") int number){
        return "Hi nerd. This is your number multiplied by 2: " + (number*2);
    }

    @RequestMapping("/katherine")
    public String HiKatherine(){
        return "Hi Katherine, you're a beautiful wife!";
    }

    @RequestMapping("/groceryitem")
    public ArrayList<GroceryItem> getGroceryItemInfo(){
        ArrayList<GroceryItem> gil = new ArrayList<>();
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

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/solvewordsearch", method = RequestMethod.POST)
    public ArrayList<Location> solveWordSearch(@RequestBody WordSearchRequest wsr){

        WordSearch ws = new WordSearch(wsr.getWordToFind().toUpperCase(), wsr.getWordSearch().toUpperCase());
        return ws.Solve();
    }
}
