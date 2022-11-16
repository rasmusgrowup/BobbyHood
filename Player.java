package BobbyHood;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;

public class Player {
    private String name;
    private HashMap<Person, String[]> dialog;

    public Player() {
        name = "Bobby";
        this.dialog = new HashMap<>();
    }

    public void setDialog(Person person, String[] dialog) {
        this.dialog.put(person, dialog);
    }

    public String getDialog(Person person, int i) {
        String[] d = dialog.get(person);
        return "Bobby: " + d[i];
    }
/*
    public static void main(String[] args) {
        Person hans = new Person("Hans", "Hi there, Bobby");
        Person lene = new Person("Lene", "Hi there, Bobby");
        hans.setDialog(new String[]{
                "Hi there, Bobby. My name is " + hans.getName() + ".",
                "Oh no, that sounds horrible.",
                "Sure. I’ll donate a 100 coins."
        });
        lene.setDialog(new String[]{
                "Hi. I'm " + lene.getName() + ". You're very handsome.",
                "Oh no, that sounds horrible.",
                "Sure. I’ll donate a 150 coins. And here's my personal phone number. Call me anytime."
        });

        Player player = new Player();
        String[] hansDialog = new String[]{
                "Hello. My name is Bobby.",
                "Did you know, that millions live in extreme poverty?",
                "Thank you"
        };
        String[] leneDialog = new String[]{
                "Hi there. My name is Bobby.",
                "Did you know, that millions live in extreme poverty?",
                "Thank you"
        };
        player.setDialog(hans, hansDialog);
        player.setDialog(lene, leneDialog);
        lene.getDialog(2);
        player.getDialog(lene, 2);
    }
    */
}
