package com.intertechlgbt.markup;

/**
 * Created by scaladojo on 30/08/2014.
 */
public class UserPronounPreferences {

    private final String userId;
    private final Pronouns pronouns;


    public UserPronounPreferences(String userId, Pronouns pronouns) {
        this.userId = userId;
        this.pronouns = pronouns;
    }

    public String getUserId() {
        return userId;
    }

    public Pronouns getPronouns() {
        return pronouns;
    }
}
