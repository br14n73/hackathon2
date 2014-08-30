package com.intertechlgbt.markup;

/**
 * Created by scaladojo on 30/08/2014.
 */
public class Pronouns {

    private final String they;
    private final String them;
    private final String their;
    private final String themselves;
    private final String theirs;


    public Pronouns(String they, String them, String their, String themselves, String theirs) {
        this.they = they;
        this.them = them;
        this.their = their;
        this.themselves = themselves;
        this.theirs = theirs;
    }

    public String getThey() {
        return they;
    }

    public String getThem() {
        return them;
    }

    public String getTheir() {
        return their;
    }

    public String getThemselves() {
        return themselves;
    }

    public String getTheirs() {
        return theirs;
    }
}
