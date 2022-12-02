package BobbyHood.GUI.Controllers;

import BobbyHood.*;
import BobbyHood.GUI.BobbyGUI;
import BobbyHood.GUI.Door;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

// reference:
// https://gist.github.com/Da9el00/421d6f02d52093ac07a9e65b99241bf8

public class CharacterController {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty shiftPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(shiftPressed);

    private int movementVariable = 3;
    private Room currentRoom, nextRoom;
    //private NPC currentPerson;

    private HashMap<Person, ImageView> persons = new HashMap();

    private final Image BOBBY_RIGHT = new Image("file:GUI/images/bobby_right.png");
    private final Image BOBBY_LEFT = new Image("file:GUI/images/bobby.png");

    @FXML
    private ImageView bobby;
    @FXML
    private AnchorPane scene;
    @FXML
    Text inventoryText, dialogText;

    public void makeMovable(ImageView bobby, AnchorPane scene, HashMap<String, Door> doors) {
        this.bobby = bobby;
        this.scene = scene;

        movementSetup(doors);

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if (wPressed.get()) {
                bobby.setLayoutY(bobby.getLayoutY() - movementVariable);
            }

            if (sPressed.get()) {
                bobby.setLayoutY(bobby.getLayoutY() + movementVariable);
            }

            if (aPressed.get()) {
                bobby.setLayoutX(bobby.getLayoutX() - movementVariable);
                bobby.setImage(BOBBY_LEFT);
            }

            if (dPressed.get()) {
                bobby.setLayoutX(bobby.getLayoutX() + movementVariable);
                bobby.setImage(BOBBY_RIGHT);
            }

            if (shiftPressed.get()) {
                movementVariable = 6;
            }
        }
    };

    void movementSetup(HashMap<String, Door> doors) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W -> wPressed.set(true);
                case S -> sPressed.set(true);
                case A -> aPressed.set(true);
                case D -> dPressed.set(true);
                case SHIFT -> shiftPressed.set(true);
            }
            try {
                checkDoor(doors);
                checkPerson();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W -> wPressed.set(false);
                case S -> sPressed.set(false);
                case A -> aPressed.set(false);
                case D -> dPressed.set(false);
                case SHIFT -> {
                    shiftPressed.set(false);
                    movementVariable = 3;
                }
            }
            try {
                checkDoor(doors);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void checkDoor(HashMap<String, Door> doors) throws IOException {
        for (HashMap.Entry<String, Door> set: doors.entrySet()) {
            Door door = set.getValue();
            if (bobby.getBoundsInParent().intersects(door.getRect().getBoundsInParent())) {
                String direction = door.getDirection();
                String fxmlPath = door.getFxmlPath();
                switchDoor(fxmlPath, direction);
            }
        }
    }

    public void checkPerson() {
        for (HashMap.Entry<Person, ImageView> set: persons.entrySet()) {
            Person person = set.getKey();
            if (bobby.getBoundsInParent().intersects(set.getValue().getBoundsInParent())) {
                if (person instanceof NPC) {
                    //BobbyGUI.getGame().setCurrentPerson((NPC) person);
                    //BobbyGUI.getGame().startDialog((NPC) person);
                    dialog(((NPC) person).getDialog(0));
                } else {
                    //BobbyGUI.getGame().johnDialog((John) person);
                    dialog(person.getDialog(1));
                }
            }
        }
    }

    public void setPersons(HashMap<Person, ImageView> persons) {
        this.persons = persons;
    }

    public void switchDoor(String fxmlPath, String direction) throws IOException {
        currentRoom = BobbyGUI.getGame().getCurrentRoom();
        nextRoom = currentRoom.getExit(direction);
        BobbyGUI.getGame().setCurrentRoom(nextRoom);
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getRoot().requestFocus();
        Stage stage = BobbyGUI.getStage();
        stage.setScene(scene);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
    }

    public void dialog(String dialogString) {
        currentRoom = BobbyGUI.getGame().getCurrentRoom();
        Text text = (Text) scene.lookup("#dialogText");
        Pane pane = (Pane) scene.lookup("#dialogPane");
        text.setText(dialogString);
        pane.setOpacity(1.0);
    }
}