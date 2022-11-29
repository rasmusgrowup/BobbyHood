package BobbyHood.GUI.Controllers;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;


// reference:
// https://gist.github.com/Da9el00/421d6f02d52093ac07a9e65b99241bf8

public class MasterController {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private int movementVariable = 3;

    private final Image BOBBY_RIGHT = new Image("file:GUI/images/bobby_right.png");
    private final Image BOBBY_LEFT = new Image("file:GUI/images/bobby.png");

    @FXML
    private ImageView bobby;

    @FXML
    private AnchorPane scene;

    public void makeMovable(ImageView bobby, AnchorPane scene) {
        this.bobby = bobby;
        this.scene = scene;

        movementSetup();

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
        }
    };

    private void movementSetup() {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                wPressed.set(true);
            }

            if (e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }

            if (e.getCode() == KeyCode.S) {
                sPressed.set(true);
            }

            if (e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W) {
                wPressed.set(false);
            }

            if (e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if (e.getCode() == KeyCode.S) {
                sPressed.set(false);
            }

            if (e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
        });
    }
}