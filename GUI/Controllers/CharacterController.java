package BobbyHood.GUI.Controllers;

import BobbyHood.Command;
import BobbyHood.GUI.BobbyGUI;
import BobbyHood.Room;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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

    private final Image BOBBY_RIGHT = new Image("file:GUI/images/bobby_right.png");
    private final Image BOBBY_LEFT = new Image("file:GUI/images/bobby.png");

    @FXML
    private ImageView bobby;
    @FXML
    private AnchorPane scene;

    public void makeMovable(ImageView bobby, AnchorPane scene, HashMap<String, Rectangle> doors) {
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

    void movementSetup(HashMap<String, Rectangle> doors) {
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

    public void checkDoor(HashMap<String, Rectangle> doors) throws IOException {
        for (HashMap.Entry<String, Rectangle> set: doors.entrySet()) {
            Rectangle door = set.getValue();
            if (bobby.getBoundsInParent().intersects(door.getBoundsInParent())) {
                String direction = set.getKey();
                switch (direction) {
                    case "building": goInside(); break;
                    case "north": goNorth(); break;
                    case "east": goEast(); break;
                    case "south": goSouth(); break;
                    case "west": goWest(); break;
                }
            }
        }
    }

    public void goNorth() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/North.fxml"));
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        sceneSwitch.getRoot().requestFocus();
        Stage stage = BobbyGUI.getStage();
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
        currentRoom = BobbyGUI.getGame().getCurrentRoom();
        nextRoom = currentRoom.getExit("north");
        BobbyGUI.getGame().setCurrentRoom(nextRoom);
        System.out.println(BobbyGUI.getGame().getCurrentRoom().getPersons());
    }

    public void goSouth() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/South.fxml"));
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        sceneSwitch.getRoot().requestFocus();
        Stage stage = BobbyGUI.getStage();
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
        currentRoom = BobbyGUI.getGame().getCurrentRoom();
        nextRoom = currentRoom.getExit("south");
        BobbyGUI.getGame().setCurrentRoom(nextRoom);
        System.out.println(BobbyGUI.getGame().getCurrentRoom().getPersons());
    }

    public void goWest() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/West.fxml"));
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        sceneSwitch.getRoot().requestFocus();
        Stage stage = BobbyGUI.getStage();
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
        currentRoom = BobbyGUI.getGame().getCurrentRoom();
        nextRoom = currentRoom.getExit("west");
        BobbyGUI.getGame().setCurrentRoom(nextRoom);
        System.out.println(BobbyGUI.getGame().getCurrentRoom().getPersons());
    }

    public void goEast() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/East.fxml"));
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        sceneSwitch.getRoot().requestFocus();
        Stage stage = BobbyGUI.getStage();
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
        currentRoom = BobbyGUI.getGame().getCurrentRoom();
        nextRoom = currentRoom.getExit("east");
        BobbyGUI.getGame().setCurrentRoom(nextRoom);
        System.out.println(BobbyGUI.getGame().getCurrentRoom().getPersons());
    }

    public void goInside() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Building.fxml"));
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        sceneSwitch.getRoot().requestFocus();
        Stage stage = BobbyGUI.getStage();
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
        currentRoom = BobbyGUI.getGame().getCurrentRoom();
        nextRoom = currentRoom.getExit("building");
        BobbyGUI.getGame().setCurrentRoom(nextRoom);
        System.out.println(BobbyGUI.getGame().getCurrentRoom().getPersons());
    }
}