package BobbyHood.GUI.Controllers;


import BobbyHood.Coin;
import BobbyHood.GUI.BobbyGUI;
import BobbyHood.GUI.Door;
import BobbyHood.Game;
import BobbyHood.Person;
import BobbyHood.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class NorthController extends GameController implements Initializable {

    private final CharacterController characterController = new CharacterController();
    private HashMap<String, Door> doors = new HashMap<>();
    private Door doorBuilding = new Door();
    private Door doorSouth = new Door();
    private Door doorWest = new Door();
    private Door doorEast = new Door();
    @FXML
    private AnchorPane scene;
    @FXML
    ImageView bobby, person;
    @FXML
    Text inventoryText;
    @FXML
    Rectangle doorBuildingRect, doorEastRect, doorSouthRect, doorWestRect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doorBuilding.setRect(doorBuildingRect);
        doorEast.setRect(doorEastRect);
        doorSouth.setRect(doorSouthRect);
        doorWest.setRect(doorWestRect);
        doorBuilding.setFxmlPath("fxml/Building.fxml");
        doorEast.setFxmlPath("fxml/East.fxml");
        doorSouth.setFxmlPath("fxml/South.fxml");
        doorWest.setFxmlPath("fxml/West.fxml");
        doorBuilding.setDirection("north");
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
        //System.out.println(game.currentRoom.getShortDescription());
    }
}
