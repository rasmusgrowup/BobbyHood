package BobbyHood;

import java.util.HashMap;
import java.util.Set;

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
        String returnString = "## " + handbook.getTitel() + " ##\n";
        for(String fact : facts.values()) {
            returnString += "-- " + fact + "\n \n";
        }
        return returnString;
    }
}
