package BobbyHood;

import javafx.scene.image.ImageView;

import java.util.Objects;

public class Person {
    // attributes
    private String name;
    private String description;
    private String[] dialog; // Just an array for efficiency
    private ImageView image;
    private boolean engaged;


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

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getImage() {
        return image;
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

    public void setEngaged(boolean b) {
        engaged = b; //set the value of engaged
    }

    public boolean getEngaged() {
        return engaged;
    }

    public String getRejected() {
        return "Hey Bobby, we already talked"; //add a standard message to all npc's that bobby has talked to
    }
}