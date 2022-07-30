package cz.vse.java.nerv01.adventura.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testovací třída {@code PinCodeTest} slouží ke komplexnímu otestování
 * třídy {@link PinCodeTest}.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */

public class PinCodeTest {
    
    private Game game;
    /**
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @BeforeEach
    public void setUp() {
        game = new Game();
    }
    
    /**
     * Test správného vygenerování pinu
     */
    @Test
    public void testOfPinCorectness()
    {
        int pin = game.getGamePlan().getPinCode();
        assertTrue(pin <= 9999);
        assertTrue(pin >= 0);
        
    }
}
