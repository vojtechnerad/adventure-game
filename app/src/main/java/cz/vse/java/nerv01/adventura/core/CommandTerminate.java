package cz.vse.java.nerv01.adventura.core;

/**
 * Třída implementující příkaz 'konec' pro ukončení hry.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandTerminate implements ICommand {
    private static final String NAME = "konec";
    private Game game;
    
    /**
     * Konstruktor třídy CommandTerminate
     * 
     * @param game odkaz na hru, která má být příkazem ukončena
     */
    public CommandTerminate(Game game) {
        this.game = game;
    }
    
    /**
     * V případě, že je metoda zavolána bez parametrů <i>(hráč na konzoli zadá
     * pouze slovo {@value NAME})</i>, ukončí hru. Jinak vrátí chybovou hlášku
     * a hra pokračuje.
     * 
     * @param parameters parametry příkazu <i>(očekává se prázdné pole)</i>
     * @return informace pro hráče, která se vypíše na CLI
     */
    @Override
    public String process(String... parameters) {
        if (parameters.length > 0) {
            return "Nechápu co mám ukončit, nechceš radši ukončit sebe?\n"
            + "Příkaz [" + NAME + "] se používá bez parametrů.";
        }
        game.setGameOver(true);
        return "Hra byla ukončena příkazem '" + NAME+ "'.";
    }
    
    /**
     * Metoda vrací název příkazu, to je slovo {@value NAME}.
     * 
     * @return název příkazu
     */
    @Override
    public String getName() {
        return NAME;
    }
}
