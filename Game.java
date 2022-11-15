package BobbyHood;

import java.util.List;
import java.util.Scanner;

public class Game {

    private Room currentRoom;
    private CommandWords commands;

    private Player bobby;
    private Person currentPerson;

    private Handbook handbook = new Handbook("UNICEF Handbook");;
    private Inventory inventory = new Inventory();

    public Game() {
        createPlayer();
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
        this.inventory = new Inventory();
       // inventory.addItem(new Coin("coin", 100));
       // inventory.addItem(new Coin("coin", 150));
       // inventory.addItem(new Coin("coin", 50));
    }

    private void createPlayer() {
        this.bobby = new Player();
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

        // Create Hans and dialogs with Hans
        String hansQuestion = new String("Your answers can be: \n" +
                "1: 100 million\n" +
                "2: 500 million\n" +
                "3: 700 million\n" +
                "type 'open handbook' if you need help to find the right answer");
        hans = new Person("Hans", "Hello, Bobby", hansQuestion, 3, new Coin(450));
        hans.setDialog(new String[]{
                "Hi there, Bobby. My name is " + hans.getName() + ".",
                "Oh no, that sounds horrible. Here, I'll donate " + hans.getValue() + " coins to your cause.",
                "Sure. I’ll donate a 200 coins."
        });
        String[] hansDialog = new String[]{
                "Hello. My name is Bobby.",
                "Did you know that ___ million people live in extreme poverty.",
                "Thank you"
        };
        bobby.setDialog(hans, hansDialog);

        // Create Lene and dialogs with Lene
        String leneQuestion = new String("The international poverty line is: \n" +
                "1: $2,15\n" +
                "2: $4,50\n" +
                "3: $7,00\n" +
                "type 'open handbook' if you need help to find the right answer");
        lene = new Person("Lene", "Hello, you handsome fella", leneQuestion, 1, new Coin(100));
        lene.setDialog(new String[]{
                "Hi there, handsome Bobby. My name is " + hans.getName() + ".",
                "Really?! I can't believe what I'm hearing. Here, I'll donate " + lene.getValue() + " coins to your cause.",
                "Sure. I’ll donate a 200 coins."
        });
        String[] leneDialog = new String[]{
                "Hello. My name is Bobby.",
                "The international poverty line is ____ .",
                "Thank you"
        };
        bobby.setDialog(lene, leneDialog);



        mathias = new Person("Mathias", "Please go away");

        // Position the persons in the rooms
        building.setPersons("John", john);
        north.setPersons("Lene", lene);
        east.setPersons("Mathias", mathias);
        north.setPersons("Hans", hans);

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

    public boolean startDialog(Command command) {
        if (!command.hasCommandValue()) {
            return false; // return false if there is no second word in command
        }

        //
        System.out.println(bobby.getDialog(currentPerson, 0));
        System.out.println(currentPerson.getDialog(0));
        System.out.println();
        System.out.println(bobby.getDialog(currentPerson, 1));
        System.out.println(currentPerson.getQuestion());
        Scanner scanner = new Scanner(System.in);
        boolean correctAnswer = true;
        while (correctAnswer) {
            int index = currentPerson.getCorrectAnswerIndex();
            int use = scanner.nextInt();
            if (use == index) {
                System.out.println(currentPerson.getDialog(1));
                correctAnswer = false;
                inventory.addItem(currentPerson.getItem());
                System.out.println("\033[3m" + currentPerson.getValue() + " COINS WAS ADDED TO YOU INVENTORY\033[0m");
                System.out.println(bobby.getDialog(currentPerson, 2));
            } else {
                System.out.println("Please try again");
            }
        }
        return true;
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