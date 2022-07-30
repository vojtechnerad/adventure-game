package cz.vse.java.nerv01.adventura.core;

/**
 * Rozhraní, které definuje metody nutné pro implementaci herního příkazu.
 * Pro každý příkaz, který je možné ve hře použít,
 * existuje třída zajišťující jeho obsluhu.
 * Třída musí implementovat metody definované tímto rozhraním.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public interface ICommand {
    /**
     * Metoda, která zajišťuje provedení herního příkazu.
     * Počet parametrů je závislý na konkrétním příkazu,
     * například příkazy 'napoveda', 'konec', 'zaplat' a 'vyber_penize' žádné
     * parametry nemají. Naopak příkazy 'jdi' a 'vloz' parametr mají právě jeden.
     * Metoda musí zkontrolovat parametry, provést změny ve stavu hry
     * a následovně vrátit text, který se hráči vypíše na CLI.
     * 
     * @param parameters parametry příkazu (v této adventuře buď žádný, nebo jeden)
     * @return výsledek zpracování (informace pro hráče, které se vypíšou na CLI)
     */
    String process(String... parameters);
    
    /**
     * Metoda vrací název příkazu.
     * Jedná se o slovo, které hráč používá pro vyvolání příkazu,
     * například 'napoveda', 'konec' a podobné.
     * 
     * @return název příkazu
     */
    String getName();
}