package BobbyHood.Domain;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {

    private CommandWords commands;
    public static Room currentRoom;

    private NPC currentPerson;
    private Player bobby;
    private John john;
    private Handbook handbook;
    private Inventory inventory;
    private ArrayList<Room> rooms = new ArrayList<>();
    private int personsCompleted;
    boolean isGameCompleted = false;
    private boolean firstVisit = true;
    private int johnIndex = 0;

    public Game() {
        createJohn();
        createPlayer(); // call createPlayer() method
        createRooms(); // call createRoom() method
        getPersonCount();
        populateHandbook(); // call populateHandbook() method
        createInventory(); // call createInventory() method
        commands = new CommandWordsImplementation();
    }

    private void populateHandbook() {
        handbook = new Handbook("HANDBOOK\n"); // new instance of Handbook
        String fact1, fact2, fact3, fact4, fact5, fact6, fact7, fact8, fact9, fact10; // fact variables

        // the strings for the facts
        fact1 = "80% of all people living in extreme poverty\n" +
                "   comes from South Asia and Sub Sahara.\n";
        fact2 = "Today, over 700 million people live in extreme\n" +
                "   poverty.\n";
        fact3 = "The international poverty line is $2,15.\n";
        fact4 = "160 million children are at risk of continuing\n" +
                "   to live in extreme poverty by 2030.\n";
        fact5 = "Almost 22 000 children dies every day\n"+
                "   due to living in poverty.\n";
        fact6 = "1.6 billion people lives without electricity\n";
        fact7 = "It’s estimated that, because of the COVID-19\n" +
                "   pandemic and subsequent global recession,\n" +
                "   poverty rates will increase for the first\n" +
                "   time since 1990.\n";
        fact8 = "Approximately 297,000 children under five die\n" +
                "   every year from diarrhoeal diseases due to\n" +
                "   poor sanitation, poor hygiene, or unsafe\n" +
                "   drinking water.\n";
        fact9 = "The entire health budget of Ethiopia, a country\n" +
                "   of 105 million people, is equivalent to just 1%\n" +
                "   of the fortune of the world’s richest man,\n" +
                "   Amazon CEO Jeff Bezos.\n";
        fact10 = "Children are twice as likely as adults\n" +
                "   to live in extreme poverty.\n";
        // set the text for the facts
        handbook.setFact("#1", fact1);
        handbook.setFact("#2", fact2);
        handbook.setFact("#3", fact3);
        handbook.setFact("#4", fact4);
        handbook.setFact("#5", fact5);
        handbook.setFact("#6", fact6);
        handbook.setFact("#7", fact7);
        handbook.setFact("#8", fact8);
        handbook.setFact("#9", fact9);
        handbook.setFact("#10", fact10);
    }

    public String getJohnStartMessage() {
        return "\nHello, Bobby. My name is " + john.getName() + "\n" +
                "Are you ready to help fight extreme poverty?\n" +

                "\nYour mission is to collect donations from people at the park. " +
                "Use this handbook as your guide on what to say\n" +

                "\nYOU RECIEVED A HANDBOOK FROM JOHN\n" +

                "\nUse the handbook wisely. People will decrease their donations " +
                        "if they detect you are stating incorrect facts\n" +

                "\nYou can try to increase their donations, by either using " +
                        "charm or reason. Each person has their own personality, " +
                        "so use their description to find the right approach.\n" +

                "\nTalk to every person in the park, and return to me " +
                        "when you're done. Good luck.";
    }

    private void createInventory() {
        inventory = new Inventory(); // new instance of Inventory
    }

    private void createJohn() {
        john = new John(); // new instance of Player
    }

    private void createPlayer() {
        bobby = new Player(); // new instance of Player
    }

    private void createRooms() {
        // room variable names
        Room building, north, east, south, west, start;

        // descriptions for the rooms
        building = new Room("in the UN headquarters");
        north = new Room("in the north of the park");
        east = new Room("in the east of the park");
        south = new Room("in the south of the park");
        west = new Room("in the west of the park");

        // set the room the player starts in
        currentRoom = building;

        // building exit
        building.setExit("north", north);
        // north exit options
        north.setExit("building", building);
        north.setExit("east", east);
        north.setExit("south", south);
        north.setExit("west", west);
        // east exit options
        east.setExit("north", north);
        // south exit options
        south.setExit("north", north);
        // west exit options
        west.setExit("north", north);

        // Create persons for the rooms
        Person hans, lene, mathias, mia, niels, kurt, gurli, hanne, johnny, lolita;

        // create the options for Lolita
        String lolitaQuestion = "\n" +
                "1: 2 times\n" +
                "2: 3 times\n" +
                "3: 4 times\n";

        // new instance of person, Lolita
        lolita = new NPC(
                "Lolita",
                "Lolita is laying in the grass in the park, enjoying the sun.",
                "Female",
                lolitaQuestion,
                1,
                1,
                new Coin(200)
        );

        // set the dialog for Lolita
        lolita.setDialog(new String[]{
                "Hi! My name is " + lolita.getName() + ".",
                "Oh no! That is really unfair.",
                "Here you go"
        });

        // set the players dialog for Lolita
        String[] lolitaDialog = new String[]{
                "Hey. My name is Bobby.",
                "Children are ___ as likely as adults to live in extreme poverty",
                "Thank you. We will do our best to make some change."
        };
        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(lolita, lolitaDialog);

        // create the options for Johnny
        String johnnyQuestion = "\n" +
                "1: 0.5 %\n" +
                "2: 1 %\n" +
                "3: 5 %\n";

        // new instance of person, Johnny
        johnny = new NPC(
                "Johnny",
                "Johnny is walking past with a group of friends.",
                "Male",
                johnnyQuestion,
                2,
                2,
                new Coin(200)
        );

        // set the dialog for Johnny
        johnny.setDialog(new String[]{
                "Hi! My name is " + johnny.getName() + ".",
                "Seriously?! It's absurd that one man could have all that.",
                "We must all work together. Especially if people like Bezos does not. Here you go!"
        });

        // set the players dialog for Johnny
        String[] johnnyDialog = new String[]{
                "Hey. My name is Bobby.",
                "The entire health budget of Ethiopia a country of 105 million people " +
                "is equivalent to ___ of the fortune of the world’s richest man, Amazon CEO Jeff Bezos.",
                "You are absolutely right and thank you very much."
        };
        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(johnny, johnnyDialog);

        // create the options for Hanne
        String hanneQuestion = "\n" +
                "1: 115 thousand\n" +
                "2: 186 thousand\n" +
                "3: 297 thousand\n";

        // new instance of person, Hanne
        hanne = new NPC(
                "Hanne",
                "Hanne is walking slowly through the park while drinking a coffee",
                "Female",
                hanneQuestion,
                3,
                1,
                new Coin(200)
        );

        // set the dialog for Hanne
        hanne.setDialog(new String[]{
                "Hey. My name is " + hanne.getName() + ".",
                "Really! That's horrible.",
                "I might be a student, but in this case it is definitely worth contributing."
        });

        // set the players dialog for Hanne
        String[] hanneDialog = new String[]{
                "Hello there. My name is Bobby.",
                "Every single year ___ children under five die from diseases due to poor " +
                "sanitation, poor hygiene, or unsafe drinking water.",
                "Thank you so much!"
        };
        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(hanne, hanneDialog);

        // create the options for Gurli
        String gurliQuestion = "\n" +
                "1: 1990\n" +
                "2: 1995\n" +
                "3: 2000\n";

        // new instance of person, Gurli
        gurli = new NPC(
                "Gurli",
                "Gurli is an elderly lady. She is feeding the birds.",
                "Female",
                gurliQuestion,
                1,
                1,
                new Coin(200)
        );

        // set the dialog for Gurli
        gurli.setDialog(new String[]{
                "Nice to meet you young man. My name is " + gurli.getName() + ".",
                "Oh! What a shame that the progress was hindered by the pandemic.",
                "Take this young man. Sounds like you need it more than i do."
        });

        // set the players dialog for Gurli
        String[] gurliDialog = new String[]{
                "Hello there. My name is Bobby Hood.",
                "We have been doing well in reducing the amount of people living in poverty. " +
                "But were you aware that, because of the covid pandemic " +
                "poverty rates will increase for the first time since ___?",
                "Thank you"
        };
        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(gurli, gurliDialog);

        // create the options for Kurt
        String kurtQuestion = "\n" +
                "1: 700 million\n" +
                "2: 1.6 billion\n" +
                "3: 2.2 billion\n";

        // new instance of person, Kurt
        kurt = new NPC(
                "Kurt",
                "Kurt is a young man. He is listening to music and reading a book in the park.",
                "Male",
                kurtQuestion,
                2,
                2,
                new Coin(200)
        );

        // set the dialog for Kurt
        kurt.setDialog(new String[]{
                "Hello Bobby nice to meet you. My name is " + kurt.getName() + ".",
                "Wow. I would'nt know how to survive without that. Must be tough!",
                "Here you go. I am sure you will put it to good use."
        });

        // set the players dialog for Kurt
        String[] kurtDialog = new String[]{
                "Hello. My name is Bobby.",
                "Have you ever considered that, ___ people lives without electricity",
                "Thank you"
        };
        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(kurt, kurtDialog);

        // create the options for Niels
        String nielsQuestion = "\n" +
                "1: 10 thousand\n" +
                "2: 14 thousand\n" +
                "3: 22 thousand\n";

        // new instance of person, Mia
        niels = new NPC(
                "Niels",
                "Niels is standing still. Looking across the small pond.",
                "Male",
                nielsQuestion,
                3,
                2,
                new Coin(200)
        );

        // set the dialog for Niels
        niels.setDialog(new String[]{
                "Eey, what up my man Bobby! My name is " + niels.getName() + ".",
                "Daaaamn! We should definitely do something about that.",
                "Here's all i got. I hope you guys can make a difference."
        });

        // set the players dialog for Mia
        String[] nielsDialog = new String[]{
                "Hello. My name is Bobby.",
                "Did you know that almost, ___ thousand children die every day " +
                "due to living in poverty?",
                "Thank you"
        };

        // add this dialog to the players dialog list.
        // the dialog list is a HashMap that accepts a
        // person and the dialog for that person
        bobby.setDialog(niels, nielsDialog);


        // create the options for Mia
        String miaQuestion = "\n" +
                "1: 60 million\n" +
                "2: 160 million\n" +
                "3: 260 million\n";

        // new instance of person, Mia
        mia = new NPC(
                "Mia",
                "Mia is a middle aged woman, walking her dog in the park.",
                "Female",
                miaQuestion,
                2,
                1,
                new Coin(200)
        );

        // set the dialog for Mia
        mia.setDialog(new String[]{
                "Nice to meet you Bobby. My name is " + mia.getName() + ".",
                "No way. That's a lot of children.",
                "Here's what I can spare for your cause."
        });

        // set the players dialog for Mia
        String[] miaDialog = new String[]{
                "Hello. My name is Bobby.",
                "According to UN, ___ million children are at risk of continuing " +
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
        hans = new NPC(
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
        lene = new NPC(
                "Lene",
                "Lene is a very attractive business woman. She's walking while talking on the phone",
                "Female",
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
        mathias = new NPC(
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
        north.setPersons("Lolita", lolita);
        north.setPersons("Lene", lene);
        north.setPersons("Niels", niels);
        west.setPersons("Mathias", mathias);
        west.setPersons("Hanne", hanne);
        east.setPersons("Hans", hans);
        east.setPersons("Gurli", gurli);
        south.setPersons("Mia", mia);
        south.setPersons("Kurt", kurt);
        south.setPersons("Johnny", johnny);

        // set currentPerson to null,
        // because no persons has been engaged
        // at the start of a new game
        currentPerson = null;
        rooms.add(building);
        rooms.add(north);
        rooms.add(west);
        rooms.add(east);
        rooms.add(south);

        // create and set the dialog for John
        john.setDialog(new String[] {
                "Hello, Bobby. My name is " + john.getName() + "\n" +
                "Are you ready to help fight extreme poverty?\n",

                "Your mission is to collect donations from people at the park. " +
                "Use this handbook as your guide on what to say\n",

                "YOU RECIEVED A HANDBOOK FROM JOHN",

                "Use the handbook wisely. People will decrease their donations " +
                "if they detect you are stating incorrect facts\n",

                "You can try to increase their donations, by either using " +
                "charm or reason. Remember, each person has their own personality.",

                "Talk to every person in the park, and return to me " +
                "when you're done. Good luck.",

                "You're not done yet. You've talked to " + personsCompleted + " out of " + getPersonCount() + "",

                "Congratulations! You completed your mission."
        });
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

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean describe(Command command) {
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
        if (engagedPerson == null || engagedPerson instanceof John) {
            return false; // return false if the person doesn't exist
        } else {
            currentPerson = (NPC) engagedPerson; // Set currentPerson to the person we want to talk to
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
         Person personToTalkTo = currentRoom.getPerson(person);

         // check if the person exists
         if (personToTalkTo == null) {
            return false; // return false if the person doesn't exist
         } else if (personToTalkTo instanceof John) {
             john = (John) personToTalkTo;
             return true;
         } else {
            currentPerson = (NPC) personToTalkTo; // Set currentPerson to the person we want to talk to
            return true;
         }
    }

    public String johnsProgress() {
        return "You're not done yet. You've talked to " + personsCompleted + " out of " + getPersonCount() + "";
    }

    public String getJohnDialog(int index) {
        return john.getDialog(index);
    }

    public void setJohnsIndex(int index) {
        johnIndex = index;
    }

    public int getJohnsIndex() {
        return johnIndex;
    }

    public void startDialog(Command command) {
        // check if the command value is empty
        if (!command.hasCommandValue()) {
            return;
        }

        if (command.getCommandValue().equals("john")) {
            if (personsCompleted < getPersonCount()) {
                System.out.println("You're not done yet. You've talked to " + personsCompleted + " out of " + getPersonCount() + "");
            } else if (personsCompleted == getPersonCount()) {
                System.out.println(john.getDialog(2));
                System.out.println("You collected " + inventory.getCoins() + " coins.\n" +
                        "This means you helped feed a family of four for " +
                        "2 weeks.\n\n" +
                        "Thanks for your help!");
                isGameCompleted = true;
            } else {
                System.out.println("error");
            }
        } else {
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
                                    s.append(i + 1).append(", ");
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
                        personsCompleted++;
                        secondStep = false;
                        break; // escape the dialog;
                    } catch (InputMismatchException e) {
                        System.out.println("Please type 1 or 2");
                    }
                }
            } else {
                System.out.println(currentPerson.getRejected()); // the person's message if the person has already been engaged
            }
        }

    }

    public boolean quit(Command command) {
        return !command.hasCommandValue();
    }

    public String getRoomDescription() {
        return currentRoom.getLongDescription();
    }

    public String getPersonDescription() {
        return currentPerson.getDescription();
    }

    public List<String> getCommandDescriptions() {
        return commands.getCommandWords();
    }

    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getCommand(word1), word2);
    }

    public int getPersonCount() {
        int count = 0;
        for (Room room: rooms) {
            count += room.getPersonsList().size();
        }
        return count - 1;
    }

    public boolean isCompleted() {
        return isGameCompleted;
    }

    public void setPersonsCompleted() {
        personsCompleted++;
    }

    public int getPersonsCompleted() {
        return personsCompleted;
    }

    public Inventory returnInventory() {
        return inventory;
    }

    public Player getBobby() { return bobby; }

    public void setFirstVisit(boolean firstVisit) {
        this.firstVisit = firstVisit;
    }
}