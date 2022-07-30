package cz.vse.java.nerv01.adventura.cli;

import cz.vse.java.nerv01.adventura.core.IGame;

import java.io.*;
import java.util.Scanner;

/**
 * Třída představující uživatelské rozhraní hry, řešené pomocí příkazové řádky.
 * Třída čte vstup zadaný uživatelem a předává tento řetězec logice hry (core),
 * následně vypisuje odpověď logiky na konzoli.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandLineInterface {
    private IGame game;
    
    /**
     * Konstruktor třídy, který vytváří textové rozhraní pro hru.
     * 
     * @param game instance logiky hry
     */
    public CommandLineInterface(IGame game) {
        this.game = game;
    }
    
    /**
     * Hlavní metoda hry. Vypíše úvodní text, a pak v cyklu opakuje načtení
     * a zpracování příkazu od hráče dokud hra neskončí (tj. dokud isGameOver
     * nevrátí hodnotu false.
     * Nakonec vypíše text epilogu.
     */
    public void play() {
        System.out.println(game.getPrologue() + "\n");
        
        while (game.isGameOver() == false) {
            String line = readLine();
            System.out.println(game.processCommand(line) + "\n");
        }
        
        System.out.println(game.getEpilogue());
    }
    
    /**
     * Pomocná metoda pro čtení příkazů z konzole
     * 
     * @return řádek textu z konzole
     */
    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }
}