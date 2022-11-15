package BobbyHood;

public class Person {
    // attributes
    private String name;
    private int age;
    private String description;
    private boolean engaged;
    private String response;

    private String question;
    private int correctAnswerIndex;

    private Item item;

    // Just an array for efficiency
    private String[] dialog;

    // constructor for person object
    public Person(String name, String response) {
        this.name = name;
        engaged = false;
        this.response = response;
    }

    public Person(String name, String response, String question, int i, Item item) {
        this.name = name;
        engaged = false;
        this.response = response;
        this.question = question;
        correctAnswerIndex = i;
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
        return getName() + " says: " + dialog[index];
    }

    public Item getItem() {
        return item;
    }

    public int getValue() {
        return item.getAmount();
    }

    public String getQuestion() {
        return question;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
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
    public String getPersonDescription() {
        return description;
    }

    // method for setting which response the npc has to use wether they have been engaged or not
    public void setEngaged(boolean b) {
        engaged = b; //set the value of engaged
    }

    public boolean getEngaged() {
        return engaged;
    }
    // method to retrieve response for the npc
    public String getResponse() {
        return response; // the response for each npc when the npc-objects are created
    }

    public String getRejected() {
        return "Hey Bobby, we already talked"; //add a standard message to all npc's that bobby has talked to
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        Person npc1 = new Person("Hans", "Hi, my name is Hans, 68 years old and I'm retired");
        npc1.setDialog(new String[]{
                "This is sentence 1",
                "This is sentence 2",
                "This is the last sentence"
        });
        //npc1.setEngaged();
        npc1.getResponse();
        System.out.println(npc1.getDialog(1) + "\n" + npc1.getDialog(1) + "\n" + npc1.getDialog(2));
    }
}