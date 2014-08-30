package com.intertechlgbt.markup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MarkupController {


    @RequestMapping("/setUserPreferences")
    public void setUserPreferences(@RequestParam(value="userId", required=true) String userId,
                                   @RequestParam(value="they", required=true) String they,
                                   @RequestParam(value="them", required=true) String them,
                                   @RequestParam(value="their", required=true) String their,
                                   @RequestParam(value="themselves", required=true) String themselves,
                                   @RequestParam(value="theirs", required=true) String theirs) {

    }


    @RequestMapping("/getUserPreferences")
    public UserPronounPreferences getUserPreferences(@RequestParam(value="userId", required=true) String userId) {
        return new UserPronounPreferences("me",new Pronouns("they","them","their","themselves","theirs"));
    }

    @RequestMapping("/processMarkup")
    public String processMarkup(@RequestParam(value="userId", required=true) String userId, @RequestParam(value="message", required=true) String message) {
        return message;
    }
}
