package BobbyHood;

import java.util.*;


public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Person> persons;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        persons = new HashMap<String, Person>();
    }

    public String getPersons() {
        String returnString = "";
        Set<String> keys = persons.keySet();
        for(String person : keys) {
            returnString += "" + persons.get(person).getName() + "\n";
        }
        return returnString;
    }


    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public void setPersons(String name, Person person) {
        persons.put(name.toLowerCase(), person);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + getShortDescription() + ".\n" + "You can talk to these persons: " + "\n" + getPersons() + getExitString();
    }

    private String getExitString() {
        String returnString = "\nGo to the:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Person getPerson(String person) {
        return persons.get(person.toLowerCase());
    }
}

