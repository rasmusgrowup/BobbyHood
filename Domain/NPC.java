package BobbyHood.Domain;

public class NPC extends Person {
    private String gender;
    private boolean engaged;
    private String question;
    private int correctAnswerIndex;
    private int correctTypeIndex;
    private Item item;

    public NPC(String name, String description, String gender, String question, int i, int t, Item item) {
        super(name, description);
        this.gender = gender;
        engaged = false;
        this.question = question;
        correctAnswerIndex = i;
        correctTypeIndex = t;
        this.item = item;
    }

    // Set dialog method
    public void setDialog(String[] dialog) {
        super.setDialog(dialog);
    }

    public Item getItem() {
        return item;
    }

    public int getValue() {
        return item.getAmount();
    }

    public String getQuestion() {
        return question + "\nPress 1), 2) or 3) to answer";
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getCorrectTypeIndex() {
        return correctTypeIndex;
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
}
