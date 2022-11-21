package BobbyHood;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {

    private CommandWords commands;
    private Room currentRoom;
    private Person currentPerson;
    private Player bobby;
    private Handbook handbook;
    private Inventory inventory;

    public Game() {
        createPlayer(); // call createPlayer() method
        createRooms(); // call createRoom() method
        populateHandbook(); // call populateHandbook() method
        createInventory(); // call createInventory() method
        commands = new CommandWordsImplementation();
    }

    private void populateHandbook() {
        handbook = new Handbook("UNICEF Handbook"); // new instance of Handbook
        String fact1, fact2, fact3, fact4; // fact variables

        // the strings for the facts
        fact1 = "80% of all people living in extreme poverty\n" +
                "comes from South Asia and Sub Sahara.\n";
        fact2 = "Today, over 700 million people live in extreme\n" +
                "poverty.\n";
        fact3 = "The international poverty line is $2,15.\n";
        fact4 = "160 million children are at risk of continuing\n" +
                "to live in extreme poverty by 2030";

        // set the text for the facts
        handbook.setFact("#1", fact1);
        handbook.setFact("#2", fact2);
        handbook.setFact("#3", fact3);
        handbook.setFact("#4", fact4);
    }

    private void createInventory() {
        inventory = new Inventory(); // new instance of Inventory
    }

    private void createPlayer() {
        bobby = new Player(); // new instance of Player
    }

    private void createRooms() {
        // room variable names
        Room building, north, east, south, west;

        // descriptions for the rooms
        building = new Room("in the UN headquarters");
        north = new Room("in the north of the park");
        east = new Room("in the east of the park");
        south = new Room("in the south of the park");
        west = new Room("in the west of the park");

        // set the room the player starts in
        currentRoom = building;

        // building exit
        building.setExit("outside", north);
        // north exit options
        north.setExit("inside", building);
        north.setExit("east", east);
        north.setExit("south", south);
        north.setExit("west", west);
        // east exit options
        east.setExit("north", north);
        east.setExit("south", south);
        east.setExit("west", west);
        // south exit options
        south.setExit("north", north);
        south.setExit("east", east);
        south.setExit("west", west);
        // west exit options
        west.setExit("north", north);
        west.setExit("south", south);
        west.setExit("east", east);

        // Create persons for the rooms
        Person john, hans, lene, mathias, mia;

        // create and set the dialog for John
        john = new Person(
                "John",
                """

                        Hello, Bobby.

                        Are you ready to help fight extreme poverty?\s

                        We are always short of hands and funds in our
                        organisation.

                        Use your UNICEF handbook as a way to persuade\s
                        the people you meet to donate for our cause,
                        or join us as a volunteer.

                        You can start in the park just outside this building.

                        Return to me, when you are done.

                        Good luck!
                        """);

        // create the options for Hans
        String miaQuestion = "\n" +
                "1: 60 million\n" +
                "2: 160 million\n" +
                "3: 260 million\n";

        // new instance of person, Hans
        mia = new Person(
                "Mia",
                "Female",
                "Mia is a middle aged woman, walking her dog in the park.",
                miaQuestion,
                2,
                1,
                new Coin(200)
        );

        // set the dialog for Hans
        mia.setDialog(new String[]{
                "Nice to meet you Bobby. My name is " + mia.getName() + ".",
                "No way. That's a lot of children.",
                "Here's what I can spare for your cause."
        });

        // set the players dialog for Hans
        String[] miaDialog = new String[]{
                "Hello. My name is Bobby.",
                "According to UN, ___ million children are at risk of continuing\n" +
                "to live in extreme poverty by 2030",
                "Thank you"
        };

        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(mia, miaDialog);

        // create the options for Hans
        String hansQuestion = "\n" +
                "1: 100 million\n" +
                "2: 500 million\n" +
                "3: 700 million\n";

        // new instance of person, Hans
        hans = new Person(
                "Hans",
                "Male",
                "Hans is an elderly looking man, strolling through the park.",
                hansQuestion,
                3,
                2,
                new Coin(400)
        );

        // set the dialog for Hans
        hans.setDialog(new String[]{
                "Hi there, Bobby. My name is " + hans.getName() + ".",
                "Oh no, that sounds horrible.",
                "Sure. I’ll donate to your cause."
        });

        // set the players dialog for Hans
        String[] hansDialog = new String[]{
                "Hello. My name is Bobby.",
                "Did you know that ___ million people live in extreme poverty.",
                "Thank you"
        };

        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(hans, hansDialog);

        // create the options for Lene
        String leneQuestion = "\n" +
                "1: $2,15\n" +
                "2: $4,50\n" +
                "3: $7,00\n";

        // new instance of Lene
        lene = new Person(
                "Lene",
                "Female",
                "Lene is a very attractive business woman. She's walking while talking on the phone",
                leneQuestion,
                1,
                1,
                new Coin(250)
        );

        // set the dialog for person, Lene
        lene.setDialog(new String[]{
                "Hi there, handsome Bobby. My name is " + lene.getName() + ".",
                "Really?! I can't believe what I'm hearing.",
                "Okay. I’ll donate to your cause"
        });

        // set the players dialog with Lene
        String[] leneDialog = new String[]{
                "Hello. My name is Bobby.",
                "Did you know, that the international poverty line is ____ .",
                "Thank you"
        };

        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(lene, leneDialog);

        // create the options for Lene
        String mathiasQuestion = "\n" +
                "1: Africa\n" +
                "2: South Asia and Sub Sahara\n" +
                "3: South America\n";

        // new instance of person, Mathias
        mathias = new Person(
                "Mathias",
                "Male",
                "Mathias is standing outside the university. He studies philosophy.",
                mathiasQuestion,
                2,
                2,
                new Coin(50)
        );

        // set the dialog for person, Lene
        mathias.setDialog(new String[]{
                "Hi there. I'm " + mathias.getName() + ".",
                "That sounds horrible.",
                "I don't have much, but I'll donate a little to your cause."
        });

        // set the players dialog with Lene
        String[] mathiasDialog = new String[]{
                "Hello. My name is Bobby.",
                "80% of all people living in extreme poverty comes from ____.",
                "Thank you"
        };

        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(mathias, mathiasDialog);

        // Position the persons in the rooms
        building.setPersons("John", john);
        north.setPersons("Lene", lene);
        west.setPersons("Mathias", mathias);
        east.setPersons("Hans", hans);
        south.setPersons("Mia", mia);

        // set currentPerson to null,
        // because no persons has been engaged
        // at the start of a new game
        currentPerson = null;
    }

    public boolean goRoom(Command command) {
        // check if there is a command value
        if (!command.hasCommandValue()) {
            return false;
        }

        // get command value and store it as a string
        String direction = command.getCommandValue();
        // get the exit that matches the string
        Room nextRoom = currentRoom.getExit(direction); //

        // if the room has an exit that matches the string
        // we go in that direction. If not, we return false
        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            return true;
        }
    }

    public String open(Command command) {
        // check if the command value is empty
        if (!command.hasCommandValue()) {
            return "empty";
        }

        // get command value and store it as a string
        String choice = command.getCommandValue();

        // check if the command value is inventory or handbook
        if (choice.equals("inventory") || choice.equals("Inventory")) {
            return "inventory";
        } else if (choice.equals("handbook") || choice.equals("Handbook")) {
            return "handbook";
        }
        // if not, return string 'error'
        return "error";
    }

    public String getHandbook() {
        return handbook.printHandbook(handbook);
    }

    public String getInventory() {
        return inventory.printInventory(inventory);
    }


    public boolean talkTo(Command command) {
        // check if the command value is empty
         if (!command.hasCommandValue()) {
            return false;
         }
         // create a string to search for the person
         // in the HashMap of persons
         String person = command.getCommandValue();
         // use that string to find a specific person
         Person engagedPerson = currentRoom.getPerson(person);

         // check if the person exists
         if (engagedPerson == null) {
            return false; // return false if the person doesn't exist
         } else {
            currentPerson = engagedPerson; // Set currentPerson to the person we want to talk to
            return true;
         }
    }

    public boolean startDialog(Command command) {
        // check if the command value is empty
        if (!command.hasCommandValue()) {
            return false;
        }

        // get the boolean value of engaged for the current person
        boolean hasBeenEngagedBefore = currentPerson.getEngaged();

        // we first check if the current person has been engaged before
        // if not, we start up a dialog
        // or else we return a message telling the player
        // that this person has been engaged before
        if (!hasBeenEngagedBefore) {
            // here we start the dialog
            System.out.println(bobby.getDialog(currentPerson, 0)); // the players greeting message
            System.out.println(currentPerson.getDialog(0)); // the current persons response
            System.out.println();
            System.out.println(bobby.getDialog(currentPerson, 1)); // the players question for the person
            System.out.println(currentPerson.getQuestion()); // the possible answers for the question
            boolean dialogActive = true; // boolean that we use to escape the following loop
            boolean firstStep = true;
            boolean secondStep = false;

            // here we handle the users answer
            // if the users answer is not an integer,
            // we catch the exception
            while (firstStep) {
                try {
                    Scanner scanner = new Scanner(System.in); // new scanner
                    int index = currentPerson.getCorrectAnswerIndex(); // get the index of the correct answer
                    int userInput = scanner.nextInt(); // get the users input
                    int amount = currentPerson.getItem().getAmount();
                    if (userInput == index) {
                        // check if the userInput equals the index of the correct answer
                        // if the user input is not a valid number, e.g. too high, print a tip
                        // and if the answer is wrong, print a message, and quit the dialog
                        System.out.println("\033[3mCorrect answer!\033[0m");
                        System.out.println(currentPerson.getDialog(1)); // the persons response if the answer is correct
                        firstStep = false;
                        secondStep = true;
                    } else if (userInput > currentPerson.getDialogLength()){
                        StringBuilder s = new StringBuilder("Please type ");
                        for (int i = 0; i < currentPerson.getDialogLength(); i++) {
                            if (i == currentPerson.getDialogLength() - 1) {
                                s.append("or ").append(i + 1).append(".");
                            } else {
                                s.append("").append(i + 1).append(", ");
                            }
                        } System.out.println(s);
                    } else {
                        currentPerson.getItem().setAmount(amount / 2);
                        System.out.println("Wrong answer. The dialog with " + currentPerson.getName() + " ended. Try again later.\nTip: open your handbook to find the right answer.");
                        break; // escape the dialog
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Not a number. Please try again by typing a number");
                }
            }
            while (secondStep) {
                try {
                    System.out.println("\n\033[3mUse (1)charm or (2)reason to increase " + currentPerson.getName() + "'s donation.\033[0m");
                    Scanner scanner = new Scanner(System.in); // new scanner
                    int index = currentPerson.getCorrectTypeIndex(); // get the index of the correct type
                    int userInput = scanner.nextInt(); // get the users input
                    int amount = currentPerson.getItem().getAmount();
                    if (userInput == index) {
                        System.out.println("\033[3mYou used " + currentPerson.printType(userInput) + " on " + currentPerson.getName() + " and it worked!\033[0m\n");
                        currentPerson.getItem().setAmount(amount * 2); // if the right type is used, increase amount
                    } else if (userInput > 2 || userInput == 0){
                        throw new InputMismatchException();
                    } else {
                        System.out.println("\033[3m" + currentPerson.getName() + " didn't respond well to " + currentPerson.printType(userInput) + " and will not increase " + currentPerson.printGender() + " donations.\033[0m\n");
                        currentPerson.getItem().setAmount(amount); // if the right type is used, increase amount
                    }
                    System.out.println(currentPerson.getDialog(2)); // the current persons response
                    inventory.addItem(currentPerson.getItem()); // add the person's items to the players inventory
                    System.out.println("\033[3mSUCCESS! " + currentPerson.getValue() + " COINS WAS ADDED TO YOUR INVENTORY\033[0m");
                    System.out.println(bobby.getDialog(currentPerson, 2)); // the players goodbye message
                    currentPerson.setEngaged(true); // set the value of engaged to true for the person
                    secondStep = false;
                    break; // escape the dialog;
                } catch (InputMismatchException e) {
                    System.out.println("Please type 1 or 2");
                }
            }
        } else {
            System.out.println(currentPerson.getRejected()); // the person's message if the person has already been engaged
        }
        return true;
    }

    public boolean quit(Command command) {
        return !command.hasCommandValue();
    }

    public String getRoomDescription() {
        return currentRoom.getLongDescription();
    }

    /*
    public String getPersonResponse() {
        if (currentPerson.getEngaged()) {
            return currentPerson.getRejected();
        } else {
            currentPerson.setEngaged(true);
            return currentPerson.getResponse();
        }
    }*/

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