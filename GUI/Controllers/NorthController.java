package BobbyHood.GUI.Controllers;

import BobbyHood.Domain.Door;
import BobbyHood.Domain.Person;
import BobbyHood.GUI.BobbyGUI;
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

public class NorthController extends GameController implements Initializable {

    private final CharacterController characterController = new CharacterController();
    private HashMap<String, Door> doors = new HashMap<>();
    private HashMap<Person, ImageView> persons = new HashMap();
    private ArrayList<ImageView> images = new ArrayList<>();
    private Door doorBuilding = new Door();
    private Door doorSouth = new Door();
    private Door doorWest = new Door();
    private Door doorEast = new Door();
    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby, lene, lolita, niels;
    @FXML
    Text inventoryText, dialogText;
    @FXML
    Rectangle doorBuildingRect, doorEastRect, doorSouthRect, doorWestRect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        images.add(lene);
        images.add(lolita);
        images.add(niels);
        setPersonsForRoom(persons, images);
        doorBuilding.setRect(doorBuildingRect);
        doorEast.setRect(doorEastRect);
        doorSouth.setRect(doorSouthRect);
        doorWest.setRect(doorWestRect);
        doorBuilding.setFxmlPath("fxml/Building.fxml");
        doorEast.setFxmlPath("fxml/East.fxml");
        doorSouth.setFxmlPath("fxml/South.fxml");
        doorWest.setFxmlPath("fxml/West.fxml");
        doorBuilding.setDirection("building");
        doorEast.setDirection("east");
        doorSouth.setDirection("south");
        doorWest.setDirection("west");
        characterController.makeMovable(bobby, scene, doors);
        doorBuilding.getRect().setFill(Color.TRANSPARENT);
        doorEast.getRect().setFill(Color.TRANSPARENT);
        doorSouth.getRect().setFill(Color.TRANSPARENT);
        doorWest.getRect().setFill(Color.TRANSPARENT);
        doors.put("building", doorBuilding);
        doors.put("east", doorEast);
        doors.put("south", doorSouth);
        doors.put("west", doorWest);
        characterController.setPersons(persons);
        BobbyGUI.getGame().setFirstVisit(false);
    }
}
