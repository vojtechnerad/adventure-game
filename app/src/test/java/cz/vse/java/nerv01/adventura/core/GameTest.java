package cz.vse.java.nerv01.adventura.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovací třída {@code GameTest} slouží ke komplexnímu otestování
 * třídy {@link Game}.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class GameTest {
    private Game game1;
    
    /**
     * Metoda se provede před spuštěním kalsické testovací metody.
     * Používá se k vytvoření fixture, což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        game1 = new Game();
    }

    /**
     * TestWin projde celou hru až do výhry
     */
    @Test
    public void testWin() {
        assertEquals(garage(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goLobby());
        assertEquals(false, game1.isGameOver());
        game1.getGamePlan().getWallet().addBalance(5000);
        /*
        assertEquals(lobby(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi bankomat");
        assertEquals(false, game1.isGameOver());
        
        System.out.println(game1.getGamePlan().getPinCode());
        assertEquals("bankomat", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vyber_penize");
        assertEquals(false, game1.isGameOver());
        
        assertEquals("bankomat", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goLobby());
        assertEquals(false, game1.isGameOver());
        */
        assertEquals(lobby(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goSecondFloor());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(secondFloor(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi lekarna");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(pharmacy(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz rouska");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(pharmacy(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(pay());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(pharmacy(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("oblec rouska");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(pharmacy(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goSecondFloor());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(secondFloor(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi knihkupectvi");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(bookStore(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz ekonomie");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(bookStore(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz java");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(bookStore(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(pay());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(bookStore(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goSecondFloor());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(secondFloor(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi elektro");
        assertEquals(false, game1.isGameOver());
        
        assertEquals("elektro", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz zarovky");
        assertEquals(false, game1.isGameOver());
        
        assertEquals("elektro", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(pay());
        assertEquals(false, game1.isGameOver());
        
        assertEquals("elektro", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goSecondFloor());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(secondFloor(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi zverimex");
        assertEquals(false, game1.isGameOver());
        
        assertEquals("zverimex", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz granule");
        assertEquals(false, game1.isGameOver());
        
        assertEquals("zverimex", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(pay());
        assertEquals(false, game1.isGameOver());
        
        assertEquals("zverimex", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goSecondFloor());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(secondFloor(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi comicspoint");
        assertEquals(false, game1.isGameOver());
        
        assertEquals("comicspoint", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz figurka");
        assertEquals(false, game1.isGameOver());
        
        assertEquals("comicspoint", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(pay());
        assertEquals(false, game1.isGameOver());
        
        assertEquals("comicspoint", game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goSecondFloor());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(secondFloor(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goLobby());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(lobby(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi garaz");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(garage(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("naloz");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(garage(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goLobby());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(lobby(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi potraviny");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz pizza");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz papir");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(pay());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goLobby());     
        assertEquals(false, game1.isGameOver());
        
        assertEquals(lobby(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi garaz");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(garage(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("naloz");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(garage(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goLobby());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(lobby(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi potraviny");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz dzus");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz vejce");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("vloz mleko");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(pay());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(groceries(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand(goLobby());
        assertEquals(false, game1.isGameOver());
        
        assertEquals(lobby(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("jdi garaz");
        assertEquals(false, game1.isGameOver());
        
        assertEquals(garage(), game1.getGamePlan().getCurrentArea().getName());
        game1.processCommand("naloz");
        assertEquals(true, game1.isGameOver());
        
    }
    
    private String lobby() {
        return "vstupni_hala";
    }
    
    private String goLobby() {
        return "jdi vstupni_hala";
    }
    
    private String garage() {
        return "garaz";
    }
    
    private String goSecondFloor() {
        return "jdi patro2";
    }
    
    
    private String secondFloor() {
        return "patro2";
    }
    
    private String pharmacy() {
        return "lekarna";
    }
    
    private String pay() {
        return "zaplat";
    }
    
    private String bookStore() {
        return "knihkupectvi";
    }
    
    private String groceries() {
        return "potraviny";
    }
}
