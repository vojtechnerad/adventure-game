package cz.vse.java.nerv01.adventura.main;


import cz.vse.java.nerv01.adventura.cli.CommandLineInterface;
import cz.vse.java.nerv01.adventura.core.Game;
import cz.vse.java.nerv01.adventura.core.IGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Třída Main je třída ve kterém se nachází statická metoda main,
 * která spouští tuto adventuru.
 * 
 * @author  Vojtěch Nerad
 * @version 0.00.0000 — 20yy-mm-dd
 */

public class Main extends Application {
    /**
     * Metoda, kterou se spouští adventura.
     * 
     * @param args Parametry příkazové řádky
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch();
        } else if (args[0].equals("text")) {
            IGame game = new Game();
            CommandLineInterface cli = new CommandLineInterface(game);
            cli.play();
        }
    }

    /**
     * Metoda spouštějící úvodní obrazovku hry (title screen),
     * ze které se následně spouští i hra.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/title_screen.fxml"));
        GridPane root = loader.load();
        TitleScreenController controller = loader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Úvodní nabídka");
        primaryStage.show();
    }
}
