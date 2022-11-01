package worldOfZuul;

import java.util.*;


public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private static ArrayList<Person> persons;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.persons = new ArrayList<Person>();
    }

    public String getPersons() {
        String allnames = "";
        for(int i=0; i<persons.size();i++) {
            allnames += "" + persons.get(i).getName() + "\n";
        }
        return allnames;
    }


    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public void setPersons(Person person) {
        persons.add(person);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + "The persons in the room: " + "\n" + getPersons() + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

}

