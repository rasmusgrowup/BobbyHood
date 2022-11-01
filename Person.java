package worldOfZuul;

public class Person {
    // attributes
    private String name;
    private int age;
    private String description;
    private boolean engaged;
    private String response;

    // contructor for person object
    public Person(String name, String response) {
        this.name = name;
        engaged = false;
        this.response = response;
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
    public void setEngaged() {
        engaged = !engaged; //reverse the value of engaged
    }

    // method to retrieve response for the npc
    public void getResponse() {
        if(!engaged) {
            System.out.println(response); // the response for each npc when the npc-objects are created
        } else {
            System.out.println("Hey Bobby, we already talked"); //add a standard message to all npc's that bobby has talked to
        }
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        Person npc1 = new Person("Hans", "Hi, my name is Hans, 68 years old and I'm retired");
        //npc1.setEngaged();
        npc1.getResponse();
    }
}