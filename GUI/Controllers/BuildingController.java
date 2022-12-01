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
import java.util.HashMap;
import java.util.ResourceBundle;

public class BuildingController extends GameController implements Initializable {

    private final CharacterController characterController = new CharacterController();
    private HashMap<String, Door> doors = new HashMap<>();
    private Door door;

    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby, john;
    @FXML
    Text inventoryText;

    @FXML
    Rectangle doorRect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.door = new Door();
        door.setRect(doorRect);
        door.setFxmlPath("fxml/North.fxml");
        door.setDirection("north");
        doors.put("north", door);
        characterController.makeMovable(bobby, scene, doors);
        doorRect.setFill(Color.TRANSPARENT);
        //System.out.println(game.currentRoom.getShortDescription());
    }
}
