package BobbyHood.GUI.Controllers;

import BobbyHood.GUI.BobbyGUI;
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

// reference:
// https://gist.github.com/Da9el00/421d6f02d52093ac07a9e65b99241bf8

public class CharacterController extends GameController {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty shiftPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(shiftPressed);

    private int movementVariable = 3;

    private final Image BOBBY_RIGHT = new Image("file:GUI/images/bobby_right.png");
    private final Image BOBBY_LEFT = new Image("file:GUI/images/bobby.png");

    @FXML
    private ImageView bobby;
    @FXML
    private AnchorPane scene;
    @FXML
    Text inventoryText;
    @FXML
    Rectangle door;
    @FXML
    Pane pane = new Pane();

    public void makeMovable(ImageView bobby, AnchorPane scene, Rectangle door, String direction) {
        this.bobby = bobby;
        this.scene = scene;

        movementSetup(door, direction);

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

    void movementSetup(Rectangle door, String direction) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:     wPressed.set(true); break;
                case S:     sPressed.set(true); break;
                case A:     aPressed.set(true); break;
                case D:     dPressed.set(true); break;
                case SHIFT: shiftPressed.set(true); break;
            }
            try {
                checkDoor(door, direction);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W:     wPressed.set(false); break;
                case S:     sPressed.set(false); break;
                case A:     aPressed.set(false); break;
                case D:     dPressed.set(false); break;
                case SHIFT: shiftPressed.set(false); movementVariable = 3; break;
            }
            try {
                checkDoor(door, direction);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void checkDoor(Rectangle rect, String direction) throws IOException {
        if (bobby.getBoundsInParent().intersects(rect.getBoundsInParent())) {
            System.out.println("collision detected");
            switch (direction) {
                case "inside": goInside(); break;
                case "east": goEast(); break;
            }
        }
    }

    public void goEast() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Outside.fxml"));
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        Stage stage = BobbyGUI.getStage();
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
    }

    public void goInside() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Building.fxml"));
        Scene sceneSwitch = new Scene(fxmlLoader.load());
        Stage stage = BobbyGUI.getStage();
        stage.setScene(sceneSwitch);
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(BobbyGUI.getGame());
    }
}