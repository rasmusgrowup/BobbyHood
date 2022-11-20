package BobbyHood;

import java.util.Objects;

public class Person {
    // attributes
    private String name;
    private int age;
    private String gender;
    private String description;
    private boolean engaged;
    private String question;
    private int correctAnswerIndex;
    private int correctTypeIndex;
    private Item item;

    // Just an array for efficiency
    private String[] dialog;

    // constructor for person object
    public Person(String name, String description) {
        this.name = name;
        engaged = false;
        this.description = description;
    }

    public Person(String name, String gender, String description, String question, int i, int t, Item item) {
        this.name = name;
        this.gender = gender;
        engaged = false;
        this.description = description;
        this.question = question;
        correctAnswerIndex = i;
        correctTypeIndex = t;
        this.item = item;
    }

    public Person() {
        String[] dialog = new String[3];
    }

    // Set dialog method
    public void setDialog(String[] dialog) {
        this.dialog = dialog;
    }

    public String getDialog(int index) {
        return getName() + ": " + dialog[index];
    }

    public String[] getFullDialog() {
        return dialog;
    }

    public int getDialogLength() {
        return dialog.length;
    }

    public Item getItem() {
        return item;
    }

    public void setItemAmount() {
        item.getAmount();
    }

    public int getValue() {
        return item.getAmount();
    }

    public String getQuestion() {
        return question + "\n\033[3mFill in the blank by typing the correct number.\033[0m";
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getCorrectTypeIndex() {
        return correctTypeIndex;
    }

    public String getGender() {
        return gender;
    }

    public String printGender() {
        String genderString = "";
        if (gender.equals("Male")) {
            genderString = "his";
        } else if (gender.equals("Female")) {
            genderString = "her";
        } return genderString;
    }

    public String printType(int index) {
        String typeString = "";
        if (index == 1) {
            typeString = "charm";
        } else if (index == 2) {
            typeString = "reason";
        } return typeString;
    }

    // method to get the name of a person object
    public String getName() {
        return name;
    }

    // method to get the age of a person object
    public int getAge() {
        return age;
    }

    // method for getting the description for each person object created
    public String getDescription() {
        return description;
    }

    // method for setting which response the npc has to use wether they have been engaged or not
    public void setEngaged(boolean b) {
        engaged = b; //set the value of engaged
    }

    public boolean getEngaged() {
        return engaged;
    }

    public String getRejected() {
        return "Hey Bobby, we already talked"; //add a standard message to all npc's that bobby has talked to
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        Person npc1 = new Person("Hans", "Male", "Hi, my name is Hans, 68 years old and I'm retired", "question", 2, 2, new Coin(100));
        System.out.println(npc1.getItem().getAmount());
        npc1.getItem().setAmount(50);
        System.out.println(npc1.getItem().getAmount());
    }
}