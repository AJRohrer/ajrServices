package com.example.ajr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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

}
