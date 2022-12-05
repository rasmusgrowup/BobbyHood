package BobbyHood.GUI;
import BobbyHood.GUI.Controllers.GameController;
import BobbyHood.Game;
import BobbyHood.Room;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class BobbyGUI extends Application {
    private static Stage primaryStage; // ** Declare static Stage **
    public static Game game;
    private Room building;


    private void setStage(Stage stage) {
        BobbyGUI.primaryStage = stage;
    }

    private void setGame() {
        BobbyGUI.game = new Game();
    }

    public static Stage getStage() {
        return BobbyGUI.primaryStage;
    }

    public static Game getGame() {
        return BobbyGUI.game;
    }
    public static void main(String[] args) {
        launch(args);
    }

    MediaPlayer mediaPlayer;
    @Override
    public void start(Stage primaryStage) throws IOException {
        setStage(primaryStage);
        setGame();
        FXMLLoader fxmlLoader = new FXMLLoader(BobbyGUI.class.getResource("fxml/Start.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Media media = new Media(new File("GUI/images/TitleTheme.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);


        GameController gameController = fxmlLoader.getController();
        gameController.persistGame(game);
        scene.getRoot().requestFocus();
        primaryStage.setTitle("BobbyHood");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setHeight(800);
        primaryStage.setWidth(1200);
        primaryStage.show();
    }
}
