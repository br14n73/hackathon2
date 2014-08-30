package com.intertechlgbt.markup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MarkupController {

	@Autowired
    private MarkupDAO markupDAO;

    @RequestMapping("/setUserPreferences")
    public void setUserPreferences(@RequestParam(value="userId", required=true) String userId,
                                   @RequestParam(value="they", required=true) String they,
                                   @RequestParam(value="them", required=true) String them,
                                   @RequestParam(value="their", required=true) String their,
                                   @RequestParam(value="themselves", required=true) String themselves,
                                   @RequestParam(value="theirs", required=true) String theirs) {
    	UserPronounPreferences userPronounPreferences = new UserPronounPreferences(userId,new Pronouns(they,
                them,
                their,
                themselves,
                theirs));
    	markupDAO.saveUserPronounPreferences(userPronounPreferences);
    }


    @RequestMapping("/getUserPreferences")
    public @ResponseBody() UserPronounPreferences getUserPreferences(@RequestParam(value="userId", required=true) String userId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return markupDAO.loadUserPronounPreferences(userId);
    }

    @RequestMapping("/processMarkup")
    public String processMarkup(@RequestParam(value="userId", required=true) String userId, @RequestParam(value="message", required=true) String message) {
        return message;
    }
}
