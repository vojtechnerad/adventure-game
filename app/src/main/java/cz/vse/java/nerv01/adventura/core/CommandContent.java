package cz.vse.java.nerv01.adventura.core;

/**
 * Instance třídy {@code CommandContent} představují ...
 * The {@code CommandContent} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class CommandContent implements ICommand {
    private static final String NAME = "obsah";
    private GamePlan plan;
    /**
     * Konstruktor třídy CommandContent.
     */
    public CommandContent(GamePlan plan) {
        this.plan = plan;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String process(String... parameters) {
        if (parameters.length == 0) {
            return "Za příkaz musíš zapsat parametr kterého inventáře chceš vypsat obsah, napiš to ve tvaru [obsah] [jméno inventáře]";
        } else if (parameters.length > 1) {
            return "Za příkaz nelze zadat víc než jeden parametr, napíš to ve tvaru - [obsah] [parametr]";
        } else {
            String inventoryName = parameters[0];

            if (inventoryName.equals(plan.getBackpack().getName())) { // PÍČOVINA
                return plan.getBackpack().listOfStoredItems();
            } else if (inventoryName.equals(plan.getShoppingCart().getName())) {
                return plan.getShoppingCart().listOfStoredItems();
            } else if (inventoryName.equals("penezenka")) {
                return "V peněžence máš " + plan.getWallet().currentBalance() + " Kč";
            } else if (inventoryName.equals(plan.getClothing().getName())) {
                return plan.getClothing().listOfStoredItems();
            } else if (inventoryName.equals("auto")) {
                return plan.getCar().listOfStoredItems();
            } else {
                return "Špatně zadaný parametr, takovýto inventář neexistuje";
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public String getName() {
        return NAME;
    }
}
