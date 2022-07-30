package cz.vse.java.nerv01.adventura.main;

import cz.vse.java.nerv01.adventura.core.IGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Třída typu controller, který poskytuje backend pro speciální okno do adventury,
 * ve kterém se odehrává minihra s hádáním čísla.
 *
 * V textové verzi je funkcionalita kompletně naprogramována ve třídě Command Withdraw,
 * ale tato třída byla absolutně nevhodná pro použití v GUI, proto se většina kódu
 * z této třídy zkopírovala sem, a kód byl následně upraven pro potřeby GUI.
 */
public class WithdrawScreenController {
    IGame gameProgress;
    Integer pinCode;

    @FXML
    TextField firstDigit;
    Integer firstDigitString;
    @FXML
    TextField secondDigit;
    Integer secondDigitString;
    @FXML
    TextField thirdDigit;
    Integer thirdDigitString;
    @FXML
    TextField fourthDigit;
    Integer fourthDigitString;

    @FXML
    TextArea withdrawOutput;

    @FXML
    Button buttonEnterPin;

    GameScreenController gameScreenController;


    /**
     * Metoda starající se o vytvoření GUI prostředí a o přesunutí instance třídy Game
     * z controlleru TitleScreenController. Tato instance sem byla překopírována,
     * aby se z ní překopíroval pin kód, který je pro každou instanci game generován náhodně,
     * a následně aby se k němu dalo přistupovat.
     *
     * @param game instance rozehrané hry
     */
    @FXML
    public void setGameProgress(IGame game) {
        this.gameProgress = game;
        getGeneratedPinCode();

        firstDigitString = 0;
        secondDigitString = 0;
        thirdDigitString = 0;
        fourthDigitString = 0;

        firstDigit.setEditable(false);
        secondDigit.setEditable(false);
        thirdDigit.setEditable(false);
        fourthDigit.setEditable(false);

        updateDigits();
    }

    /**
     * Metoda aktualizující hodnotu číslic a starající se o jejich výpis.
     */
    private void updateDigits() {
        firstDigit.setText(firstDigitString.toString());
        secondDigit.setText(secondDigitString.toString());
        thirdDigit.setText(thirdDigitString.toString());
        fourthDigit.setText(fourthDigitString.toString());
    }

    /**
     * Metoda typu getter, získávající vygenerovaný pin kód.
     */
    private void getGeneratedPinCode() {
        pinCode = gameProgress.getGamePlan().getPinCode();
    }

    /**
     * Metoda inkrementující číslici po zmáčknutí příslušného tlačítka.
     * @param actionEvent kliknutí na tlačítko inkrementace
     */
    public void firstDigitIncrement(ActionEvent actionEvent) {
        if (firstDigitString < 9) {
            firstDigitString++;
        }
        updateDigits();
    }

    /**
     * Metoda inkrementující číslici po zmáčknutí příslušného tlačítka.
     * @param actionEvent kliknutí na tlačítko inkrementace
     */
    public void secondDigitIncrement(ActionEvent actionEvent) {
        if (secondDigitString < 9) {
            secondDigitString++;
        }
        updateDigits();
    }

    /**
     * Metoda inkrementující číslici po zmáčknutí příslušného tlačítka.
     * @param actionEvent kliknutí na tlačítko inkrementace
     */
    public void thirdDigitIncrement(ActionEvent actionEvent) {
        if (thirdDigitString < 9) {
            thirdDigitString++;
        }
        updateDigits();
    }
    /**
     * Metoda inkrementující číslici po zmáčknutí příslušného tlačítka.
     * @param actionEvent kliknutí na tlačítko inkrementace
     */
    public void fourthDigitIncrement(ActionEvent actionEvent) {
        if (fourthDigitString < 9) {
            fourthDigitString++;
        }
        updateDigits();
    }

    /**
     * Metoda dekrementující číslici po zmáčknutí příslušného tlačítka.
     * @param actionEvent kliknutí na tlačítko dekrementace
     */
    public void firstDigitDecrement(ActionEvent actionEvent) {
        if (firstDigitString > 0) {
            firstDigitString--;
        }
        updateDigits();
    }

    /**
     * Metoda dekrementující číslici po zmáčknutí příslušného tlačítka.
     * @param actionEvent kliknutí na tlačítko dekrementace
     */
    public void secondDigitDecrement(ActionEvent actionEvent) {
        if (secondDigitString > 0) {
            secondDigitString--;
        }
        updateDigits();
    }

    /**
     * Metoda dekrementující číslici po zmáčknutí příslušného tlačítka.
     * @param actionEvent kliknutí na tlačítko dekrementace
     */
    public void thirdDigitDecrement(ActionEvent actionEvent) {
        if (thirdDigitString > 0) {
            thirdDigitString--;
        }
        updateDigits();
    }

    /**
     * Metoda dekrementující číslici po zmáčknutí příslušného tlačítka.
     * @param actionEvent kliknutí na tlačítko dekrementace
     */
    public void fourthDigitDecrement(ActionEvent actionEvent) {
        if (fourthDigitString > 0) {
            fourthDigitString--;
        }
        updateDigits();
    }

    /**
     * Metoda kontrolující správnost zadaného pin kódu.
     *
     * @param actionEvent kliknutí na příslušné tlačítko
     * @throws IOException
     */
    public void enterPin(ActionEvent actionEvent) throws IOException {
        Integer guessedPin = (firstDigitString * 1000) + (secondDigitString * 100) + (thirdDigitString * 10) + (fourthDigitString * 1);
        if (pinCode.equals(guessedPin)) {
            withdrawOutput.clear();
            withdrawOutput.appendText("Pin " + guessedPin + " je správný pin.\n");
            withdrawOutput.appendText("Vybral jsi 5 000 Kč.");

            buttonEnterPin.setDisable(true);

            gameScreenController.addBalance();
            gameScreenController.update();
        } else if (pinCode > guessedPin) {
            withdrawOutput.clear();
            withdrawOutput.appendText("To není správný pin,\n zkus větší číslo. ");
        } else if (pinCode < guessedPin) {
            withdrawOutput.clear();
            withdrawOutput.appendText("To není správný pin,\n zkus menší číslo. ");
        }
    }

    /**
     * Metoda kopírující odkaz na controller GameScreenController,
     * přes který se po zadání správného pinu volá metoda v daném kontrolleru,
     * která navyšuje zůstatek peněz.
     *
     * @param controller instance controlleru GameScreenController
     */
    public void passGameScreenController (GameScreenController controller) {
        gameScreenController = controller;
    }
}
