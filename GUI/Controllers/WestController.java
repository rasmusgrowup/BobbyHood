package BobbyHood.GUI.Controllers;


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
    private HashMap<String, Rectangle> doors = new HashMap<>();
    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby;
    @FXML
    Text inventoryText;

    @FXML
    Rectangle door = new Rectangle();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        characterController.makeMovable(bobby, scene, doors);
        door.setFill(Color.TRANSPARENT);
        doors.put("north", door);
        //System.out.println(game.currentRoom.getShortDescription());
    }
}
