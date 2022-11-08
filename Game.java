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
        String fact1, fact2, fact3;
        fact1 = "80% of all people living in extreme poverty\n" +
                "comes from South Asia and Sub Sahara.\n";
        fact2 = "Today, over 700 million people live in extreme\n" +
                "poverty.\n";
        fact3 = "The international poverty line is $2,15.\n";
        handbook.setFact("#1", fact1);
        handbook.setFact("#2", fact2);
        handbook.setFact("#3", fact3);
    }

    private void createInventory() {
        inventory.addItem(new Coin("coin", 100));
        inventory.addItem(new Coin("coin", 150));
        inventory.addItem(new Coin("coin", 50));
    }

    private void createRooms() {
        Room building, north, east, south, west;

        building = new Room("in the FN headquarters");
        north = new Room("in the north of the park");
        east = new Room("in the east of the park");
        south = new Room("in the south of the park");
        west = new Room("in the west of the park");

        // Create persons for the rooms
        Person john, hans, lene, mathias;
        john = new Person(
                "John",
                "\nHello, Bobby.\n" +
                        "\nAre you ready to help fight extreme poverty? \n" +
                        "\nWe are always short of hands and funds in our\n" +
                        "organisation.\n" +
                        "\nUse your UNICEF handbook as a way to persuade \n" +
                        "the people you meet to donate for our cause,\n" +
                        "or join us as a volunteer.\n" +
                        "\nYou can start in the park just outside this building.\n" +
                        "\nReturn to me, when you are done.\n" +
                        "\nGood luck!\n");
        hans = new Person("Hans", "Hello, Bobby");
        lene = new Person("Lene", "Hello, you handsome fella");
        mathias = new Person("Mathias", "Please go away");

        // Position the persons in the rooms
        building.setPersons("John", john);
        north.setPersons("Lene", lene);
        east.setPersons("Mathias", mathias);
        south.setPersons("Hans", hans);

        building.setExit("outside", north);

        north.setExit("inside", building);
        north.setExit("east", east);
        north.setExit("south", south);
        north.setExit("west", west);

        east.setExit("north", north);
        east.setExit("south", south);
        east.setExit("west", west);

        south.setExit("north", north);
        south.setExit("east", east);
        south.setExit("west", west);

        west.setExit("north", north);
        west.setExit("south", south);
        west.setExit("east", east);

        currentRoom = building;
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
        if (choice.equals("inventory") || choice.equals("Inventory")) {
            return "inventory";
        } else if (choice.equals("handbook") || choice.equals("Handbook")) {
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
            return false; // return false if there is no second word in command
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
        return !command.hasCommandValue();
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