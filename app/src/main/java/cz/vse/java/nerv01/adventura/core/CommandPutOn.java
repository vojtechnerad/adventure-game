package cz.vse.java.nerv01.adventura.core;

/**
 * Třída CommandPutOn vytváří příkaz 'oblec' (instanci třídy),
 * který zprostředkovává v této adventuře nasazování roušky.
 * V programu to funguje tak, že pokud má hráč zakoupenou roušku,
 * tj. nachází se v inventáři 'batoh', po zadání příkazu 'oblec rouska'
 * se rouška přesune z inventáře 'batoh' do inventáře 'obleceni'.
 * Toto umožňuje hráčovi vchod do všech obchodů (lokací).
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandPutOn implements ICommand {
    private static final String NAME = "oblec";
    private GamePlan plan;
    private Item item;
    /**
     * Konstruktor pro příkaz 'oblec'
     */
    public CommandPutOn(GamePlan plan) {
        this.plan = plan;
    }
    
    /**
     * Metoda, která zpracovává příkaz 'oblec';
     */
    @Override
    public String process(String... parameters) {
        if (parameters.length == 0) {
            return "Nezadal jsi co chceš obléct, zkus to zapsat ve tvaru: oblec [parametr]";
        } else if (parameters.length > 1) {
            return "Za příkaz nelze zadat víc než jeden parametr, napíš to ve tvaru: oblec [parametr]";
        } else {
            String itemName = parameters[0];
            
            // Kontrola jestli nasazovaný předmět (parametr) je rouška
            if (itemName.equals("rouska")) {
                // Kontrola jestli inventář 'batoh' obsahuje předmět 'rouska'
                if (plan.getBackpack().isIncludingItem("rouska")) {
                    item = plan.getBackpack().getFirstItem();
                    plan.getClothing().addItemToInventory(item);
                    plan.getBackpack().deleteContentInInventory();
                    return "Nasadil sis roušku, nyní můžeš do všech obchodů.";
                } else {
                    return "Nemůžeš si obléct roušku, protože jí u sebe momentálně nemáš";
                }
            } else {
                return "Předmět " + itemName + " si obléknout nemůžeš.";
            }
        }
    }
    
    /**
     * Getter návzu příkazu
     * 
     * @return 
     */
    @Override
    public String getName() {
        return NAME;
    }
}
