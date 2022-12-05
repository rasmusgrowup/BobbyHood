package BobbyHood.GUI.Controllers;


import BobbyHood.GUI.Door;
import BobbyHood.Person;
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

public class SouthController extends GameController implements Initializable {

    private final CharacterController characterController = new CharacterController();
    private HashMap<String, Door> doors = new HashMap<>();
    private Door door = new Door();
    private HashMap<Person, ImageView> persons = new HashMap();
    private ArrayList<ImageView> images = new ArrayList<>();
    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby, mia, johnny, kurt;
    @FXML
    Text inventoryText;

    @FXML
    Rectangle doorRect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        images.add(mia);
        images.add(johnny);
        images.add(kurt);
        setPersonsForRoom(persons, images);
        door.setRect(doorRect);
        door.setFxmlPath("fxml/North.fxml");
        door.setDirection("north");
        characterController.makeMovable(bobby, scene, doors);
        door.getRect().setFill(Color.TRANSPARENT);
        doors.put("north", door);
        characterController.setPersons(persons);
        //System.out.println(game.currentRoom.getShortDescription());
    }
}
