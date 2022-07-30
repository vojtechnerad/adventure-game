package cz.vse.java.nerv01.adventura.core;

/**
 * Instance třídy {@code CommandSearch} představují ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class CommandSearch implements ICommand {
    private static final String NAME = "prohledej";
    private GamePlan plan;
    
    /**
     * 
     */
    public CommandSearch(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda která vytváří příkaz 'prohledej'
     */
    @Override
    public String process(String... parameters) {
        // Kontrola počtu parametrů
        if (parameters.length == 0) {
            return "Nezadal jsi co se má prohledat, napiš to ve tvaru - [prohledej] [parametr]";
        } else if (parameters.length > 1) {
            return "Za příkaz nelze zadat víc než jeden parametr, napíš to ve tvaru - [prohledej] [parametr]";
        }

        // Zapsání parametru do proměnné
        String chestName = parameters[0];
        Area currentArea = plan.getCurrentArea();
        if (currentArea.getName().equals("potraviny")) {
            if (chestName.equals("truhla")) {
                Item key = plan.getChest().getFirstItem();
                plan.getBackpack().addItemToInventory(key);
                plan.getChest().deleteContentInInventory();
                return "Našel si klíč k portálu a vložil si jej do batohu.";
                
            } else {
                return "Nic takovýho se tady nedá prohledat.";
                
            }
            
        } else {
            return "Tady není co k prohledávání.";
            
        }
        
    }
    
    /**
     * Getter pro název příkazu
     */
    @Override
    public String getName() {
        return NAME;
    }
}
