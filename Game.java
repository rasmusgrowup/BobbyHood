package worldOfZuul;

import java.util.List;

public class Game {

    private Room currentRoom;
    private CommandWords commands;
    private Person currentPerson;

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
        Room outside, theatre, pub, lab, office;

        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // Create persons for the rooms
        Person hans, lene, mathias;
        hans = new Person("Hans", "Hello, Booby");
        lene = new Person("Lene", "Hello, you handsome fella");
        mathias = new Person("Mathias", "Please go away");

        // Position the persons in the rooms
        outside.setPersons("Hans", hans);
        outside.setPersons("Lene", lene);
        theatre.setPersons("Mathias", mathias);

        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;
        // set currentPerson to null,
        // because no persons has been engaged
        // at the start of a new game
        currentPerson = null;
    }

    public boolean goRoom(Command command) {

        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            return false;
        }

        String direction = command.getCommandValue();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            return true;
        }
    }

    public boolean talkTo(Command command) {
         if (!command.hasCommandValue()) {
            return false; // return false if there is no command
         }
         // create a string to search for the person
         // in the HashMap of persons
         String person = command.getCommandValue();
         // use that string to find a specific person
         Person engagedPerson = currentRoom.getPerson(person);

        if (engagedPerson == null) {
            return false; // return false if the person doesn't exist
        } else {
            currentPerson = engagedPerson; // Set currentPerson to the person we want to talk to
            return true;
        }
    }

    public boolean quit(Command command) {
        if (command.hasCommandValue()) {
            return false;
        } else {
            return true;
        }
    }

    public String getRoomDescription() {
        return currentRoom.getLongDescription();
    }

    public String getPersonResponse() {
        if (currentPerson.getEngaged()) {
            return currentPerson.getRejected();
        } else {
            currentPerson.setEngaged(true);
            return currentPerson.getResponse();
        }
    }

    public CommandWords getCommands() {
        return commands;
    }

    public List<String> getCommandDescriptions() {
        return commands.getCommandWords();
    }

    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getCommand(word1), word2);
    }
}