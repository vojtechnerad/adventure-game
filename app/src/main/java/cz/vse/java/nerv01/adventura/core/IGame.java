package cz.vse.java.nerv01.adventura.core;

/**
 * Rozhraní, které musí implementovat hra.
 * Na rozhraní je navázáno textové uživatelské rozhraní (CLI).
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public interface IGame {
    /**
     * Metoda, která vrátí úvodní zprávu pro hráče.
     * 
     * @return vrací řetězec, který se vypíše na CLI
     */
    public String getPrologue();
    
    /**
     * Metoda, která vrátí závěrečnou zprávu pro hráče.
     * 
     * @return vrací řetězec, který se vypíše na CLI
     */
    public String getEpilogue();
    
    /**
     * Metoda, která vrací informaci o tom, jestli hra skončila.
     * Je jedno jestli výhrou, prohrou nebo příkazem konec.
     * 
     * @return vrací hodnotu true, pokud hra skončila
     */
    public boolean isGameOver();
    
    /**
     * Metoda, která zpracuje řetězec zapsaný do CLI. Řetězec rozdělí
     * na část příkazu a část parametru - [příkaz] [parametr].
     * Dále se otestuje, zdali je zadaný příkaz skutečně předdefinovaným
     * příkazem, pokud ano, příkaz se provede.
     * 
     * @param line text, který zadal uživatel jako příkaz (popřípadě i parametr) do hry.
     * @return vrací se řetězec, který se má vypsat na CLI
     */
    public String processCommand(String line);
    
    
    /**
     * Metoda, která vrací odkaz na herní plán.
     * Je využita převážně v testech, kde se jejím
     * prostřednictvím získává aktuální místnost hry.
     * 
     * @return odkaz na herní plán
     */
    public GamePlan getGamePlan();
}
