package BobbyHood;

import java.util.Objects;

public class Person {
    // attributes
    private String name;
    private String description;
    private String[] dialog; // Just an array for efficiency

    // constructor for person object
    public Person(String name, String description) {
        this.name = name;
        this.description = description;
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


    public int getDialogLength() {
        return dialog.length;
    }

    // method to get the name of a person object
    public String getName() {
        return name;
    }

    // method for getting the description for each person object created
    public String getDescription() {
        return description;
    }
}