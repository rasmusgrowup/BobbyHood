package BobbyHood.Domain;
import javafx.scene.shape.Rectangle;

public class Door {
    private Rectangle door;
    private String direction;
    private String fxmlPath;

    public Door() {}

    public Door(Rectangle door, String direction, String fxmlPath) {
        this.door = door;
        this.direction = direction;
        this.fxmlPath = fxmlPath;
    }

    public Rectangle getRect() {
        return door;
    }

    public String getDirection() {
        return direction;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public void setRect(Rectangle rect) {
        this.door = rect;
    }
}
