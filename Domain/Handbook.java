package BobbyHood.Domain;

import java.util.HashMap;

public class Handbook {

    private String titel;
    private HashMap<String, String> facts;

    Handbook(String titel) {
        this.titel = titel;
        facts = new HashMap<String, String>();
    }

    public void setFact(String key, String fact) {
        facts.put(key, fact);
    }

    public String getTitel() {
        return titel;
    }

    public String printHandbook(Handbook handbook) {
        String returnString = "\n  " + handbook.getTitel() + " \n";
        for(String fact : facts.values()) {
            returnString += "-- " + fact + "";
        }
        return returnString;
    }
}
