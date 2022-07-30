package cz.vse.java.nerv01.adventura.core;

/**
 * Třída, která implementuje příkaz 'pomoc', pro zobrazení nápovědy ke hře.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandHelp implements ICommand {
    private static final String NAME = "pomoc";
    private ListOfCommands listOfCommands;
    
    /**
     * Konstruktor třídy CommandHelp
     * 
     * @param listOfCommands odkaz na seznam příkazů, které je možné ve hře použít
     */
    public CommandHelp(ListOfCommands listOfCommands) {
        this.listOfCommands = listOfCommands;
    }
    
    /**
     * Metoda vrací obecnou nápovědu ke hře. Nyní vypisuje vcelku primitivní
     * zprávu o herním příběhu a seznam dostupných příkazů, které může hráč
     * používat.
     * 
     * @param parameters parametry příkazu <i>(aktuálně se ignorují)</i>
     * @return nápověda, která se vypíše na konzoli
     */
    @Override
    public String process(String... parameters) {
        return "***** NÁPOVĚDA *****\n"
                + "Cílem této adventury je nakoupit všechny položky\n"
                + "z poskytnutého seznamu, přenést je do auta a odjet.\n"
                + "Seznam všech příkazů: " + listOfCommands.getNames();
    }
    
    /**
     * Metoda vrací názec příkazu, to je slovo {@value NAME}.
     * 
     * @return název příkazu
     */
    @Override
    public String getName() {
        return NAME;
    }
}
