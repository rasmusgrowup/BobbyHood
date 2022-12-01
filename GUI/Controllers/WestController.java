package BobbyHood.GUI.Controllers;


import BobbyHood.GUI.Door;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class WestController extends GameController implements Initializable {

    private final CharacterController characterController = new CharacterController();
    private HashMap<String, Door> doors = new HashMap<>();
    private Door door = new Door();
    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby;
    @FXML
    Text inventoryText;

    @FXML
    Rectangle doorRect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        door.setRect(doorRect);
        door.setFxmlPath("fxml/North.fxml");
        door.setDirection("north");
        characterController.makeMovable(bobby, scene, doors);
        door.getRect().setFill(Color.TRANSPARENT);
        doors.put("north", door);
        //System.out.println(game.currentRoom.getShortDescription());
    }
}
