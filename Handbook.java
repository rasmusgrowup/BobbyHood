package BobbyHood;

import java.util.HashMap;

public class Handbook {

    private String titel;
    private HashMap<String, Fact> facts;

    Handbook(String titel) {
        this.titel = titel;
        facts = new HashMap<String, Fact>();
    }

    public void setFacts(String specificTitel, Fact fact) {
        facts.put(specificTitel, fact);
    }

    public static void main(String[] args) {
        Handbook handbook = new Handbook();
        System.out.println(handbook.printHandbook());
    }
}
