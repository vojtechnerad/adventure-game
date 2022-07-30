package cz.vse.java.nerv01.adventura.main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class TitleScreenController extends GridPane {
    @FXML
    private void initialize() {

    }

    /**
     * Metoda spouštějící samotnou hru.
     */
    @FXML
    private void startGame() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/game_screen.fxml"));
        GameScreenController controller = loader.getController();

        Stage stage = new Stage();
        BorderPane root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Adventura");
        stage.show();
    }

    /**
     * Metoda zobrazující nápovědu.
     */
    @FXML
    private void showHelp() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            File file = new File("help.html");
            URL url = file.toURI().toURL();
            webEngine.load(url.toString());
            VBox root = new VBox();
            root.getChildren().addAll(webView);
            stage.setScene(new Scene(root, 1000, 500));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Metoda ukončující chod aplikace.
     */
    @FXML
    private void exitApplication() {
        Platform.exit();
    }
}
