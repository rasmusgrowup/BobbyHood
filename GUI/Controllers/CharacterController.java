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

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

// reference:
// https://gist.github.com/Da9el00/421d6f02d52093ac07a9e65b99241bf8

public class CharacterController {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty shiftPressed = new SimpleBooleanProperty();
    private boolean paused, success;
    private boolean isDialogActive;
    private boolean isHandbookOpen;
    private int dIndex = 0;
    private int charmIndex = 0;
    private int userInput = 0;
    private int johnIndex;
    private boolean dialogSwitch = true;
    private boolean questionIsActive;
    public boolean isControlsPressed;
    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(shiftPressed);

    private int movementVariable = 3;
    private int startheight, width, height;
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
            if (!paused && !aBoolean) {
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if (wPressed.get() && bobby.getLayoutY() > startheight) {
                bobby.setLayoutY(bobby.getLayoutY() - movementVariable);
            }

            if (sPressed.get() && (bobby.getLayoutY() + bobby.getBoundsInLocal().getHeight() + 35 < height)) {
                bobby.setLayoutY(bobby.getLayoutY() + movementVariable);
            }

            if (aPressed.get() && bobby.getLayoutX() > 0) {
                bobby.setLayoutX(bobby.getLayoutX() - movementVariable);
                bobby.setImage(BOBBY_LEFT);
            }

            if (dPressed.get() && (bobby.getLayoutX() + bobby.getBoundsInLocal().getWidth() + 5) < width) {
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
                case Z -> {
                    userInput = 1;
                    checkPerson();
                }
                case X -> {
                    userInput = 2;
                    checkPerson();
                }
                case C -> {
                    userInput = 3;
                    checkPerson();
                }
                case W -> {
                    wPressed.set(true);
                    fadeControls("#wButton");
                }
                case S -> {
                    sPressed.set(true);
                    fadeControls("#sButton");
                }
                case A -> {
                    aPressed.set(true);
                    fadeControls("#aButton");
                }
                case D -> {
                    dPressed.set(true);
                    fadeControls("#dButton");
                }
                case P -> {
                    setPaused();
                    fadeControls("#pButton");
                }
                case H -> {
                    if (!isDialogActive) {
                        displayHandbook();
                        fadeControls("#hButton");
                    }
                }
                case ENTER -> {
                    checkPerson();
                    fadeControls("#enterButton");
                }
                case SHIFT -> shiftPressed.set(true);
            }
            try {
                checkDoor(doors);
                //checkPerson();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W -> {
                    wPressed.set(false);
                    displayControls("#wButton");
                }
                case S -> {
                    sPressed.set(false);
                    displayControls("#sButton");
                }
                case A -> {
                    aPressed.set(false);
                    displayControls("#aButton");
                }
                case D -> {
                    dPressed.set(false);
                    displayControls("#dButton");
                }
                case SHIFT -> {
                    shiftPressed.set(false);
                    movementVariable = 3;
                }
                case ENTER -> displayControls("#enterButton");
            }
            try {
                checkDoor(doors);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setPaused() {
        paused = !paused;
        //System.out.println(paused);
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
                dialog(person);
                wPressed.set(false);
                sPressed.set(false);
                aPressed.set(false);
                dPressed.set(false);
                shiftPressed.set(false);
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

    public void displayHandbook() {
        isHandbookOpen = !isHandbookOpen;
        setPaused();
        Text text = (Text) scene.lookup("#handbook");
        Pane pane = (Pane) scene.lookup("#handbookPane");
        if (isHandbookOpen) {
            pane.setOpacity(1.0);
            text.setText(BobbyGUI.getGame().getHandbook());
        } else {
            pane.setOpacity(0.0);
        }
    }


    public void fadeControls(String id) {
        ImageView controls = (ImageView) scene.lookup(id);
        controls.setOpacity(0.5);
    }

    public void displayControls(String id) {
        ImageView controls = (ImageView) scene.lookup(id);
        controls.setOpacity(1);
    }
    public void setDialogSwitch() {
        dialogSwitch = !dialogSwitch;
    }

    public void johnDialog() {
        //paused = true;
        johnIndex = BobbyGUI.getGame().getJohnsIndex();
        System.out.println("local variable:" + johnIndex);
        System.out.println("game variable:" + BobbyGUI.getGame().getJohnsIndex());
        Text text = (Text) scene.lookup("#dialogText");
        text.setStyle("-fx-font: 18 monospace;");
        Pane pane = (Pane) scene.lookup("#dialogPane");
        text.setText(BobbyGUI.getGame().getJohnStartMessage());
        pane.setOpacity(1.0);
        if (BobbyGUI.getGame().getJohnsIndex() <= 5) {
            text.setText(BobbyGUI.getGame().getJohnDialog(BobbyGUI.getGame().getJohnsIndex()));
            BobbyGUI.getGame().setJohnsIndex(++johnIndex);
        } else if (BobbyGUI.getGame().getJohnsIndex() == 6) {
            pane.setOpacity(0.0);
            BobbyGUI.getGame().setJohnsIndex(++johnIndex);
            paused = false;
        } else if (BobbyGUI.getGame().getJohnsIndex() == 7) {
            text.setText(BobbyGUI.getGame().johnsProgress());
            BobbyGUI.getGame().setJohnsIndex(++johnIndex);
        } else {
            pane.setOpacity(0.0);
            BobbyGUI.getGame().setJohnsIndex(7);
            johnIndex = 7;
            paused = false;
        }
    }

    public void dialog(Person person) {
        paused = true;
        isDialogActive = true;
        currentRoom = BobbyGUI.getGame().getCurrentRoom();
        Text text = (Text) scene.lookup("#dialogText");
        Text inventoryText = (Text) scene.lookup("#inventoryText");
        text.setStyle("-fx-font: 18 monospace;");
        Pane pane = (Pane) scene.lookup("#dialogPane");
        pane.setOpacity(1.0);
        if (person instanceof John) {
            johnDialog();
        } else {
            NPC npc = (NPC) person;
            if (npc.getEngaged()) {
                if (dIndex < 1) {
                    text.setText(npc.getRejected());
                    dIndex++;
                } else {
                    dIndex = 0;
                    paused = false;
                    pane.setOpacity(0);
                    isDialogActive = false;
                }
            } else {
                int amount = npc.getItem().getAmount();
                switch (dIndex) {
                    case 0 -> {
                        if (dialogSwitch) {
                            text.setText(BobbyGUI.getGame().getBobby().getDialog(npc, dIndex));
                            setDialogSwitch();
                        } else {
                            text.setText(npc.getDialog(dIndex));
                            setDialogSwitch();
                            dIndex++;
                        }
                    }
                    case 1 -> {
                        boolean wasAnswerCorrect = false;
                        if (dialogSwitch) {
                            text.setText(BobbyGUI.getGame().getBobby().getDialog(npc, dIndex) + "\n" + npc.getQuestion());
                            setDialogSwitch();
                            questionIsActive = true;
                        } else if (questionIsActive) {
                            int correctAnswer = npc.getCorrectAnswerIndex();
                            success = correctAnswer == userInput;
                            text.setText(success ? "Correct answer!" : "Wrong answer. Try again later.");
                            if (!success) { npc.getItem().setAmount(amount / 2); }
                            questionIsActive = false;
                            userInput = 0;
                        } else {
                            if (success) {
                                text.setText(npc.getDialog(dIndex));
                                setDialogSwitch();
                                questionIsActive = true;
                                dIndex++;
                            } else {
                                dIndex = 0;
                                setDialogSwitch();
                                setPaused();
                                pane.setOpacity(0.0);
                            } success = false;
                        }
                    }
                    case 2 -> {
                        switch (charmIndex) {
                            case 0 -> {
                                text.setText("Use charm (Z) or reason (X) to persuade " + npc.getName() + " into increasing " + npc.printGender() + " donation.");
                                charmIndex++;
                            }
                            case 1 -> {
                                int correctAnswer = npc.getCorrectTypeIndex();
                                success = correctAnswer == userInput;
                                text.setText(success ?
                                        "You used " + npc.printType(userInput) + ", and it worked!" :
                                        "" + npc.getName() + " didn't respond well to '" + npc.printType(userInput) + "' and will not increase " + npc.printGender() + " donations."
                                );
                                if (success) {
                                    npc.getItem().setAmount(amount * 2);
                                }
                                charmIndex++;
                            }
                            case 2 -> {
                                text.setText(npc.getDialog(dIndex));
                                charmIndex++;
                            }
                            case 3 -> {
                                BobbyGUI.getGame().returnInventory().addItem(npc.getItem());
                                text.setText(npc.getValue() + " COINS WAS ADDED TO YOUR INVENTORY");
                                inventoryText.setText(BobbyGUI.getGame().getInventory());
                                charmIndex++;
                            }
                            case 4 -> {
                                text.setText(BobbyGUI.getGame().getBobby().getDialog(npc, dIndex));
                                charmIndex = 0;
                                dIndex++;
                            }
                        }
                    }
                    case 3 -> {
                        npc.setEngaged(true);
                        BobbyGUI.getGame().setPersonsCompleted();
                        dIndex = 0;
                        paused = false;
                        pane.setOpacity(0);
                        isDialogActive = false;
                        //BobbyGUI.getGame().returnInventory().addItem(npc.getItem());
                    }
                }
            }
        }
    }
    public void setBorderValues(int startheight,int width, int height) {
        this.startheight = startheight;
        this.width = width;
        this.height = height;
    }
}