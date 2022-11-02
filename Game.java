package BobbyHood;

import java.util.List;

public class Game {

    private Room currentRoom;
    private CommandWords commands;
    private Person currentPerson;

    private Handbook handbook = new Handbook("UNICEF Handbook");;
    private Inventory inventory = new Inventory();

    public Game() {
        createRooms();
        createHandbook();
        createInventory();
        commands = new CommandWordsImplementation();
    }

    private void createHandbook() {
        String fact1 = "This is fact 1";
        String fact2 = "This is fact 2";
        String fact3 = "This is fact 3";
        handbook.setFact("Fact 1", fact1);
        handbook.setFact("Fact 2", fact2);
        handbook.setFact("Fact 3", fact3);
    }

    private void createInventory() {
        inventory.addItem(new Coin("Æble", 100));
        inventory.addItem(new Coin("coin", 150));
        inventory.addItem(new Coin("coin", 50));
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

    public String open(Command command) {
        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            return "wrong";
        }

        String choice = command.getCommandValue();
        if (choice.equals("inventory")) {
            return "inventory";
        } else if (choice.equals("handbook")) {
            handbook.printHandbook(handbook);
            return "handbook";
        }
        return "wrong";
    }

    public String getHandbook() {
        return handbook.printHandbook(handbook);
    }

    public String getInventory() {
        return inventory.printInventory(inventory);
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