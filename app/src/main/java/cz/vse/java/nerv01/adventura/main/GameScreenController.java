package cz.vse.java.nerv01.adventura.main;

import cz.vse.java.nerv01.adventura.core.Area;
import cz.vse.java.nerv01.adventura.core.Game;
import cz.vse.java.nerv01.adventura.core.IGame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GameScreenController extends BorderPane implements Observer {
    @FXML
    Label currentBalance;
    IGame game;
    @FXML
    TextArea textOutput;
    @FXML
    ImageView playermodel1;
    @FXML
    ImageView playermodel2;
    private Map<String, Point2D> coordinates;

    @FXML
    Button buttonPay;
    @FXML
    Button buttonPutOnMask;
    @FXML
    Button buttonWithdrawMoney;
    @FXML
    Button buttonLoad;

    @FXML
    ListView listOfAdjacentAreas;
    @FXML
    ListView itemsInArea;
    @FXML
    ListView itemsInShoppingCart;
    @FXML
    ListView itemsInBackpack;
    @FXML
    ListView itemsInCar;


    GameScreenController thisController;
    boolean alreadyWithdrawn = false;

    /**
     * Metoda initialize slouží k přípravě potřebných věcí, která se spouští při
     * zapnutí nové hry z úvodní obrazovky.
     */
    @FXML
    public void initialize() {
        game = new Game(); //vytvoření nové instance hry

        //příprava výpisového okna
        textOutput.setEditable(false);
        textOutput.appendText(game.getPrologue() + "\n\n");

        //registrace nových observerů
        game.getGamePlan().register(this);
        game.getGamePlan().getShoppingCart().register(this);
        game.getGamePlan().getBackpack().register(this);
        game.getGamePlan().getCar().register(this);

        coordinates = createCoordinates(); //vytvoření souřadnic k lokacím

        //odemknutí tlačítek
        listOfAdjacentAreas.setDisable(false);
        itemsInArea.setDisable(false);
        buttonLoad.setDisable(false);
        buttonPay.setDisable(false);
        buttonPutOnMask.setDisable(false);
        buttonWithdrawMoney.setDisable(false);

        update(); //aktualizace všech inventářů a výpisu lokací
    }

    /**
     * Metoda zprostředkovávající zpracování příkazu logikou hry.
     *
     * @param commandToProcess předávaný příkaz
     */
    private void sendInput(String commandToProcess) {
        textOutput.clear();
        textOutput.appendText("Příkaz: " + commandToProcess + "\n\n");
        String processedCommand = game.processCommand(commandToProcess);
        textOutput.appendText(processedCommand + "\n\n");
    }

    /**
     * Metoda implementující pohyb po mapě ve hře, za pomocí klikání na položky
     * v ListView, kde položky jsou vypsané lokace, do kterých lze jít ze současné lokace.
     *
     * @param mouseEvent kliknutí na položku v ListView
     */
    public void selectedArea(MouseEvent mouseEvent) {
        Area selectedArea = (Area) listOfAdjacentAreas.getSelectionModel().getSelectedItem();
        sendInput("jdi " + selectedArea.getName());
    }

    /**
     * Metoda implementující vkládání předmětů do košíku, za pomocí klikání na položky
     * v ListView, kde položky jsou vypsané předměty, které se v současné lokaci nacházejí.
     * @param mouseEvent
     */
    public void selectedItem(MouseEvent mouseEvent) {
        String selectedItem = itemsInArea.getSelectionModel().getSelectedItem().toString();
        sendInput("vloz " + selectedItem);
        update();
    }

    /**
     * Metoda vykreslující inventáře - vezme seznam předmětů v daném inventáři,
     * přidá k nim obrázky a vykreslí do ListView.
     * @param view
     */
    private void renderInventory(ListView view) {
        view.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            public void updateItem(String item, boolean empty) {
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image("/icons/" + item + ".png"));
                    setText(item);
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(20);
                    setGraphic(imageView);
                }
            }

        });

    }

    /**
     * Metoda aktualizující stavy inventářů a seznamu východů.
     * Je volána vždy když se ve hře něco změní.
     */
    @Override
    public void update() {
        //aktualizace seznamu sousedních lokací
        listOfAdjacentAreas.getItems().clear();
        listOfAdjacentAreas.getItems().addAll(game.getGamePlan().getCurrentArea().getAjdacentAreas());

        //aktualuzace seznamu předmětů v současné lokaci
        itemsInArea.getItems().clear();
        itemsInArea.getItems().addAll(game.getGamePlan().getCurrentArea().getItemsInArea());

        //aktualizace předmětů v inventáří nákupní košík
        itemsInShoppingCart.getItems().clear();
        itemsInShoppingCart.getItems().addAll(game.getGamePlan().getShoppingCart().namesOfStoredItems());
        renderInventory(itemsInShoppingCart);

        //aktualizace předmětů v inventáři batoh
        itemsInBackpack.getItems().clear();
        itemsInBackpack.getItems().addAll(game.getGamePlan().getBackpack().namesOfStoredItems());
        renderInventory(itemsInBackpack);

        //aktualizace předmětů v inventáři auto
        itemsInCar.getItems().clear();
        itemsInCar.getItems().addAll(game.getGamePlan().getCar().namesOfStoredItems());
        renderInventory(itemsInCar);


        Integer currentBalanceNum = game.getGamePlan().getWallet().currentBalance();
        currentBalance.setText(currentBalanceNum.toString() + " CZK");

        //kontrola stavu nasazenosti roušky - změna ikonky hráče
        String nameOfCurrentArea = game.getGamePlan().getCurrentArea().getName();
        if (!game.getGamePlan().getClothing().isIncludingItem("rouska")) {
            playermodel1.setVisible(true);
            playermodel2.setVisible(false);
            playermodel1.setLayoutX(coordinates.get(nameOfCurrentArea).getX());
            playermodel1.setLayoutY(coordinates.get(nameOfCurrentArea).getY());
        } else {
            playermodel1.setVisible(false);
            playermodel2.setVisible(true);
            playermodel2.setLayoutX(coordinates.get(nameOfCurrentArea).getX());
            playermodel2.setLayoutY(coordinates.get(nameOfCurrentArea).getY());
        }

        //blokace tlačítek, pokud je hra vyhraná
        if (game.getGamePlan().isVictorious()) {
            listOfAdjacentAreas.setDisable(true);
            itemsInArea.setDisable(true);
            buttonLoad.setDisable(true);
            buttonPay.setDisable(true);
            buttonPutOnMask.setDisable(true);
            buttonWithdrawMoney.setDisable(true);
        }
    }

    /**
     * Metoda, která se aktivuje po zmáčknutí tlačítka 'zaplať'.
     * Odešle ke zpracování příkaz zaplať logice hry.
     *
     * @param actionEvent zmáčknutí tlačítka zaplať
     */
    public void pay(ActionEvent actionEvent) {
        sendInput("zaplat");
        update();
    }

    /**
     * Metoda, která se aktivuje po zmáčknutí tlačítka 'nalož'.
     * Odešle ke zpracování příkaz zaplať logice hry.
     *
     * @param actionEvent zmáčknutí tlačítka nalož
     */
    public void load(ActionEvent actionEvent) {
        sendInput("naloz");
        update();
    }

    /**
     * Metoda starající se o výběr peněz z bankomatu.
     * Od textové verze se liší, protože je zde implementována vlastním oknem.
     *
     * @param actionEvent zmáčknutí tlačítka vyber peníze
     * @throws Exception
     */
    @FXML
    public void withdrawMoney(ActionEvent actionEvent) throws Exception{
        if (game.getGamePlan().getCurrentArea().getName().equals("bankomat")) {
            if (alreadyWithdrawn == false) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/withdraw_screen.fxml"));
                    GridPane root = loader.load();

                    WithdrawScreenController controller = loader.getController();
                    controller.setGameProgress(game);
                    controller.passGameScreenController(this);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Výběr peněz");
                    stage.show();

                } catch (Exception e) {
                    System.err.println(e);
                }
            } else {
                textOutput.clear();
                textOutput.appendText("Peníze už sis vybral. Máš jich dostatek.");
            }

        } else {
            textOutput.clear();
            textOutput.appendText("Aby sis mohl vybrat peníze, potřebuješ být v lokaci 'bankomat'.");
        }

    }

    /**
     * Metoda přidávající 5000 Kč na účet a uzamykající možnost dalšího výběru
     * z bankomatu.
     */
    public void addBalance() {
        game.getGamePlan().getWallet().addBalance(5000);
        alreadyWithdrawn = true;
    }

    public void getThisController(GameScreenController passedController) {
        thisController = passedController;
    }

    /**
     * Metoda odesílající příkaz 'oblec rouska', který je specifický
     * a nedal se vyřešit jinak než vytvořením vlastního tlačítka.
     */
    public void putOnMask(ActionEvent actionEvent) {
        sendInput("oblec rouska");
        update();
    }

    /**
     * Metoda vytvářející kolakci tyupu map, která obsahuje souřadnice všech obchodů (lokací).
     *
     * @return coordinates souřadnice všech obchodů
     */
    private Map<String, Point2D> createCoordinates() {
        Map<String, Point2D> coordinates = new HashMap<>();
        coordinates.put("garaz", new Point2D(14, 83));
        coordinates.put("vstupni_hala", new Point2D(118, 83));
        coordinates.put("potraviny", new Point2D(115, 162));
        coordinates.put("bankomat", new Point2D(115, 4));
        coordinates.put("lekarna", new Point2D(320, 38));
        coordinates.put("patro2", new Point2D(219, 83));
        coordinates.put("zverimex",new Point2D(422, 129));
        coordinates.put("knihkupectvi", new Point2D(422, 38));
        coordinates.put("portal", new Point2D(522, 129));
        coordinates.put("comicspoint", new Point2D(320, 129));
        coordinates.put("elektro", new Point2D(522, 38));
        return coordinates;
    }

    /**
     * Metoda zakládající novou hru zavoláním metody initialize, která přepíše instanci třídy game,
     * čímž vytvoří novou hru.
     */
    public void newGame() {
        textOutput.clear();
        initialize();
    }

    /**
     * Metoda zobrazující HTML nápovědu ve vlastním okně.
     */
    public void showHelp() {
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
     * Metoda ukončující aplikaci.
     */
    public void endApplication() {
        Platform.exit();
        System.exit(0);
    }
}
